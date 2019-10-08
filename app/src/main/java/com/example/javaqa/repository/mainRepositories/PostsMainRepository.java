package com.example.javaqa.repository.mainRepositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.PostData;
import com.example.javaqa.repository.remoteDatabase.firebase.PostsFirebase;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostsMainRepository {

  private MutableLiveData<List<PostData>> posts = new MutableLiveData<>();
  private static PostsMainRepository mInstance;
  private static PostsFirebase postsFirebase;

  public static  PostsMainRepository getInstance(Application application) {
    if(mInstance == null) {
      mInstance = new PostsMainRepository();
      postsFirebase = new PostsFirebase();
      postsFirebase.loadPosts();
    }
    return mInstance;
  }

  public void update(PostData postData) {
    postsFirebase.update(postData);
  }

  public void insert(PostData postData){
    postsFirebase.insert(postData);
  }

  public String getKey(){
    return postsFirebase.getKey();
  }

  public void delete(PostData postData){
    postsFirebase.delete(postData);
  }

  public void loadPosts(){
    postsFirebase.getPosts().observeForever(postObs -> {
      if(postObs != null) {
        Log.d(TAG, "getAllPosts: not null");
        posts.postValue(postObs);
      }
    });
  }

  public MutableLiveData<List<PostData>> getAllPosts() {
    return posts;
  }

}
