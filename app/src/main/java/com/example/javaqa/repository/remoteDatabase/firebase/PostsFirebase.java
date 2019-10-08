package com.example.javaqa.repository.remoteDatabase.firebase;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.PostData;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostsFirebase  {

  private FirebaseServerInstance firebaseServerInstance;
  private DatabaseReference reference;
  private Disposable loadPostsDisposable;
  private MutableLiveData<List<PostData>> posts = new MutableLiveData<>();

  public PostsFirebase(){
    firebaseServerInstance = FirebaseServerInstance.getInstance();
    reference = firebaseServerInstance.getReference().child("PostData");
    reference.keepSynced(true);
  }

  public void update(PostData postData) {
    reference.child(postData.getKey()).child("rating").setValue(postData.getRating());
    reference.child(postData.getKey()).child("countOfViews").setValue(postData.getRating());
    reference.child(postData.getKey()).child("comments").setValue(postData.getComments());
  }

  public void insert(PostData postData){
    String unique_key = reference.push().getKey();
    assert unique_key != null;
    postData.setKey(unique_key);
    reference.child(unique_key).setValue(postData);
    loadPosts();
  }

  public void delete(PostData postData){

  }

  public String getKey(){
    return reference.getKey();
  }

  public void loadPosts(){
    loadPostsDisposable = RxFirebaseDatabase.observeSingleValueEvent(reference, DataSnapshotMapper.listOf(PostData.class))
        .subscribe(
            postsData -> posts.postValue(postsData),
            throwable -> Log.d(TAG, "loadPosts: error while loading ")
        );
  }

  public MutableLiveData<List<PostData>> getPosts(){
    return posts;
  }
}
