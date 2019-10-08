package com.example.javaqa.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "table_friends")
public class FriendItem implements Serializable {

  @PrimaryKey(autoGenerate = true)
  private int id;

  private String imageUrl;
  private boolean mOnline;
  private boolean switcher = true;
  private String mUserName;

  public FriendItem(){

  }

  public FriendItem(String currentUserName, String currentUserImage) {
    this.mUserName = currentUserName;
    this.imageUrl = currentUserImage;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
