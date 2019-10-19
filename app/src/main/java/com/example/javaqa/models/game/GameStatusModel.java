package com.example.javaqa.models.game;

public class GameStatusModel {

  private String username;
  private String userImage;
  private String gameCategory;
  private String gameTime;
  private String type;

  public GameStatusModel(){}

  public GameStatusModel(String username, String userImage, String gameCategory, String gameTime) {
    this.username = username;
    this.userImage = userImage;
    this.gameCategory = gameCategory;
    this.gameTime = gameTime;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public String getGameCategory() {
    return gameCategory;
  }

  public void setGameCategory(String gameCategory) {
    this.gameCategory = gameCategory;
  }

  public String getGameTime() {
    return gameTime;
  }

  public void setGameTime(String gameTime) {
    this.gameTime = gameTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
