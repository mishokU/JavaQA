package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.javaqa.models.FriendItem;

public class FriendsViewModel extends AndroidViewModel {

  private LiveData<PagedList<FriendItem>> mFriends;

  public FriendsViewModel(@NonNull Application application) {
    super(application);
  }

}
