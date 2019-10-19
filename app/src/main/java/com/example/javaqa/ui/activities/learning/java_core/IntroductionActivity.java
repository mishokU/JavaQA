package com.example.javaqa.ui.activities.learning.java_core;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.javaqa.ActivityUtils.AlertDialogHelper;
import com.example.javaqa.R;
import com.example.javaqa.ui.activities.learning.base.BaseLearnActivity;
import com.example.javaqa.ui.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.ui.fragments.java_core.introduction.CompileAndBenefitsOfJava;
import com.example.javaqa.ui.fragments.java_core.introduction.IntroductionFragment;
import com.example.javaqa.ui.fragments.java_core.introduction.QuestionToIntroductionLearnFragment;
import com.example.javaqa.ui.fragments.java_core.introduction.QueueToJavaCompile;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionActivity extends BaseLearnActivity {

  @BindView(R.id.intro_toolbar) Toolbar toolbar;
  @BindView(R.id.intro_view_pager) ViewPager viewPager;
  @BindView(R.id.intro_tab_layout) TabLayout tabLayout;
  @BindView(R.id.intro_nested_scroll_view) NestedScrollView nestedScrollView;

  private IntroductionFragment introductionFragment;
  private QuestionToIntroductionLearnFragment questionToIntroductionFragment;
  private CompileAndBenefitsOfJava compileAndBenefitsOfJava;
  private QueueToJavaCompile queueToJavaCompile;

  private MainActivityViewPagerAdapter mViewPagerAdapter;
  private AlertDialogHelper alertDialogHelper;
  private UserViewModel mUserViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.javacore_introduction_activity);
    ButterKnife.bind(this);

    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    setUpToolbar();
    initFragments();
    setUpViewPager();
    setProgressListener();
  }

  private void setProgressListener() {

  }

  private void initFragments() {
    introductionFragment = new IntroductionFragment();
    questionToIntroductionFragment= new QuestionToIntroductionLearnFragment();
    compileAndBenefitsOfJava = new CompileAndBenefitsOfJava();
    queueToJavaCompile = new QueueToJavaCompile();
  }

  private void setUpViewPager() {
    nestedScrollView.setFillViewport(true);

    mViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

    mViewPagerAdapter.addFragment(introductionFragment,"");
    mViewPagerAdapter.addFragment(questionToIntroductionFragment,"");
    mViewPagerAdapter.addFragment(compileAndBenefitsOfJava,"");
    mViewPagerAdapter.addFragment(queueToJavaCompile,"");

    viewPager.setAdapter(mViewPagerAdapter);

    tabLayout.setupWithViewPager(viewPager);

    for(int i = 0; i < tabLayout.getTabCount(); i++){
      if(i%2 == 0) {
        tabLayout.getTabAt(i).setCustomView(R.layout.tabitem_theory);
      } else {
        tabLayout.getTabAt(i).setCustomView(R.layout.tabitem_question);
      }
    }
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Introduction");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate((R.menu.learning_menu),menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.how_to_pass) {
      alertDialogHelper = new AlertDialogHelper("Задержите элемент и передвинте его вниз или вверх, чтобы пройти задачку", this);
      alertDialogHelper.show(getSupportFragmentManager(), "");
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public ViewPager getViewPager(){
    return viewPager;
  }

  public TabLayout getTabLayout(){
    return tabLayout;
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

  @Override
  public void onProgressSend(String type, int progress) {
    mUserViewModel.setLearningProgress(type, progress);
  }
}
