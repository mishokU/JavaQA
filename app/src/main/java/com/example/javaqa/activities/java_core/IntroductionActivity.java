package com.example.javaqa.activities.java_core;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.example.javaqa.ActivityUtils.AlertDialogHelper;
import com.example.javaqa.R;
import com.example.javaqa.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.fragments.java_core.introduction.CompileAndBenefitsOfJava;
import com.example.javaqa.fragments.java_core.introduction.IntroductionFragment;
import com.example.javaqa.fragments.java_core.introduction.QuestionToIntroductionFragment;
import com.example.javaqa.fragments.java_core.introduction.QueueToJavaCompile;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionActivity extends AppCompatActivity {

  @BindView(R.id.intro_toolbar) Toolbar toolbar;
  @BindView(R.id.intro_view_pager) ViewPager viewPager;
  @BindView(R.id.intro_tab_layout) TabLayout tabLayout;
  @BindView(R.id.intro_nested_scroll_view) NestedScrollView nestedScrollView;

  private MainActivityViewPagerAdapter mViewPagerAdapter;
  private AlertDialogHelper alertDialogHelper;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.javacore_introduction_activity);
    ButterKnife.bind(this);
    setUpToolbar();
    setUpViewPager();
  }

  private void setUpViewPager() {
    nestedScrollView.setFillViewport(true);

    mViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

    mViewPagerAdapter.addFragment(new IntroductionFragment(),"");
    mViewPagerAdapter.addFragment(new QuestionToIntroductionFragment(),"");
    mViewPagerAdapter.addFragment(new CompileAndBenefitsOfJava(),"");
    mViewPagerAdapter.addFragment(new QueueToJavaCompile(),"");

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
    switch (item.getItemId()){
      case R.id.how_to_pass:
        alertDialogHelper = new AlertDialogHelper("Задержите элемент и передвинте его вниз или вверх, чтобы пройти задачку",this);
        alertDialogHelper.show(getSupportFragmentManager(),"");
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
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
}
