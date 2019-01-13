package UI;

import Adapter.CourseAdapter;
import androidx.appcompat.app.AppCompatActivity;
import database.AppDatabase;
import database.CourseEntity.CourseEntity;
import database.dao.Coursedao;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.subjectmanager.subjectmanager.R;

public class AddSubject extends AppCompatActivity {
    private CompositeDisposable mDisposable = new CompositeDisposable();
    CourseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject_layout);
        EditText Cname = findViewById(R.id.addCname);
        EditText Cno = findViewById(R.id.addCno);
        EditText CTeacher = findViewById(R.id.addCTeacher);
        EditText CStart = findViewById(R.id.addCStart);
        EditText CEnd = findViewById(R.id.addCEnd);
        Button add = findViewById(R.id.sure);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCname(Cname.getText().toString());
        courseEntity.setCNO(Cno.getText().toString());
        courseEntity.setCTeacher(CTeacher.getText().toString());
        courseEntity.setCStart(CStart.getText().toString());
        courseEntity.setCEnd(CEnd.getText().toString());
        AppDatabase database = AppDatabase.getInstance();
        Coursedao dao = database.getSubjectDao();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDisposable.add(
                        Completable.fromAction(() -> {dao.add(courseEntity);})
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                                }, throwable -> {
                                    //出错的时候的处理
                                    Toast.makeText(getApplicationContext(), "添加出错啦", Toast.LENGTH_SHORT).show();
                                    throwable.printStackTrace();
                                }));

            }
        });

}}
