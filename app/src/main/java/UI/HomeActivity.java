package UI;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.subjectmanager.subjectmanager.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.CourseAdapter;
import database.AppDatabase;
import database.CourseEntity.CourseEntity;
import database.dao.Coursedao;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {
    private List<CourseEntity> courseList = new ArrayList<>();
    private CompositeDisposable mDisposable = new CompositeDisposable();

    CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        Button add = findViewById(R.id.addCourse);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddSubject.class);
                startActivity(intent);
            }
        });

        AppDatabase database = AppDatabase.getInstance();
        Coursedao dao = database.getSubjectDao();

        mDisposable.add(
                dao.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subjects -> {
                            courseList.addAll(subjects);
                            adapter.notifyDataSetChanged();

                        }, throwable -> {
                            //出错的时候的处理
                        }));
        adapter = new CourseAdapter(HomeActivity.this, R.layout.course_item_layout, courseList);
        ListView listView = findViewById(R.id.course);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseEntity course = courseList.get(position);
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("Course",course);
                startActivity(intent);
            }
        });




    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }
}