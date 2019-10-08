package com.example.javaqa.repository.mainRepositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.javaqa.models.FriendItem;
import com.example.javaqa.repository.localDatabase.friends.FriendRepository;
import com.example.javaqa.repository.remoteDatabase.firebase.FirebaseServerInstance;
import com.example.javaqa.repository.remoteDatabase.firebase.FriendsFirebase;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FriendsMainRepository {

  private static FriendsMainRepository instance;
  private static FriendRepository friendRepository;
  private static FriendsFirebase friendsFirebase;

  public static FriendsMainRepository getInstance(Application application) {
    if(instance == null) {
      instance = new FriendsMainRepository();
      friendRepository = new FriendRepository(application);

    }
    return instance;
  }

  public void insert(FriendItem friend){
    friendRepository.insert(friend);
    friendsFirebase.insert(friend);
  }

  public void delete(FriendItem friend){
    friendRepository.delete(friend);
    friendsFirebase.delete(friend);
  }

  public LiveData<List<FriendItem>> getFriends(){
    LiveData<List<FriendItem>> friends = friendsFirebase.getFriends();
    if(friends != null) {
      friendRepository.insertAll(friends.getValue());
      Log.d(TAG, "getFriends: friends from firebase is not null");
      return friends;
    } else {
      Log.d(TAG, "getFriends: friends from firebase is null");
      return friendRepository.getFriends();
    }
  }
}
