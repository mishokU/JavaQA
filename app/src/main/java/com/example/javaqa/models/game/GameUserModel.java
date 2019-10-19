package com.example.javaqa.models.game;

public class GameUserModel {

  private String score = "0";
  private String currentQuestion = "1";
  private String playerPath;

  public GameUserModel(){}

  public GameUserModel(String score, String currentQuestion, String playerPath) {
    this.score = score;
    this.currentQuestion = currentQuestion;
    this.playerPath = playerPath;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public String getCurrentQuestion() {
    return currentQuestion;
  }

  public void setCurrentQuestion(String currentQuestion) {
    this.currentQuestion = currentQuestion;
  }

  public String getPlayerPath() {
    return playerPath;
  }

  public void setPlayerPath(String playerPath) {
    this.playerPath = playerPath;
  }
}
