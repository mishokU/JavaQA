package com.example.javaqa.items;

public class ConversationItem {

  private String title;
  private String hashtags;
  private String type;
  private String publication_time;
  private int count_of_views;
  private int rating;

  public int getComments() {
    return comments;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  private int comments;
  private String user_name;
  private String userImageUrl;

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

  public String getPublication_time() {
    return publication_time;
  }

  public void setPublication_time(String publication_time) {
    this.publication_time = publication_time;
  }

  public int getCount_of_views() {
    return count_of_views;
  }

  public void setCount_of_views(int count_of_views) {
    this.count_of_views = count_of_views;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
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


}
