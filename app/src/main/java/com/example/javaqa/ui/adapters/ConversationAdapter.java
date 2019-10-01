package com.example.javaqa.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.holders.ConversationItemHolder;
import com.example.javaqa.models.PostData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ConversationAdapter extends RecyclerView.Adapter {

  private List<PostData> posts = new ArrayList<>();
  private OnItemClickListener onItemClickListener;
  private DatabaseReference databaseReference;
  private View view;

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation,parent,false);
    return new ConversationItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    PostData conversationItem = posts.get(position);
    if(conversationItem != null) {

      ((ConversationItemHolder) holder).getTitle().setText(conversationItem.getTitle());
      ((ConversationItemHolder) holder).getComments().setText(conversationItem.getComments());
      ((ConversationItemHolder) holder).getTime().setText(conversationItem.getPublicationTime());
      ((ConversationItemHolder) holder).getViews().setText(conversationItem.getCountOfViews());
      ((ConversationItemHolder) holder).getRating().setText(conversationItem.getRating());
      ((ConversationItemHolder) holder).createChipGroup(conversationItem.getHashtags());

      //getUserDataFromUrl(conversationItem.getUserUrl(),holder,conversationItem);
    }
  }

  private void getUserDataFromUrl(String userUrl, RecyclerView.ViewHolder holder, PostData conversationItem) {
    databaseReference = FirebaseDatabase.getInstance().getReference();
      databaseReference.child("Users").child(userUrl).child("userData").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          String userName = Objects.requireNonNull(dataSnapshot.child("username").getValue()).toString();
          String userImageUrl = Objects.requireNonNull(dataSnapshot.child("imageURL").getValue()).toString();

          ((ConversationItemHolder) holder).getUserName().setText(userName);

          conversationItem.setUserName(userName);
          conversationItem.setUserImageUrl(userImageUrl);
          //Profile Image;
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public void setPosts(List<PostData> posts){
    this.posts = posts;
    notifyDataSetChanged();
  }

}
