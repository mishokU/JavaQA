package com.example.javaqa.repository.remoteDatabase.firebase;

import android.service.autofill.UserData;

import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.game.GameStatusModel;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;

public class GameStatusesFirebase {

  private FirebaseServerInstance firebaseServerInstance;
  private DatabaseReference reference;
  private DatabaseReference gameStatusesRef;
  private UserDataFirebase mUserDataFirebase;

  private DatabaseReference yourGamesRef;
  private DatabaseReference waitingGamesRef;
  private DatabaseReference finishedGamesRef;

  private MutableLiveData<List<GameStatusModel>> yourGames = new MutableLiveData<>();
  private MutableLiveData<List<GameStatusModel>> waitingGames = new MutableLiveData<>();
  private MutableLiveData<List<GameStatusModel>> finishedGames = new MutableLiveData<>();

  private Disposable yourObserver;
  private Disposable waitingObserver;
  private Disposable finishedObserver;

  public GameStatusesFirebase(){
    firebaseServerInstance = FirebaseServerInstance.getInstance();
    reference = firebaseServerInstance.getReference().child("Users");
    mUserDataFirebase = new UserDataFirebase();
    gameStatusesRef = reference.child(mUserDataFirebase.getUserUid()).child("gameStatistics");
    setGamesPath();
  }

  private void setGamesPath() {
    yourGamesRef = gameStatusesRef.child("yourTurn");
    waitingGamesRef = gameStatusesRef.child("waiting");
    finishedGamesRef = gameStatusesRef.child("finished");
  }

  public void loadYourTurns(){
    yourObserver = RxFirebaseDatabase.observeSingleValueEvent(yourGamesRef, DataSnapshotMapper.listOf(GameStatusModel.class))
        .subscribe(
            gameStatistics -> yourGames.postValue(gameStatistics)
        );
    yourObserver.dispose();
  }

  public void loadWaitingGames(){
    waitingObserver = RxFirebaseDatabase.observeSingleValueEvent(waitingGamesRef, DataSnapshotMapper.listOf(GameStatusModel.class))
        .subscribe(
            gameStatistics -> waitingGames.postValue(gameStatistics)
        );
    waitingObserver.dispose();
  }

  public void loadFinishedGames(){
    finishedObserver = RxFirebaseDatabase.observeSingleValueEvent(finishedGamesRef, DataSnapshotMapper.listOf(GameStatusModel.class))
        .subscribe(
            gameStatistics -> finishedGames.postValue(gameStatistics)
        );
    finishedObserver.dispose();
  }

  public MutableLiveData<List<GameStatusModel>> getYourGames(){
    return yourGames;
  }

  public MutableLiveData<List<GameStatusModel>> getWaitingGames(){
    return waitingGames;
  }

  public MutableLiveData<List<GameStatusModel>> getFinishedGames(){
    return finishedGames;
  }

}
