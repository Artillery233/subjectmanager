package UI;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.subjectmanager.subjectmanager.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        Intent intent =getIntent();
        TextView Cname = findViewById(R.id.name);
        String coursename = intent.getStringExtra("Course");
        Cname.setText(coursename);
    }
}
