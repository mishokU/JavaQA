package com.example.javaqa.ui.fragments.Bases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BaseLearnFragment extends Fragment {

  protected OnProgressListener progressListener;

  public interface OnProgressListener{
    void onProgressSend(String type, int progress);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if(context instanceof OnProgressListener){
      progressListener = (OnProgressListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + "must implement progress listener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    progressListener = null;
  }

}
