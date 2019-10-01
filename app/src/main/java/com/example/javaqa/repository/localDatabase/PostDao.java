package com.example.javaqa.repository.localDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.javaqa.models.PostData;

import java.util.List;

@Dao
public interface PostDao {

  @Insert (onConflict = OnConflictStrategy.REPLACE)
  void insert(PostData post);

  @Update
  void update(PostData post);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAllPosts(List<PostData> postEntities);

  @Delete
  void delete(PostData post);

  @Query("DELETE FROM table_posts")
  void deleteAllPosts();

  @Query("SELECT * FROM table_posts ORDER BY id DESC")
  LiveData<List<PostData>> getAllPosts();
}
