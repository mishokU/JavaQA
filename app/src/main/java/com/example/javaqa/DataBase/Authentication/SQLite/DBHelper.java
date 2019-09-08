package com.example.javaqa.DataBase.Authentication.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "javaDB";
  public static final String TABLE_CONTACTS = "contacts";

  public static final String KEY_ID = "_id";
  public static final String KEY_NAME = "name";
  public static final String KEY_EMAIL = "mail";
  public static final String KEY_LEVEL = "level";
  public static final String KEY_LEVEL_EXPERIENCE = "experience ";

  public DBHelper(Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

    String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
        DATABASE_NAME + " (" +
        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_NAME + " TEXT NOT NULL, " +
        KEY_EMAIL + " TEXT NOT NULL, " +
        KEY_LEVEL + " INTEGER NOT NULL," +
        KEY_LEVEL_EXPERIENCE + " INTEGER NOT NULL" + ");";

    sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("Drop table if exists " + TABLE_CONTACTS);
    onCreate(sqLiteDatabase);
  }
}
