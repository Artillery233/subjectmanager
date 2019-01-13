package database.CourseEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Course")
public class CourseEntity {
    @ColumnInfo
    private String Cname;
    @PrimaryKey
    @NonNull
    private String CNO;
    @ColumnInfo
    private String CTeacher;
    @ColumnInfo
    private String CStart;
    @ColumnInfo
    private String CEnd;
    @ColumnInfo
    private boolean isAlter;

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCNO() {
        return CNO;
    }

    public void setCNO(String CNO) {
        this.CNO = CNO;
    }

    public String getCTeacher() {
        return CTeacher;
    }

    public void setCTeacher(String CTeacher) {
        this.CTeacher = CTeacher;
    }

    public String getCStart() {
        return CStart;
    }

    public void setCStart(String CStart) {
        this.CStart = CStart;
    }

    public String getCEnd() {
        return CEnd;
    }

    public void setCEnd(String CEnd) {
        this.CEnd = CEnd;
    }

    public boolean isAlter() {
        return isAlter;
    }

    public void setAlter(boolean alter) {
        isAlter = alter;
    }
    public  void  CourseEntity(String name,String no,String teacher){
        this.Cname = name;
        this.CNO = no;
        this.CTeacher = teacher;
    }
}
