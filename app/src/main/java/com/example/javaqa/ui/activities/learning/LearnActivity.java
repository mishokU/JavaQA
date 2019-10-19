package com.example.javaqa.ui.activities.learning;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearnActivity extends AppCompatActivity {

  @BindView(R.id.java_core_linear_layout) LinearLayout mJavaCoreItemList;
  @BindView(R.id.version_control_systems_linear_layout) LinearLayout mVCSItemList;
  @BindView(R.id.build_tools_linear_layout) LinearLayout mBuildToolsItemList;
  @BindView(R.id.data_base_linear_layout) LinearLayout mDatabasesItemList;
  @BindView(R.id.learning_toolbar) Toolbar mToolbar;

  private LaunchActivityHelper launchActivityHelper;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn);
    ButterKnife.bind(this);

    launchActivityHelper = LaunchActivityHelper.getInstance();

    setToolbar();
    setOnClicks();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate((R.menu.learning_main_menu),menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.search){
      showSearchLine();
    }
    return super.onOptionsItemSelected(item);
  }

  private void showSearchLine() {

  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Learning");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void setOnClicks() {
    for(int i = 0; i < mJavaCoreItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mJavaCoreItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> launchActivityHelper.JavaCoreIndexingActivityHelper(this,index));
    }
    for(int i = 0; i < mVCSItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mVCSItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> launchActivityHelper.VCSActivityHelper(this,index));
    }

    for(int i = 0; i < mBuildToolsItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mBuildToolsItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> launchActivityHelper.BuildToolsActivityHelper(this,index));
    }

    for(int i = 0; i < mDatabasesItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mDatabasesItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> launchActivityHelper.DataBasesActivityHelper(this,index));
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

}
