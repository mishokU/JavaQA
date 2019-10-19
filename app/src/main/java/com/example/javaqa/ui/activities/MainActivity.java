package com.example.javaqa.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.FrameLayout;

import com.example.javaqa.R;
import com.example.javaqa.models.PostData;
import com.example.javaqa.ui.fragments.BottomSheetCommentsKeyBoard;
import com.example.javaqa.ui.fragments.ConversationFragment;
import com.example.javaqa.ui.fragments.MainBottomSheet;
import com.example.javaqa.ui.fragments.PostItemFragment;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ConversationFragment.PostItemListener {

    @BindView(R.id.bottom_app_bar) BottomAppBar mBottomAppBar;
    @BindView(R.id.create_post_fab) FloatingActionButton mCreatePost;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.main_frame) FrameLayout mMainFrame;

    private boolean isFabTapped;
    private FragmentTransaction fragmentTransaction;
    private ConversationFragment conversationFragment;
    private PostItemFragment postItemFragment;
    private BottomSheetCommentsKeyBoard bottomSheetCommentsKeyBoard;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpAppBar();

        conversationFragment = new ConversationFragment();
        postItemFragment = new PostItemFragment();
        handleFrame(conversationFragment);

        setViewModels();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mUserViewModel.getUserUid() == null) {
            sendToStart();
        }
    }

    //Rewrite animation
    private void handleFrame(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commitNow();
        getSupportFragmentManager().executePendingTransactions();
    }

    private void setViewModels() {
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate((R.menu.main_menu),menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void launchActivity(Class activity){
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    private void setUpAppBar() {
        setSupportActionBar(mBottomAppBar);
        mBottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.search:
                    return true;
                case R.id.notifications:
                    return true;
                case R.id.write_answer:
                    showBottomAnswerDialog();
                    return true;
                case R.id.write_message:
                    return true;
                case R.id.delete_post:
                    return true;
            }
            return false;
        });

        mBottomAppBar.setNavigationOnClickListener(view -> createBottomSheetFragment());
        mCreatePost.setOnClickListener(view -> {
            if(isFabTapped){
                removeFragment();
                mCreatePost.hide(listener);
            } else {
                launchActivity(CreateConversationPostActivity.class);
            }
        });
    }

    private void showBottomAnswerDialog() {
        bottomSheetCommentsKeyBoard = new BottomSheetCommentsKeyBoard();
        bottomSheetCommentsKeyBoard.setViewModel(postItemFragment.getCommentsViewModel());
        bottomSheetCommentsKeyBoard.setUserViewModel(mUserViewModel);
        bottomSheetCommentsKeyBoard.showKeyBoard();
        bottomSheetCommentsKeyBoard.show(getSupportFragmentManager(), "");
    }

    private void removeFragment() {
        if(postItemFragment != null) {
            handleFrame(conversationFragment);
        }
    }

    private void createBottomSheetFragment() {
        MainBottomSheet mainBottomSheet = new MainBottomSheet();
        mainBottomSheet.show(getSupportFragmentManager(), "main");
    }

    private void sendToStart() {
        launchActivity(StartActivity.class);
        finish();
    }

    @Override
    public void onPostInput(PostData postData) {
        postItemFragment.setItem(postData);
        mCreatePost.hide(listener);
        handleFrame(postItemFragment);
    }

    FloatingActionButton.OnVisibilityChangedListener listener = new FloatingActionButton.OnVisibilityChangedListener(){
        @Override
        public void onShown(FloatingActionButton fab) {
            super.onShown(fab);
        }

        @Override
        public void onHidden(FloatingActionButton fab) {
            super.onHidden(fab);
            isFabTapped = !isFabTapped;
            if(isFabTapped){
                mBottomAppBar.setNavigationIcon(null);
                mBottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                mBottomAppBar.replaceMenu(R.menu.full_post_menu);
                mCreatePost.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_reply_white_24dp));
            } else {
                mBottomAppBar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
                mBottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                mBottomAppBar.replaceMenu(R.menu.main_menu);
                mCreatePost.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_create_white_24dp));
            }
            fab.show();
        }
    };
}
