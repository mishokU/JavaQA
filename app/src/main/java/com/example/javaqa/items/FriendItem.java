package com.example.javaqa.items;

public class FriendItem {

  private String imageUrl;
  private boolean mOnline;
  private String mUserName = "Mishok";

  public String getUserName() {
    return mUserName;
  }

  public void setUserName(String mUserName) {
    this.mUserName = mUserName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public boolean isOnline() {
    return mOnline;
  }

  public void setOnline(boolean mOnline) {
    this.mOnline = mOnline;
  }

}
