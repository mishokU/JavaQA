package com.example.javaqa.items;

public class QueueItem {

  private String statement;
  private int index;

  public QueueItem(String statement,int index){
    this.statement = statement;
    this.index = index;
  }

  public String getStatement() {
    return statement;
  }
  public int getIndex() {return index; }

}
