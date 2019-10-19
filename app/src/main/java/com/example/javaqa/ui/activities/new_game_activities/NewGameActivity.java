package com.example.javaqa.ui.activities.new_game_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.ui.activities.new_game_activities.ChoiceWeaponAlertDialog;
import com.example.javaqa.ui.activities.new_game_activities.StartNewGame;
import com.example.javaqa.ui.adapters.FriendMainListAdapter;
import com.example.javaqa.models.FriendItem;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {

  private RecyclerView recyclerView;
  private MaterialButton mRandomPlayer;
  private MaterialButton mFacebookFriends;
  private ImageView mDeleteFriends;
  private Toolbar toolbar;

  private RecyclerView.LayoutManager layoutManager;
  private FriendMainListAdapter friendMainListAdapter;
  private ArrayList<FriendItem> friendItemList;
  private SwipeRefreshLayout swipeRefreshLayout;

  private UserViewModel mUserViewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_game);
    ButterKnife.bind(this);

    findViews();
    setViewModels();
    setUpToolbar();
    setClicks();
    setUpFriendListAdapter();
    setUpSwipeRefresh();
  }

  private void setViewModels() {
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      friendItemList.clear();
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
    friendMainListAdapter = new FriendMainListAdapter();

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(friendMainListAdapter);

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

  public void launchActivity(Class toActivity,String weapon){
    Intent intent = new Intent(this,toActivity);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    intent.putExtra("weapon", weapon);
    startActivity(intent);
  }

  private void startGameWithFacebookFriend() {
    Toast.makeText(this,"facebook",Toast.LENGTH_SHORT).show();
  }

  private void startGameWithRandomPlayer() {
    Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
  }

  public void fight(String weapon) {
    Toast.makeText(this,"weapon" + weapon, Toast.LENGTH_SHORT).show();
    launchActivity(StartNewGame.class,weapon);
  }
}
