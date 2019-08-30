package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javaqa.R;

import java.util.ResourceBundle;

public class ClickableEffectHelper {

  public static void buttonEffect(View button){
    button.setOnTouchListener(new View.OnTouchListener() {

      public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN: {
            v.getBackground().setColorFilter(v.getResources().getColor(R.color.light_orange), PorterDuff.Mode.SRC_ATOP);
            v.invalidate();
            break;
          }
          case MotionEvent.ACTION_UP: {
            v.getBackground().clearColorFilter();
            v.invalidate();
            break;
          }
        }
        return false;
      }
    });
  }
}
