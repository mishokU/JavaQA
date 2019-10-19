package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.FriendItem;
import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.repository.remoteDatabase.firebase.UserDataFirebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

  private LiveData<UserMainData> userMainData;
  private LiveData<GameStatistics> gameStatistics;
  private UserDataFirebase userDataFirebase;

  public UserViewModel(@NonNull Application application) {
    super(application);
    userDataFirebase = new UserDataFirebase();
  }

  public FirebaseAuth getAuth() {
    return userDataFirebase.getAuth();
  }

  public String getUserUid(){
    return userDataFirebase.getUserUid();
  }

  public MutableLiveData<UserMainData> getUserMainData(){
    userDataFirebase.setMainDataListener();
    return userDataFirebase.getUserMainDataMutableLiveData();
  }

  public LiveData<GameStatistics> getGameStatistics(){
    userDataFirebase.setGameStatictisListener();
    return userDataFirebase.getGameStatisticsMutableLiveData();
  }

  public LiveData<List<FriendItem>> getUserFriends() {
    userDataFirebase.setFriendsListener();
    return userDataFirebase.getUserFriends();
  }

  public void setGameStatistic(GameStatistics gameStatistics) {
    userDataFirebase.setGameStatistics(gameStatistics);
  }

  public void setUserMainData(UserMainData userMainData) {
    userDataFirebase.setUserMainData(userMainData);
  }

  public void createUser(UserMainData userMainData) {
    userDataFirebase.setUserMainData(userMainData);
  }

  public void updateAuth() {
    userDataFirebase.setUserAuthentication();
  }

  public void setLearningProgress(String type, int progress) {
    userDataFirebase.setLearningProgress(type, progress);
  }
}
