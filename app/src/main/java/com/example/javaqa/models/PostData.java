package com.example.javaqa.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostData implements Serializable {

  private String title;
  private String hashtags;
  private String userUrl;
  private String userName;
  private String userImageUrl;
  private String type;
  private String publicationTime;
  private String description;
  private String key;

  private int rating;
  private int comments;
  private int countOfViews;

  @Ignore
  public PostData() {}

  public PostData(String title,
                  String description,
                  String hashtags,
                  String userUrl,
                  String userName,
                  String userImageUrl,
                  int rating,
                  String publicationTime,
                  int countOfViews,
                  int comments,
                  String type,
                  String key) {

    this.userUrl = userUrl;
    this.title = title;
    this.hashtags = hashtags;
    this.userName = userName;
    this.userImageUrl = userImageUrl;
    this.comments = comments;
    this.type = type;
    this.publicationTime = publicationTime;
    this.countOfViews = countOfViews;
    this.description = description;
    this.rating = rating;
    this.key = key;
  }

  public void setUserUrl(String userUrl) {
      this.userUrl = userUrl;
    }

  public int getComments(){
    return comments;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }

  public String getUserUrl() {
    return userUrl;
  }

  public String getPublicationTime() {
    return publicationTime;
  }

  public void setPublicationTime(String publicationTime) {
    this.publicationTime = publicationTime;
  }

  public int getCountOfViews() {
    return countOfViews;
  }

  public void setCountOfViews(int countOfViews) {
    this.countOfViews += countOfViews;
  }

  public String getUserData() {
    return userName;
  }

  public String getUserName(){
    return userName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHashtags() {
    return hashtags;
  }

  public void setHashtags(String hashtags) {
    this.hashtags = hashtags;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating += rating;
  }

  public String getUserImageUrl() {
    return userImageUrl;
  }

  public void setUserImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey(){ return key;}

}
