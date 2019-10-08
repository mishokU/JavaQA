package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.CommentsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CommentsForPostsFirebase {

  private FirebaseServerInstance firebaseServerInstance;
  private MutableLiveData<List<CommentsModel>> mComments;
  private DatabaseReference commentsReference;
  private DatabaseReference reference;
  private Boolean loadError;
  private String path;
  private Disposable loadComments;

  public CommentsForPostsFirebase(){
    firebaseServerInstance = FirebaseServerInstance.getInstance();
    reference = firebaseServerInstance.getReference();
    mComments = new MutableLiveData<>();
  }

  public void setPath(String path){
    this.path = path;
    Log.d(TAG, "setPath: "+ path);
    commentsReference = reference.child("PostData").child(path).child("CommentsModel");
    commentsReference.keepSynced(false);
    loadComments();
  }

  public void loadComments(){
    commentsReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<CommentsModel> commentsModels = new ArrayList<>();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
          commentsModels.add(ds.getValue(CommentsModel.class));
        }
        mComments.postValue(commentsModels);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

    /*loadComments = RxFirebaseDatabase.observeSingleValueEvent(commentsReference,
        DataSnapshotMapper.listOf(CommentsModel.class))
        .subscribe(commentsModels -> {
          mComments.postValue(commentsModels);
        });*/
  }

  public Boolean loadRrror(){
    return loadError;
  }

  public MutableLiveData<List<CommentsModel>> getComments(){
    return mComments;
  }

  public void insert(CommentsModel comment) {
    String unique_key = commentsReference.push().getKey();
    assert unique_key != null;
    commentsReference.child(unique_key).setValue(comment);
    loadComments();
  }

  public void delete(CommentsModel comment) {

  }

  public void setLike(boolean liked) {
    commentsReference.child("rating").setValue(liked);
  }
}
