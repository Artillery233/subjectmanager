package UI;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.subjectmanager.subjectmanager.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.CourseAdapter;
import database.AppDatabase;
import database.CourseEntity.CourseEntity;
import database.dao.Coursedao;
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
        CourseEntity courseEntity = new CourseEntity();
        Coursedao dao = database.getSubjectDao();
        //增加
        courseEntity.setCname("Math");
        courseEntity.setCNO("12021");
        courseEntity.setCTeacher("Liuxiaodong");
        courseEntity.setCStart("2.30pm");
        courseEntity.setCEnd("3.00pm");
        courseEntity.setAlter(false);
        adapter = new CourseAdapter(HomeActivity.this, R.layout.course_item_layout, courseList);
        ListView listView = findViewById(R.id.course);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseEntity course = courseList.get(position);
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("Course", course.getCname());
                startActivity(intent);
            }
        });
        mDisposable.add(
                dao.add(courseEntity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subjects -> {

                        }, throwable -> {
                            //出错的时候的处理
                        }));
        mDisposable.add(
                dao.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subjects -> {
                            //获取数据后的处理
                            //subjects的类型是List<SubjectEntity>
                            //Adapter.setList(subjects)
                            courseList = subjects;
                            adapter.notifyDataSetChanged();

                        }, throwable -> {
                            //出错的时候的处理
                        }));


    }

    private void initCourse() {
        //接收数据库的数据
//        AppDatabase database = AppDatabase.getInstance();
//        CourseEntity courseEntity = new CourseEntity();
//        Coursedao dao = database.getSubjectDao();
//        //增加
//        courseEntity.setCname("Math");
//        courseEntity.setCNO("12021");
//        courseEntity.setCTeacher("Liuxiaodong");
//        courseEntity.setCStart("2.30pm");
//        courseEntity.setCEnd("3.00pm");
//        courseEntity.setAlter(false);
//        dao.add(courseEntity);
//        CourseEntity Chinese = new CourseEntity();
//        Chinese.setCname("语文");
//        Chinese.setCNO("12022");
//        Chinese.setCTeacher("Maoqin");
//        Chinese.setCStart("9.50am");
//        Chinese.setCEnd("10.30am");
//        Chinese.setAlter(true);
//        dao.add(Chinese);
//                mDisposable.add(
//                        dao.getAll()
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(subjects -> {
//                                    //获取数据后的处理
//                                    //subjects的类型是List<SubjectEntity>
//                                    //Adapter.setList(subjects)
//                                    courseList = subjects;
//                                }, throwable -> {
//                                    //出错的时候的处理
//                                }));


//        //查询单个
//        mDisposable.add(
//                dao.getOne("Android")
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidScheduler.mainThread())
//                        .subscribe(subject -> {
//                            //获取数据后的处理
//                            //subject的类型是SubjectEntity
//                            //编辑操作
//                        }, throwable -> {
//                            //出错的时候的处理
//                        }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }
}