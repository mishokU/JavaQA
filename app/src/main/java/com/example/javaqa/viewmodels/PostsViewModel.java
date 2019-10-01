package com.example.javaqa.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.javaqa.models.PostData;
import com.example.javaqa.repository.localDatabase.PostRepository;
import com.example.javaqa.repository.mainRep.MainRepository;
import com.example.javaqa.repository.remoteDatabase.RemotePostsService;
import com.example.javaqa.repository.utils.Resource;

import java.util.List;
import java.util.Map;

import javax.inject.Provider;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class PostsViewModel extends AndroidViewModel {

  private final MainRepository mainRepository;

  public PostsViewModel(@NonNull Application application) {
    super(application);
    mainRepository = MainRepository.getInstance(application);
  }

  public LiveData<Resource<List<PostData>>> getAllPosts() {
      Log.d(TAG, "getAllPosts: title" + mainRepository.getAllPosts().toString());
    return mainRepository.getAllPosts();
  }
}
