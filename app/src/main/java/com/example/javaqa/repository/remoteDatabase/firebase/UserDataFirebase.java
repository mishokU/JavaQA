package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

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
  private String mUserUid;

  private Disposable userMainDataObserver;
  private Disposable gameStatisticsObserver;
  private Disposable userFriendsObserver;

  private MutableLiveData<UserMainData> userMainDataMutableLiveData = new MutableLiveData<>();
  private MutableLiveData<GameStatistics> gameStatisticsMutableLiveData = new MutableLiveData<>();
  //For friends;

  private void init(){
    firebaseServerInstance = FirebaseServerInstance.getInstance();
    mUser = FirebaseAuth.getInstance().getCurrentUser();
    reference = firebaseServerInstance.getReference().child("Users");
  }

  public UserDataFirebase(){
    init();
    mUserUid = mUser.getUid();
    userReference = reference.child(mUserUid);
    setReferences();
  }

  public UserDataFirebase(String anotherUser){
    init();
    userReference = reference.child(anotherUser);
    setReferences();
  }

  public String getUserUid(){
    return mUserUid;
  }

  private void setReferences(){
    userMainDataReference = userReference.child("userData");
    gameStatisticsReference = userReference.child("GameStatistics");
    userFriendsReference = userReference.child("friends");
  }

  public void setMainDataListener(){
    userMainDataObserver = RxFirebaseDatabase.observeSingleValueEvent
        (userMainDataReference, DataSnapshotMapper.of(UserMainData.class))
        .subscribe(
            userMainData -> userMainDataMutableLiveData.postValue(userMainData)
        );
  }

  public void setGameStatictisListener(){
    gameStatisticsObserver = RxFirebaseDatabase.observeSingleValueEvent
        (gameStatisticsReference, DataSnapshotMapper.of(GameStatistics.class))
        .subscribe(
            gameStatistics -> gameStatisticsMutableLiveData.postValue(gameStatistics)
        );
  }

  public MutableLiveData<UserMainData> getUserMainDataMutableLiveData(){
    return userMainDataMutableLiveData;
  }

  public MutableLiveData<GameStatistics> getGameStatisticsMutableLiveData(){
    return gameStatisticsMutableLiveData;
  }

}
