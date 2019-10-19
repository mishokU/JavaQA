package com.example.javaqa.ui.fragments.java_core.introduction;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.ActivityUtils.SwapperHelper;
import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.QueueItemListAdapter;
import com.example.javaqa.models.QueueItem;
import com.example.javaqa.ui.fragments.Bases.BaseLearnFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class QueueToJavaCompile extends BaseLearnFragment {

  private View view;
  private RecyclerView recyclerView;
  private ArrayList<QueueItem> items;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private MaterialButton checkButton;
  private OnProgressListener progressListener;

  private final int correctCount = 4;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.javacore_fourth_introduction_fragment,container,false);

    findAllViews();
    checkButtonSetUp();
    setUpRecyclerView();

    return view;
  }

  private void checkButtonSetUp() {
    checkButton.setOnClickListener(view -> {
      if(checkCorrectOrder()){
        checkButton.setRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.button_color)));
        progressListener.onProgressSend("JavaCore",1);
        if(checkButton.getText().equals("Продолжить")){
          getActivity().finish();
        }
        checkButton.setText("Продолжить");
      } else {
        checkButton.setRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
      }
    });
  }

  // 2 0 3 1
  private Boolean checkCorrectOrder() {
    int correct = 0;
    for(int i = 0; i < items.size(); i++){
      if(i == items.get(i).getIndex()){
        correct++;
      }
    }
    return correct == correctCount;
  }

  private void setUpRecyclerView() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    items = new ArrayList<>();
    adapter = new QueueItemListAdapter(items);

    items.add(new QueueItem("байт-код с расширением .class",1));
    items.add(new QueueItem("Операционная система.",3));
    items.add(new QueueItem("Компилятор javac c файлами .java",0));
    items.add(new QueueItem("Выполнение в JVM",2));

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    SwapperHelper swapperHelper = new SwapperHelper(adapter,items);
    swapperHelper.createHelper();
    swapperHelper.getHelper().attachToRecyclerView(recyclerView);

    ((QueueItemListAdapter) adapter).setOnItemClickListener(position ->
        Toast.makeText(getContext(),"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

  private void findAllViews() {
    recyclerView = view.findViewById(R.id.queue_recycler_view);
    checkButton = view.findViewById(R.id.check_button);
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
