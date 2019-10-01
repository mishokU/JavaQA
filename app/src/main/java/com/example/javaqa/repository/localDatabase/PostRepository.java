package com.example.javaqa.repository.localDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.javaqa.models.PostData;

import java.util.List;

import static android.content.ContentValues.TAG;

public class PostRepository {

  private PostDao postDao;
  private LiveData<List<PostData>> allPosts;

  public PostRepository(Application application) {
    PostsDatabase database = PostsDatabase.getInstance(application);
    postDao = database.postDao();
    allPosts = postDao.getAllPosts();
  }

  public void insert(PostData post) {
    new InsertPostAsyncTask(postDao).execute(post);
  }

  public void update(PostData post) {
    new UpdatePostAsyncTask(postDao).execute(post);
  }

  public void insertAllPosts(List<PostData> posts) {
    if(posts != null) {
      postDao.insertAllPosts(posts);
    } else {
      Log.d(TAG, "insertAllPosts: null");
    }
  }

  public void delete(PostData post) {
    new DeletePostAsyncTask(postDao).execute(post);
  }

  public void deleteAllPosts(){
    new DeleteAllPostsAsyncTask(postDao).execute();
  }

  public LiveData<List<PostData>> getAllPosts() {
    return allPosts;
  }

  private static class InsertPostAsyncTask extends AsyncTask<PostData, Void, Void> {

    private PostDao postDao;

    private InsertPostAsyncTask(PostDao postDao) {
      this.postDao = postDao;
    }

    @Override
    protected Void doInBackground(PostData... postData) {
      postDao.insert(postData[0]);
      return null;
    }
  }

  private static class UpdatePostAsyncTask extends AsyncTask<PostData, Void, Void> {
    private PostDao postDao;

    private UpdatePostAsyncTask(PostDao noteDao) {
      this.postDao = noteDao;
    }

    @Override
    protected Void doInBackground(PostData... notes) {
      postDao.update(notes[0]);
      return null;
    }
  }

  private static class DeletePostAsyncTask extends AsyncTask<PostData, Void, Void> {
    private PostDao postDao;

    private DeletePostAsyncTask(PostDao noteDao) {
      this.postDao = noteDao;
    }

    @Override
    protected Void doInBackground(PostData... notes) {
      postDao.delete(notes[0]);
      return null;
    }
  }

  private static class DeleteAllPostsAsyncTask extends AsyncTask<Void, Void, Void> {
    private PostDao postDao;

    private DeleteAllPostsAsyncTask(PostDao noteDao) {
      this.postDao = noteDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      postDao.deleteAllPosts();
      return null;
    }
  }
}
