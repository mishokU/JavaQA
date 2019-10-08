package com.example.javaqa.repository.localDatabase.friends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.javaqa.models.FriendItem;
import java.util.List;

@Dao
public interface FriendDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(FriendItem friend);

  @Update
  void update(FriendItem friend);

  @Delete
  void delete(FriendItem friend);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<FriendItem> friends);

  @Query("DELETE FROM table_friends")
  void deleteAllFriends();

  @Query("SELECT * FROM table_friends ORDER BY id DESC")
  LiveData<List<FriendItem>> getAllFriends();
}
