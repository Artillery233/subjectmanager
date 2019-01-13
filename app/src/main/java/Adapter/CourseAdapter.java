package Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.subjectmanager.subjectmanager.R;

import java.util.List;

import database.CourseEntity.CourseEntity;

public class CourseAdapter extends ArrayAdapter<CourseEntity> {
    private List<CourseEntity> courseList;

    private int id;

    public CourseAdapter(Context context, int textViewResourceId, List<CourseEntity> objects) {
        super(context, textViewResourceId, objects);
        id = textViewResourceId;
    }




    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        CourseEntity course=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(id,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.courseName = view.findViewById(R.id.courseName);
            viewHolder.courseNo = view.findViewById(R.id.courseNo);
            viewHolder.courseTeacher = view.findViewById(R.id.courseTeacher);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.courseName.setText(course.getCname());
//        viewHolder.courseNo.setText(course.getCNO());
//        viewHolder.courseTeacher.setText(course.getCTeacher());

        return  view;
    }

    private class ViewHolder {
        TextView courseName;
        TextView courseNo;
        TextView courseTeacher;
    }
}
