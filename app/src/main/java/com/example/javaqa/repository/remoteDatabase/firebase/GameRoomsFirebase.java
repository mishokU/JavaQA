package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.javaqa.models.game.GameQuestionsModel;
import com.example.javaqa.models.game.GameRoomModel;
import com.example.javaqa.models.game.GameUserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GameRoomsFirebase {

  private LiveData<GameRoomModel> gameRoomModel;
  private FirebaseServerInstance instance;
  private DatabaseReference gamesReference;
  private DatabaseReference activeGamesReference;
  private DatabaseReference soloGamesReference;
  private DatabaseReference userReference;

  private QuestionThemesFirebase questionThemesFirebase;

  private boolean hasRooms;

  private Disposable soloRooms;
  private Disposable activeRooms;

  private String mUserReference;

  public GameRoomsFirebase(){
    instance = FirebaseServerInstance.getInstance();
    gamesReference = instance.getReference().child("Games");
    activeGamesReference = gamesReference.child("ActiveGames");
    soloGamesReference = gamesReference.child("SoloGames");

    questionThemesFirebase = new QuestionThemesFirebase();
  }

  private boolean hasSoloRoom(){
    soloRooms = RxFirebaseDatabase.observeSingleValueEvent
        (soloGamesReference, DataSnapshot::hasChildren)
        .subscribe(aBoolean -> hasRooms = aBoolean, throwable -> {
          Log.d(TAG, "hasSoloRoom: error");
        });
    soloRooms.dispose();
    return hasRooms;
  }

  private void CreateRoom(){
    String unique_id = soloGamesReference.push().getKey();
    assert unique_id != null;

    GameUserModel gameUserModel = new GameUserModel();
    gameUserModel.setPlayerPath(mUserReference);

    soloGamesReference.child(unique_id).child("FirstPlayer").setValue(gameUserModel);

    questionThemesFirebase.getQuestions()
        .observeForever(gameQuestionsModels -> soloGamesReference
            .child(unique_id)
            .child("Questions")
            .setValue(gameQuestionsModels));

  }

  private void ConnectToRoom(){

  }

  public void setThemePath(String path){
    questionThemesFirebase.setQuestionThemeReference(path);
  }

  public void initRooms(){
    if(hasSoloRoom()){
      ConnectToRoom();
    } else {
      CreateRoom();
    }
  }

  public LiveData<GameRoomModel> getRoom() {
    return gameRoomModel;
  }

  public void setUserReference(String mUserReference) {
    this.mUserReference = mUserReference;
  }
}
