package com.example.javaqa.DataBase.Authentication.Server;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class Receiver  {

  private DatabaseReference databaseReference;
  private FirebaseUser firebaseUser;
  private FirebaseAuth firebaseAuth;
  private FirebaseStorage firebaseStorage;
  private Boolean withStorage;
  private String userId;
  private String path;

  public Receiver(String path, Boolean withStorage){
    this.path = path;
    this.withStorage = withStorage;
  }

  public void initReceiver(){
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    databaseReference = FirebaseDatabase.getInstance().getReference(path + "/" + userId);
    if(withStorage) {
      firebaseStorage = FirebaseStorage.getInstance();
    }
  }

  public void receiveData(String... data){
    if(data == null) {
      //Set observabale
    }

    databaseReference.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });


  }

  public void sendData(String... data){

    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  public void sendImage(ImageView imageView) {

  }
}
