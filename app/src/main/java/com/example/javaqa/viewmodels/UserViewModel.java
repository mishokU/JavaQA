package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.repository.remoteDatabase.firebase.UserDataFirebase;

public class UserViewModel extends AndroidViewModel {

  private LiveData<UserMainData> userMainData;
  private LiveData<GameStatistics> gameStatistics;
  private UserDataFirebase userDataFirebase;

  public UserViewModel(@NonNull Application application) {
    super(application);
    userDataFirebase = new UserDataFirebase();
    userDataFirebase.setMainDataListener();
  }

  public String getUserUid(){
    return userDataFirebase.getUserUid();
  }

  public MutableLiveData<UserMainData> getUserMainData(){
    return userDataFirebase.getUserMainDataMutableLiveData();
  }

  public LiveData<GameStatistics> getGameStatistics(){
    userDataFirebase.setGameStatictisListener();
    return userDataFirebase.getGameStatisticsMutableLiveData();
  }

}
