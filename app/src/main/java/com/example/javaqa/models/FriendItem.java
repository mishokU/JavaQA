package com.example.javaqa.models;

public class FriendItem {

  private String imageUrl;
  private boolean mOnline;
  private boolean switcher = true;
  private String mUserName;

  public FriendItem(String currentUserName, String currentUserImage) {
    this.mUserName = currentUserName;
    this.imageUrl = currentUserImage;
  }

  public boolean isSwitcher() {
    return switcher;
  }

  public void setSwitcher(boolean switcher) {
    this.switcher = switcher;
  }

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
