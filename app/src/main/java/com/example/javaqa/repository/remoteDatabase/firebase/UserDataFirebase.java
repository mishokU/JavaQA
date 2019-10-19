package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.FriendItem;
import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.ui.activities.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserDataFirebase {

  private FirebaseServerInstance firebaseServerInstance;
  private DatabaseReference reference;
  private FirebaseUser mUser;
  private DatabaseReference userReference;
  private DatabaseReference userMainDataReference;
  private DatabaseReference gameStatisticsReference;
  private DatabaseReference userFriendsReference;
  private FirebaseAuth auth;
  private String mUserUid;

  private Disposable userMainDataObserver;
  private Disposable gameStatisticsObserver;
  private Disposable userFriendsObserver;

  private MutableLiveData<UserMainData> userMainDataMutableLiveData = new MutableLiveData<>();
  private MutableLiveData<GameStatistics> gameStatisticsMutableLiveData = new MutableLiveData<>();
  private MutableLiveData<List<FriendItem>> userFriendsMutableLiveData = new MutableLiveData<>();

  public UserDataFirebase(){
    firebaseServerInstance = FirebaseServerInstance.getInstance();
    reference = firebaseServerInstance.getReference().child("Users");
    auth = firebaseServerInstance.getAuth();
    setUserAuthentication();
  }

  private void setReferences(){
    userMainDataReference = userReference.child("userData");
    gameStatisticsReference = userReference.child("GameStatistics");
    userFriendsReference = userReference.child("friends");
  }

  public void setUserAuthentication() {
    mUser = firebaseServerInstance.getAuth().getCurrentUser();
    if (mUser != null) {
      mUserUid = mUser.getUid();
      userReference = reference.child(mUserUid);
      setReferences();
    } else {
      Log.d(TAG, "setUserAuthentication: null");
    }
  }

  public String getUserUid(){
    return mUserUid;
  }


  public void setMainDataListener(){
    userMainDataObserver = RxFirebaseDatabase.observeSingleValueEvent
        (userMainDataReference, DataSnapshotMapper.of(UserMainData.class))
        .subscribe(
            userMainData -> userMainDataMutableLiveData.postValue(userMainData),
            throwable -> Log.d(TAG, "setMainDataListener: error")
        );
  }

  public void setGameStatictisListener(){
    gameStatisticsObserver = RxFirebaseDatabase.observeSingleValueEvent
        (gameStatisticsReference, DataSnapshotMapper.of(GameStatistics.class))
        .subscribe(
            gameStatistics -> gameStatisticsMutableLiveData.postValue(gameStatistics),
            throwable -> Log.d(TAG, "setGameStatisticsListener: error")
        );
    gameStatisticsObserver.dispose();
  }

  public void setFriendsListener(){
    userFriendsObserver = RxFirebaseDatabase.observeSingleValueEvent
        (userFriendsReference, DataSnapshotMapper.listOf(FriendItem.class))
        .subscribe(
            friendItems -> userFriendsMutableLiveData.postValue(friendItems),
            throwable -> Log.d(TAG, "setFriendsListener: error")
        );
    userFriendsObserver.dispose();
  }

  public MutableLiveData<UserMainData> getUserMainDataMutableLiveData(){
    return userMainDataMutableLiveData;
  }

  public MutableLiveData<GameStatistics> getGameStatisticsMutableLiveData(){
    return gameStatisticsMutableLiveData;
  }

  public void setGameStatistics(GameStatistics gameStatistics) {
    gameStatisticsReference.setValue(gameStatistics);
  }

  public void setUserMainData(UserMainData userMainData) {
    userMainDataReference.setValue(userMainData);
  }

  public MutableLiveData<List<FriendItem>> getUserFriends() {
    return userFriendsMutableLiveData;
  }

  public FirebaseAuth getAuth() {
    return auth;
  }

  public void setLearningProgress(String type, int progress) {

  }
}
