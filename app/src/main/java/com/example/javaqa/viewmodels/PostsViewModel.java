package com.example.javaqa.viewmodels;

import android.app.Application;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.PostData;
import com.example.javaqa.repository.mainRepositories.PostsMainRepository;

import java.util.List;


public class PostsViewModel extends AndroidViewModel {

  private MutableLiveData<List<PostData>> mPosts = new MutableLiveData<>();
  private final PostsMainRepository mPostsMainRepository;
  private ProgressBar mProgressBar;

  public PostsViewModel(@NonNull Application application) {
    super(application);
    setProgressBar();
    mPostsMainRepository = PostsMainRepository.getInstance(application);
    mPostsMainRepository.loadPosts();
  }

  public void setProgressBar() {
    mProgressBar = new ProgressBar(getApplication(),null,android.R.style.Widget_Material_ProgressBar);
    mProgressBar.setVisibility(View.VISIBLE);
  }

  public void update(PostData postData){
    mPostsMainRepository.update(postData);
  }

  public void insert(PostData postData){
    mPostsMainRepository.insert(postData);
  }

  public void delete(PostData postData){
    mPostsMainRepository.delete(postData);
  }

  public String getKey(){
    return mPostsMainRepository.getKey();
  }

  public LiveData<List<PostData>> getAllPosts() {
    mPostsMainRepository.getAllPosts().observeForever(postData -> {
      if(!postData.isEmpty()) {
         mProgressBar.setVisibility(View.GONE);
      } else {
        mProgressBar.setVisibility(View.VISIBLE);
      }
      mPosts.postValue(postData);
    });
    return mPosts;
  }

}
