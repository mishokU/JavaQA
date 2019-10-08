package com.example.javaqa.repository.localDatabase.friends;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.javaqa.models.FriendItem;

@Database(entities = {FriendItem.class}, version = 1)
public abstract class FriendsDatabase extends RoomDatabase {

  private static FriendsDatabase instance;

  public abstract FriendDao friendDao();

  public static synchronized FriendsDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(
          context.getApplicationContext(),
          FriendsDatabase.class, "posts_database")
          .fallbackToDestructiveMigration()
          .addCallback(roomCallBack)
          .build();
    }
    return instance;
  }

  private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }
  };

}
