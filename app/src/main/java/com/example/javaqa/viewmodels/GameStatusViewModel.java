package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.game.GameStatusModel;
import com.example.javaqa.repository.remoteDatabase.firebase.GameStatusesFirebase;

import java.util.List;

public class GameStatusViewModel extends AndroidViewModel {

  private GameStatusesFirebase gameStatusesFirebase;
  private MutableLiveData<List<GameStatusModel>> yourGames;
  private MutableLiveData<List<GameStatusModel>> waitingGames;
  private MutableLiveData<List<GameStatusModel>> finishedGames;

  public GameStatusViewModel(@NonNull Application application) {
    super(application);
    gameStatusesFirebase = new GameStatusesFirebase();

    yourGames = new MutableLiveData<>();
    waitingGames = new MutableLiveData<>();
    finishedGames = new MutableLiveData<>();
  }

  public LiveData<List<GameStatusModel>> getYourGames(){
    gameStatusesFirebase.loadYourTurns();
    gameStatusesFirebase.getYourGames().observeForever(gameStatusModels -> {
      if(!gameStatusModels.isEmpty()){
        yourGames.postValue(gameStatusModels);
      }
    });
    return yourGames;
  }

  public LiveData<List<GameStatusModel>> getWaitingGames(){
    gameStatusesFirebase.loadWaitingGames();
    gameStatusesFirebase.getWaitingGames().observeForever(gameStatusModels -> {
      if(!gameStatusModels.isEmpty()) {
        waitingGames.postValue(gameStatusModels);
      }
    });
    return waitingGames;
  }

  public LiveData<List<GameStatusModel>> getFinishedGames(){
    gameStatusesFirebase.loadFinishedGames();
    gameStatusesFirebase.getFinishedGames().observeForever(gameStatusModels -> {
      if(!gameStatusModels.isEmpty()){
        finishedGames.postValue(gameStatusModels);
      }
    });
    return finishedGames;
  }

}
