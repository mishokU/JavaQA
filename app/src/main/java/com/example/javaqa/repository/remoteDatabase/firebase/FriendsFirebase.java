package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.FriendItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FriendsFirebase {

  private MutableLiveData<List<FriendItem>> friends;
  private FirebaseServerInstance database;
  private List<FriendItem> list = new ArrayList<>();
  private DatabaseReference reference;

  public FriendsFirebase(){
    database = FirebaseServerInstance.getInstance();
    //reference = database.getReference().child("Users").child(database.getUserId()).child("friends");
    friends = new MutableLiveData<>();
  }

  public void insert(FriendItem friendItem){
    String unique_key = reference.push().getKey();
    reference.child(unique_key).setValue(friendItem.getClass());
  }

  public void delete(FriendItem friendItem){
    //reference.child()
  }

  public void setFriendsListener(){
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()) {
          list.add(ds.getValue(FriendItem.class));
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.d(TAG, "onCancelled: friends load error");
      }
    });
  }

  public MutableLiveData<List<FriendItem>> getFriends(){
    return friends;
  }

}
