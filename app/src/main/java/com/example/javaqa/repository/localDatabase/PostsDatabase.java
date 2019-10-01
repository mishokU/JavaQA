package com.example.javaqa.repository.localDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.javaqa.models.PostData;

/*
  Singleton with all entities like posts friends and answers
 */

@Database(entities = {PostData.class}, version = 1)
public abstract class PostsDatabase extends RoomDatabase {

  private static PostsDatabase instance;

  public abstract PostDao postDao();

  public static synchronized PostsDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(
          context.getApplicationContext(),
          PostsDatabase.class, "posts_database")
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
      new PopulateDbAsyncTask(instance).execute();
    }
  };

  private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

    private PostDao postDao;

    private PopulateDbAsyncTask(PostsDatabase db) {
      postDao = db.postDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
      postDao.insert(new PostData());
      postDao.insert(new PostData());
      postDao.insert(new PostData());
      postDao.insert(new PostData());

      return null;
    }
  }

}
