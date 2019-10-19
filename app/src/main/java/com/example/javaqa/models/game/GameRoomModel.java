package com.example.javaqa.models.game;

import java.util.HashMap;

public class GameRoomModel  {

  private HashMap<String,String> Questions;
  private HashMap<String,String> FirstPlayer;
  private HashMap<String,String> SecondPlayer;

  public GameRoomModel(){}

  public GameRoomModel(HashMap<String, String> questions,
                       HashMap<String, String> firstPlayer,
                       HashMap<String, String> secondPlayer) {
    Questions = questions;
    FirstPlayer = firstPlayer;
    SecondPlayer = secondPlayer;
  }

  public HashMap<String, String> getQuestions() {
    return Questions;
  }

  public void setQuestions(HashMap<String, String> questions) {
    Questions = questions;
  }

  public HashMap<String, String> getFirstPlayer() {
    return FirstPlayer;
  }

  public void setFirstPlayer(HashMap<String, String> firstPlayer) {
    FirstPlayer = firstPlayer;
  }

  public HashMap<String, String> getSecondPlayer() {
    return SecondPlayer;
  }

  public void setSecondPlayer(HashMap<String, String> secondPlayer) {
    SecondPlayer = secondPlayer;
  }
}
