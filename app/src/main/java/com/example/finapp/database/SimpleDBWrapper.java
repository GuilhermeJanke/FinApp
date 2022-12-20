package com.example.finapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDBWrapper extends SQLiteOpenHelper {

  private static final String DB_NAME = "Fintech.db";
  private static final int DB_VERSION = 1;

  //cadastro de operações
  public static final String TABLE_NAME_OPER = "Operations";
  public static final String OPERATION_ID= "id";
  public static final String OPERATION_FILTER= "filter"; //credito ou debito
  public static final String OPERATION_TYPE = "type"; //moradia, saude, salario etc
  public static final String OPERATION_VALUE= "value";
  public static final String OPERATION_DATE= "date";

  private static final String DB_CREATE_OPER ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME_OPER +
          "(" + OPERATION_ID + " integer primary key autoincrement, " + OPERATION_FILTER + " text not null, " +
          OPERATION_TYPE + " text not null, " + OPERATION_VALUE + " double, " + OPERATION_DATE + " date);";

  public SimpleDBWrapper(Context context){
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(DB_CREATE_OPER);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_OPER);
    onCreate(sqLiteDatabase);
  }
}
