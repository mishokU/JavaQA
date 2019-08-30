package com.example.javaqa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.DataBase.Authentication.SignIn;
import com.example.javaqa.R;
import com.example.javaqa.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.fragments.ConversationFragment;
import com.example.javaqa.fragments.GamesStatusFragment;
import com.example.javaqa.fragments.LearnFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tabs) TabLayout mTabLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.appbar) AppBarLayout mAppBarLayout;
    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.main_nested_scroll_view) NestedScrollView mNestedScrollView;
    @BindView(R.id.fab) FloatingActionButton fab;

    private MainActivityViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpAppBar();
        setUpViewPagerAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate((R.menu.main_menu),menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.invite_friends:

                return true;
            case R.id.notifications:

                return true;
            case R.id.profile:
                    new LaunchActivityHelper(this,ProfileActivity.class,Intent.FLAG_ACTIVITY_NO_ANIMATION);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpViewPagerAdapter() {
        mNestedScrollView.setFillViewport(true);

        mViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragment(new LearnFragment(),"");
        mViewPagerAdapter.addFragment(new GamesStatusFragment(),"");
        mViewPagerAdapter.addFragment(new ConversationFragment(),"");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.student_48px);
        mTabLayout.getTabAt(1).setIcon(R.drawable.controller_48px);
        mTabLayout.getTabAt(2).setIcon(R.drawable.comments_48px);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String title;
                switch (position){
                    case 0:
                        title = "Обучение";
                        break;
                    case 1:
                        title = "Играть";
                        break;
                    case 2:
                        title = "Обсуждения";
                        break;
                    default:
                        title = "Q&A";
                }
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    fab.hide();
                } else if(position == 1) {
                    fab.hide();
                    fab.setImageResource(R.drawable.plus_math_16px);
                    fab.show();
                    fab.setOnClickListener(view -> launchActivity(NewGameActivity.class));
                } else if(position == 2) {
                    fab.hide();
                    fab.setImageResource(R.drawable.pencil_16px);
                    fab.show();
                    //Action
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void launchActivity(Class activity){
        Intent intent = new Intent(this,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void setUpAppBar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SignIn signIn = new SignIn(this);
        signIn.authentication();
        if(signIn.checkOnSignIn()) {
            sendToStart();
        }
    }

    private void sendToStart() {
        new LaunchActivityHelper(this, StartActivity.class,
            Intent.FLAG_ACTIVITY_NO_ANIMATION| Intent.FLAG_ACTIVITY_NO_HISTORY);
        finish();
    }
}
