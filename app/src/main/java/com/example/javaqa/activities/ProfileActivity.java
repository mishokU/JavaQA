package com.example.javaqa.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;
import com.example.javaqa.activities.profile_activities.CreatePostActivity;
import com.example.javaqa.activities.profile_activities.ProfileEditActivity;
import com.example.javaqa.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.fragments.profile_fragments.ProfileConversationFragment;
import com.example.javaqa.fragments.profile_fragments.ProfilePostFragment;
import com.example.javaqa.fragments.profile_fragments.ProfileStatisticFragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.profile_tablayout) TabLayout tabLayout;
  @BindView(R.id.profile_user_level) TextView userLevel;
  @BindView(R.id.profile_user_image) CircleImageView profileUserImage;
  @BindView(R.id.profile_view_pager) ViewPager viewPager;
  @BindView(R.id.profile_nested_scroll_view) NestedScrollView nestedScrollView;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.experience_progress_bar) ProgressBar progressBar;
  @BindView(R.id.fab) FloatingActionButton fab;

  private MainActivityViewPagerAdapter viewPagerAdapter;
  private LaunchActivityHelper launchActivityHelper;

  private DatabaseReference databaseReference;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;

  private SharedPreferences sPref;;
  public static final String DATA_NAME = "USERDATA";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);

    launchActivityHelper = new LaunchActivityHelper();

    initFirebase();
    setUpToolbar();
    setUpViewPagerAdapter();
  }

  private void initFirebase() {

    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("userData");
    firebaseStorage = FirebaseStorage.getInstance();

    loadUserData();
  }

  private void loadUserData(){
    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String userName = dataSnapshot.child("username").getValue().toString();
        String level = dataSnapshot.child("level").getValue().toString();
        String imageURL = dataSnapshot.child("imageURL").getValue().toString();
        //String levelProgress = dataSnapshot.child("progress").getValue().toString();
        level = "Уровень: " + level;

        updateFields(userName, level, imageURL);
        //progressBar.setProgress(Integer.parseInt(levelProgress));
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void updateFields(String userName, String level, String imageURL) {
    Objects.requireNonNull(getSupportActionBar()).setTitle(userName);
    toolbar.setTitle(userName);
    collapsingToolbarLayout.setTitle(userName);
    userLevel.setText(level);
    Picasso.get().load(imageURL).placeholder(R.drawable.mytest).into(profileUserImage);

    sPref = getPreferences(MODE_PRIVATE);
    SharedPreferences.Editor ed = sPref.edit();
    ed.putString("USERNAME", userName);
    ed.commit();
  }

  private void setUpViewPagerAdapter() {
    nestedScrollView.setFillViewport(true);

    viewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

    viewPagerAdapter.addFragment(new ProfilePostFragment(),"Posts");
    viewPagerAdapter.addFragment(new ProfileConversationFragment(), "Parley");
    viewPagerAdapter.addFragment(new ProfileStatisticFragment(),"Statistic");

    viewPager.setAdapter(viewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        if(position == 0) {
          fab.hide();
          fab.setImageResource(R.drawable.plus_math_16px);
          fab.setOnClickListener(view -> launchActivity(CreatePostActivity.class));
          fab.show();
        } else if(position == 1) {
          fab.hide();
          fab.setImageResource(R.drawable.pencil_16px);
          fab.show();
          fab.setOnClickListener(view ->  launchActivity(CreateConversationPostActivity.class));
        } else if(position == 2) {
          fab.hide();
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate((R.menu.profile_menu),menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.invite_friends:

        return true;
      case R.id.settings:

        return true;
      case R.id.share:

        return true;
      case R.id.edit_profile:
        launchActivityHelper.launchActivity(this, ProfileEditActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return true;
      case R.id.exit_from_account:
        exitFromAccount();
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void launchActivity(Class activity) {
    Intent intent = new Intent(this, activity);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

  private void exitFromAccount(){
    FirebaseAuth.getInstance().signOut();
    Intent intent = new Intent(this, StartActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
    finish();
  }
}
