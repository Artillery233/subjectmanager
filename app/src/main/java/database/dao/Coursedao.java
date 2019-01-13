package database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import database.CourseEntity.CourseEntity;
import io.reactivex.Flowable;

@Dao
public interface Coursedao  {
    @Query("select * from Course")
    Flowable<List<CourseEntity>> getAll();

    @Query("select * from Course where CNO = :CNO")
    Flowable<CourseEntity> getOne(String CNO);

    @Insert
    void add(CourseEntity entity);
    @Delete
    void delete(CourseEntity entity);
    @Update
    void update(CourseEntity entity);
}
