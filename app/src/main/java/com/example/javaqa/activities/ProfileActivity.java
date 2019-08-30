package com.example.javaqa.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.example.javaqa.R;
import com.example.javaqa.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.fragments.ProfilePostFragment;
import com.example.javaqa.fragments.ProfileStatisticFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.profile_tablayout) TabLayout tabLayout;
  @BindView(R.id.profile_user_level) TextView userLevel;
  @BindView(R.id.profile_user_image) CircleImageView profileUserImage;
  @BindView(R.id.profile_view_pager) ViewPager viewPager;
  @BindView(R.id.profile_nested_scroll_view) NestedScrollView nestedScrollView;

  MainActivityViewPagerAdapter viewPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);
    setUpToolbar();
    setUpViewPagerAdapter();
  }

  private void setUpViewPagerAdapter() {
    nestedScrollView.setFillViewport(true);

    viewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

    viewPagerAdapter.addFragment(new ProfilePostFragment(),"Posts");
    viewPagerAdapter.addFragment(new ProfileStatisticFragment(),"Statistic");

    viewPager.setAdapter(viewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

  @Override
  public void onClick(View view) {

  }
}
