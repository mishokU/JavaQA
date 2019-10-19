package com.example.javaqa.repository.remoteDatabase.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseServerInstance {

  private static FirebaseServerInstance instance;
  private static DatabaseReference reference;
  private static FirebaseDatabase database;
  private static FirebaseStorage storage;
  private static FirebaseAuth authInstance;

  public static FirebaseServerInstance getInstance() {
    if(instance == null) {
      instance = new FirebaseServerInstance();
      database = FirebaseDatabase.getInstance();
      storage = FirebaseStorage.getInstance();
      authInstance = FirebaseAuth.getInstance();

      database.setPersistenceEnabled(true);
      reference = database.getReference();
    }
    return instance;
  }

  public DatabaseReference getReference(){
    return reference;
  }

  public FirebaseStorage getStorage(){
    return storage;
  }

  public FirebaseAuth getAuth(){
    return authInstance;
  }

}
