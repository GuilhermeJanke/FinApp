package com.example.finapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class OperationsDAO {
  //private SimpleDBWrapper dbHelper;
  //private String[] OPERATION_TABLE_COLUMNS = {SimpleDBWrapper.OPERATION_ID, SimpleDBWrapper.OPERATION_FILTER, SimpleDBWrapper.OPERATION_TYPE, SimpleDBWrapper.OPERATION_VALUE, SimpleDBWrapper.OPERATION_DATE};
  //private SQLiteDatabase db;
  private SQLiteDatabase write, read;

  public OperationsDAO(Context context){
    SimpleDBWrapper db = new SimpleDBWrapper(context);
    write = db.getWritableDatabase();
    read = db.getReadableDatabase();
  }

  public boolean addOperation(Operation operation){
    ContentValues values = new ContentValues();
    values.put(SimpleDBWrapper.OPERATION_FILTER, operation.getFilter());
    values.put(SimpleDBWrapper.OPERATION_TYPE, operation.getType());
    values.put(SimpleDBWrapper.OPERATION_VALUE, operation.getValue());
    values.put(SimpleDBWrapper.OPERATION_DATE, operation.getDate());

    try{
      write.insert(SimpleDBWrapper.TABLE_NAME_OPER, null, values);
      Log.i("INFO", "Operação adicionada!");
    }catch (Exception e){
      Log.e("INFO", "Erro ao salvar operação." + e.getMessage());
      return false;
    }
    return true;
  }

  public List<Operation> getListaClassificada(){
    List<Operation> operationList = new ArrayList<>();
    String sql = "SELECT id, filter, type, value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " GROUP BY filter;";
    Cursor cursor = read.rawQuery(sql, null);

    while(cursor.moveToNext()){
      Operation operation = new Operation();
      @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
      operation.setId(id);
      operation.setFilter("filter");
      operation.setType("type");
      operation.setValue(Double.parseDouble("value"));
      operationList.add(operation);
    }
    return operationList;
  }

  public List<Operation> search(String minDate, String maxDate){
    List<Operation> operationList = new ArrayList<>();
    String sql = "SELECT id, filter, date, value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE "
            + SimpleDBWrapper.OPERATION_DATE + " BETWEEN " + "minDate" + " AND " + "maxDate;";
    Cursor cursor = read.rawQuery(sql, null);

    while(cursor.moveToNext()){
      Operation operation = new Operation();
      @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
      operation.setId(id);
      operation.setFilter("filter");
      operation.setType("date");
      operation.setValue(Double.parseDouble("value"));
      operationList.add(operation);
    }
    return operationList;
  }

}
