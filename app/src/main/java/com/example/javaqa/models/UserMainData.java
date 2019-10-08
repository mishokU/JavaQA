package com.example.javaqa.models;

public class UserMainData  {

  public UserMainData(String email, String imageURL, String level, String password, String username) {
    this.email = email;
    this.imageURL = imageURL;
    this.level = level;
    this.password = password;
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String email;
  private String imageURL;
  private String level;
  private String password;
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public UserMainData(){

  }




}
