package com.example.javaqa.models;

public class CommentsModel {

  private int rating;
  private String answer;
  private String time;
  private String userName;
  private String userImageUrl;

  public CommentsModel(){}

  public CommentsModel(int rating, String answer, String time, String userName, String userImageUrl) {
    this.rating = rating;
    this.answer = answer;
    this.time = time;
    this.userName = userName;
    this.userImageUrl = userImageUrl;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating += rating;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserImageUrl() {
    return userImageUrl;
  }

  public void setUserImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
  }



}
