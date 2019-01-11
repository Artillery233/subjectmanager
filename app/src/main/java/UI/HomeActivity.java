package UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.subjectmanager.subjectmanager.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.CourseAdapter;
import vo.Course;


public class HomeActivity extends AppCompatActivity {
    private List<Course> courseList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        Button add=findViewById(R.id.addCourse);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent =new Intent(HomeActivity.this,AddSubject.class);
                startActivity(intent);
            }
        });
        initCourse();
        CourseAdapter adapter=new CourseAdapter(HomeActivity.this,R.id.course,courseList);
        ListView listView =findViewById(R.id.course);
        listView.setAdapter(adapter);
    }

    private void initCourse() {
        //接收数据库的数据
    }

}
