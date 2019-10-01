package com.example.javaqa.repository.mainRep;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.javaqa.models.PostData;
import com.example.javaqa.repository.localDatabase.PostRepository;
import com.example.javaqa.repository.remoteDatabase.RemotePostsService;
import com.example.javaqa.repository.remoteDatabase.RetrofitClientInstance;
import com.example.javaqa.repository.utils.NetworkBoundResource;
import com.example.javaqa.repository.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainRepository  {

  private static final String TAG = "MainRep";

  private static PostRepository postRepository;
  private static RemotePostsService remotePostsService;
  private static MainRepository instance;

  public static  MainRepository getInstance(Application application) {
    if(instance == null) {
      instance = new MainRepository();
      postRepository = new PostRepository(application);
      remotePostsService = RetrofitClientInstance.getRetrofitInstance();
    }
    return instance;
  }

  public LiveData<Resource<List<PostData>>> getAllPosts() {
    return new NetworkBoundResource<List<PostData>, List<PostData>>() {

      @Override
      protected void saveCallResult(@NonNull List<PostData> item) {
        //postRepository.insertAllPosts(item);
      }

      @NonNull
      @Override
      protected LiveData<List<PostData>> loadFromDb() {
        return postRepository.getAllPosts();
      }

      @NonNull
      @Override
      protected Call<List<PostData>> createCall() {
        return remotePostsService.getAllPosts();
      }
    }.getAsLiveData();
  }

}
