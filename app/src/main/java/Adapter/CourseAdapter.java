package Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.subjectmanager.subjectmanager.R;

import org.w3c.dom.Text;

import java.util.List;

import vo.Course;

public class CourseAdapter extends ArrayAdapter<Course> {
    private List<Course> courseList;
    private LayoutInflater inflater;
    private int id;
    public CourseAdapter( Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        id=resource;
    }


    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        Course course=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = inflater.from(getContext()).inflate(id, parent, false);
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
        viewHolder.courseNo.setText(course.getCNO());
        viewHolder.courseTeacher.setText(course.getCTeacher());

        return  view;
    }

    private class ViewHolder {
        TextView courseName;
        TextView courseNo;
        TextView courseTeacher;
    }
}
