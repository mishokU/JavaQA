package com.example.javaqa.fragments.java_core.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.ActivityUtils.SwapperHelper;
import com.example.javaqa.R;
import com.example.javaqa.adapters.QueueItemListAdapter;
import com.example.javaqa.items.QueueItem;

import java.util.ArrayList;

public class QueueToJavaCompile extends Fragment {

  private View view;
  private RecyclerView recyclerView;
  private ArrayList<QueueItem> items;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private Button checkButton;

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
        checkButton.setBackground(getResources().getDrawable(R.drawable.blue_button_correct_game_style));
        if(checkButton.getText().equals("Продолжить")){
          getActivity().finish();
        }
        checkButton.setText("Продолжить");
      } else {
        checkButton.setBackground(getResources().getDrawable(R.drawable.blue_button_wrong_game_style));
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
}
