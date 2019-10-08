package com.example.javaqa.repository.remoteDatabase.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseServerInstance {

  private static FirebaseServerInstance instance;
  private static DatabaseReference reference;
  private static FirebaseDatabase database;

  public static FirebaseServerInstance getInstance() {
    if(instance == null) {
      instance = new FirebaseServerInstance();
      database = FirebaseDatabase.getInstance();
      database.setPersistenceEnabled(true);
      reference = database.getReference();
    }
    return instance;
  }

  public DatabaseReference getReference(){
    return reference;
  }

}
