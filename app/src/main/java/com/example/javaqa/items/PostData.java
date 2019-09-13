package com.example.javaqa.items;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class PostData implements Serializable {

  private String title;
  private String hashtags;
  private String comments;
  private String userUrl;
  private String userName;
  private String userImageUrl;
  private String type;
  private String publicationTime;
  private String countOfViews;
  private String description;
  private String rating;
  private String key;

  public PostData() {}

  public PostData(String title, String hashtags, String comments, String userUrl,
                  String type, String publicationTime, String countOfViews, String description, String rating, String key) {

    this.userUrl = userUrl;
    this.title = title;
    this.hashtags = hashtags;
    this.comments = comments;
    this.type = type;
    this.publicationTime = publicationTime;
    this.countOfViews = countOfViews;
    this.description = description;
    this.rating = rating;
    this.key = key;
  }

  public void setValues(PostData postData) {
    this.title = postData.getTitle();
    this.comments = postData.getComments();
    this.type = postData.getType();
    this.publicationTime = postData.getPublicationTime();
    this.countOfViews = postData.getCountOfViews();
    this.description = postData.getDescription();
    this.rating = postData.getRating();
    this.key = postData.getKey();
  }

  public String getComments(){
    return comments;
  }

  public void setComments(String comments) {
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

  public String getCountOfViews() {
    return countOfViews;
  }

  public void setCountOfViews(String countOfViews) {
    this.countOfViews = countOfViews;
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

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
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
