package com.example.javaqa.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.adapters.FriendMainListAdapter;
import com.example.javaqa.items.FriendItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {

  private RecyclerView recyclerView;
  private RelativeLayout mRandomPlayer;
  private RelativeLayout mFacebookFriends;
  private RelativeLayout mInviteFriend;
  private ImageView mDeleteFriends;
  private Toolbar toolbar;

  private RecyclerView.LayoutManager layoutManager;
  private RecyclerView.Adapter friendMainListAdapter;
  private ArrayList<FriendItem> friendItemList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.new_game_fragment);
    ButterKnife.bind(this);

    findViews();
    setUpToolbar();
    setClicks();
    setUpFriendListAdapter();
  }

  private void findViews() {
    toolbar = findViewById(R.id.toolbar);
    recyclerView = findViewById(R.id.friend_recycler_view_list);
    mRandomPlayer = findViewById(R.id.random_player_button);
    mFacebookFriends = findViewById(R.id.facebook_player_button);
    mInviteFriend = findViewById(R.id.invite_new_player_button);
    mDeleteFriends = findViewById(R.id.change_friend_list);
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Q & A");
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
    mInviteFriend.setOnClickListener(this);
    mDeleteFriends.setOnClickListener(this);
  }

  private void setUpFriendListAdapter() {
    layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    friendItemList = new ArrayList<>();
    friendMainListAdapter = new FriendMainListAdapter(friendItemList);

    friendItemList.add(new FriendItem());
    friendItemList.add(new FriendItem());

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(friendMainListAdapter);

    ((FriendMainListAdapter) friendMainListAdapter).setOnItemClickListener(position ->
        Toast.makeText(this,"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

  @Override
  public void onClick(View view) {
    if(view == mRandomPlayer) {
      startGameWithRandomPlayer();
    } else if(view == mFacebookFriends) {
      startGameWithFacebookFriend();
    } else if(view == mInviteFriend) {
      openSocialMediaPrograms();
    } else if(view == mDeleteFriends) {
      deleteFriends();
    }
  }

  private void deleteFriends() {
    Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
  }

  private void openSocialMediaPrograms() {
    Toast.makeText(this,"open",Toast.LENGTH_SHORT).show();
  }

  private void startGameWithFacebookFriend() {
    Toast.makeText(this,"facebook",Toast.LENGTH_SHORT).show();
  }

  private void startGameWithRandomPlayer() {
    Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
  }
}
