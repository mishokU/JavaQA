package com.example.javaqa.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.ui.activities.new_game_activities.ChoiceWeaponAlertDialog;
import com.example.javaqa.ui.adapters.FriendMainListAdapter;
import com.example.javaqa.models.FriendItem;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {

  private RecyclerView recyclerView;
  private MaterialButton mRandomPlayer;
  private MaterialButton mFacebookFriends;
  private ImageView mDeleteFriends;
  private Toolbar toolbar;

  private RecyclerView.LayoutManager layoutManager;
  private RecyclerView.Adapter friendMainListAdapter;
  private ArrayList<FriendItem> friendItemList;
  private SwipeRefreshLayout swipeRefreshLayout;

  private DatabaseReference databaseReference;
  private DatabaseReference userReference;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_game);
    ButterKnife.bind(this);

    findViews();
    setUpToolbar();
    setClicks();
    initFirebase();
    setUpFriendListAdapter();
    setUpSwipeRefresh();
  }

  private void initFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    databaseReference = FirebaseDatabase.getInstance().getReference();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    userReference = databaseReference.child("Users").child(userId).child("friends");
    firebaseStorage = FirebaseStorage.getInstance();

    loadFriends();
  }

  private void loadFriends() {
    userReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //Get reference of all users
        String userReference = (String)dataSnapshot.child("userRef").getValue();
        loadCurrentUserInformation(userReference);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void loadCurrentUserInformation(String userReference) {
    DatabaseReference currentUserRef = databaseReference.child("Users").child(userReference).child("userData");

    currentUserRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String currentUserName = (String)dataSnapshot.child("username").getValue();
        String currentUserImage = (String)dataSnapshot.child("imageURL").getValue();

        FriendItem friendItem = new FriendItem(currentUserName, currentUserImage);

        friendItemList.add(friendItem);
        friendMainListAdapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      friendItemList.clear();
      loadFriends();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void findViews() {
    toolbar = findViewById(R.id.toolbar);
    recyclerView = findViewById(R.id.friend_recycler_view_list);
    mRandomPlayer = findViewById(R.id.random_player_button);
    mFacebookFriends = findViewById(R.id.facebook_player_button);
    mDeleteFriends = findViewById(R.id.change_friend_list);
    swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("New Game");
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

  private void setClicks() {
    mRandomPlayer.setOnClickListener(this);
    mFacebookFriends.setOnClickListener(this);
    mDeleteFriends.setOnClickListener(this);
  }

  private void setUpFriendListAdapter() {
    layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    friendItemList = new ArrayList<>();
    friendMainListAdapter = new FriendMainListAdapter(friendItemList);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(friendMainListAdapter);

    ((FriendMainListAdapter) friendMainListAdapter).setOnItemClickListener(position ->
        Toast.makeText(this,"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

  @Override
  public void onClick(View view) {
    if(view == mRandomPlayer) {
      ChoiceWeaponAlertDialog choiceWeaponAlertDialog = new ChoiceWeaponAlertDialog(this);
      choiceWeaponAlertDialog.show(getSupportFragmentManager(), "");
      startGameWithRandomPlayer();
    } else if(view == mFacebookFriends) {
      startGameWithFacebookFriend();
    } else if(view == mDeleteFriends) {
      deleteFriends();
    }
  }

  private void deleteFriends() {

  }

  private void startGameWithFacebookFriend() {
    Toast.makeText(this,"facebook",Toast.LENGTH_SHORT).show();
  }

  private void startGameWithRandomPlayer() {
    Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
  }

  public void fight(String weapon) {
    Toast.makeText(this,"weapon" + weapon, Toast.LENGTH_SHORT).show();
  }
}
