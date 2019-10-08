package com.example.javaqa.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.javaqa.models.FriendItem;
import com.example.javaqa.repository.mainRepositories.FriendsMainRepository;

import java.util.List;

public class FriendsViewModel extends AndroidViewModel {

  private FriendsMainRepository friendsMainRepository;

  public FriendsViewModel(@NonNull Application application) {
    super(application);
    friendsMainRepository = FriendsMainRepository.getInstance(application);
  }

  public LiveData<List<FriendItem>> getFriends(){
    return friendsMainRepository.getFriends();
  }

}
