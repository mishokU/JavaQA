package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.javaqa.models.game.GameQuestionsModel;
import com.example.javaqa.models.game.GameRoomModel;
import com.example.javaqa.repository.remoteDatabase.firebase.GameRoomsFirebase;

import java.util.List;

public class GameViewModel extends AndroidViewModel {

  private LiveData<List<GameQuestionsModel>> questions;
  private LiveData<GameRoomModel> gameRoom;
  private GameRoomsFirebase gameRoomsFirebase;

  public GameViewModel(@NonNull Application application) {
    super(application);
    gameRoomsFirebase = new GameRoomsFirebase();
  }

  public void setGameThemePath(String path){
    gameRoomsFirebase.setThemePath(path);
  }

  public LiveData<GameRoomModel> getRoom() {
    return gameRoomsFirebase.getRoom();
  }

  public void setUserReference(String mUserReference) {
    gameRoomsFirebase.setUserReference(mUserReference);
    gameRoomsFirebase.initRooms();
  }
}
