package com.example.javaqa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javaqa.ActivityUtils.CurrentTime;
import com.example.javaqa.R;
import com.example.javaqa.models.CommentsModel;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.viewmodels.CommentsViewModel;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSheetCommentsKeyBoard extends BottomSheetDialogFragment {

  private View mView;
  private EditText mInputField;
  private ImageButton mSendCommentButton;
  private CircleImageView mUserProfileImage;
  private CommentsViewModel mCommentViewModel;
  private UserViewModel mUserViewModel;
  private UserMainData mUserMainData;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.write_comment_to_post,container,false);

    findViews();
    sendComment();

    return mView;
  }

  private void findViews() {
    mInputField = mView.findViewById(R.id.input_message);
    mSendCommentButton = mView.findViewById(R.id.post_comment_button);
    mUserProfileImage = mView.findViewById(R.id.user_profile_image);
  }

  public void setViewModel(CommentsViewModel mCommentViewModel){
    this.mCommentViewModel = mCommentViewModel;
  }

  public void setUserViewModel(UserViewModel mUserViewModel){
    this.mUserViewModel = mUserViewModel;
    mUserViewModel.getUserMainData().observe(this, userMainData -> mUserMainData = userMainData);
  }

  private void sendComment(){
    mSendCommentButton.setOnClickListener(view -> {
      CommentsModel model = new CommentsModel();
      model.setAnswer(mInputField.getText().toString());
      model.setUserName(mUserMainData.getUsername());
      model.setRating(0);
      model.setUserImageUrl(mUserMainData.getImageURL());
      model.setTime(CurrentTime.getCurrentDate());

      mCommentViewModel.insertComment(model);
      mInputField.setText("");
      dismiss();
    });
  }
}
