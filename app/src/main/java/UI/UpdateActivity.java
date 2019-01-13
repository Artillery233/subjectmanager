package UI;

import androidx.appcompat.app.AppCompatActivity;
import database.AppDatabase;
import database.CourseEntity.CourseEntity;
import database.dao.Coursedao;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.subjectmanager.subjectmanager.R;

public class UpdateActivity extends AppCompatActivity {
    private CompositeDisposable mDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_layout);
        CourseEntity courseEntity = (CourseEntity) getIntent().getSerializableExtra("upcourse");
        AppDatabase database = AppDatabase.getInstance();
        Coursedao dao = database.getSubjectDao();
        EditText CName = findViewById(R.id.upCname);
        EditText CNo = findViewById(R.id.upCNo);
        EditText CTeacher = findViewById(R.id.upCTeacher);
        EditText CStart = findViewById(R.id.upCStart);
        EditText CEnd = findViewById(R.id.upCEnd);
        Button sure = findViewById(R.id.upsure);
        CName.setText(courseEntity.getCname());
        CNo.setText(courseEntity.getCNO());
        CTeacher.setText(courseEntity.getCTeacher());
        CStart.setText(courseEntity.getCStart());
        CEnd.setText(courseEntity.getCEnd());
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDisposable.add(Completable.fromAction(() -> {dao.update(courseEntity);})
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                            Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        }, throwable -> {
                            Toast.makeText(getApplicationContext(), "添加出错啦", Toast.LENGTH_SHORT).show();
                        }));
            }
        });
    }
}
