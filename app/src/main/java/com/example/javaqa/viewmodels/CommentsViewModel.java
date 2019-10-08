package com.example.javaqa.viewmodels;

import android.app.Application;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.javaqa.models.CommentsModel;
import com.example.javaqa.repository.remoteDatabase.firebase.CommentsForPostsFirebase;

import java.util.List;

public class CommentsViewModel extends AndroidViewModel {

  private MutableLiveData<List<CommentsModel>> mComments;
  private CommentsForPostsFirebase commentsForPostsFirebase;
  private ProgressBar mProgressBar;
  private TextView mEmptyListText;

  public CommentsViewModel(@NonNull Application application) {
    super(application);
    commentsForPostsFirebase = new CommentsForPostsFirebase();
    mComments = new MutableLiveData<>();
  }

  public void setProgressBar(ProgressBar progressBar, TextView emptyListText){
    mProgressBar = progressBar;
    mEmptyListText = emptyListText;
    mProgressBar.setVisibility(View.VISIBLE);
  }

  public void insertComment(CommentsModel comment){
    commentsForPostsFirebase.insert(comment);
  }

  public void deleteComment(CommentsModel comment){
    commentsForPostsFirebase.delete(comment);
  }

  public LiveData<List<CommentsModel>> getComments(){

    commentsForPostsFirebase.getComments().observeForever(commentsModels -> {
      if(!commentsModels.isEmpty()) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyListText.setVisibility(View.GONE);
      } else {
        mProgressBar.setVisibility(View.INVISIBLE);
        mEmptyListText.setVisibility(View.VISIBLE);
      }
      mComments.postValue(commentsModels);
    });
    return mComments;
  }

  public void setPathToComments(String key) {
    commentsForPostsFirebase.setPath(key);
  }
}
