package UI;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import database.CourseEntity.CourseEntity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.subjectmanager.subjectmanager.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        Intent intent =getIntent();
        TextView Cname = findViewById(R.id.name);
        TextView Details = findViewById(R.id.detailss);
        CourseEntity courseEntity = (CourseEntity) getIntent().getSerializableExtra("Course");
        Cname.setText(courseEntity.getCname());
        Details.setText(courseEntity.getCNO());
        Button note = findViewById(R.id.Bnote);
        Button update = findViewById(R.id.update);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailsActivity.this,NoteActivity.class);
                startActivity(intent1);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailsActivity.this,UpdateActivity.class);
                intent2.putExtra("upcourse",courseEntity);
                startActivity(intent2);
            }
        });
    }
}
