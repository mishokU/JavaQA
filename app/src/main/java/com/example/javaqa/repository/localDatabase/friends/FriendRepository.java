package com.example.javaqa.repository.localDatabase.friends;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.javaqa.models.FriendItem;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class FriendRepository {

  private LiveData<List<FriendItem>> friends;
  private FriendDao friendDao;

  public FriendRepository(Application application) {
    FriendsDatabase friendsDatabase = FriendsDatabase.getInstance(application);
    friendDao = friendsDatabase.friendDao();
    friends = friendDao.getAllFriends();
  }

  public void insert(FriendItem friend){
    Completable
        .fromAction(() -> friendDao.insert(friend))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
  }

  public void update(FriendItem friend){
    friendDao.update(friend);
  }

  public void delete(FriendItem friend){
    friendDao.delete(friend);
  }

  public void insertAll(List<FriendItem> friends){
    friendDao.insertAll(friends);
  }

  public LiveData<List<FriendItem>> getFriends(){
    return friends;
  }

}
