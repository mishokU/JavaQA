package com.example.javaqa.DataBase.Authentication.Server;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Transmitter  {

  private FirebaseDatabase database;
  private DatabaseReference databaseReference;
  private String path;

  public Transmitter(String path){
    this.path = path;
  }

  public void initReference(){
    database = FirebaseDatabase.getInstance();
    databaseReference = database.getReference(path);
  }

  public void updateDatabase(){
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }
}
