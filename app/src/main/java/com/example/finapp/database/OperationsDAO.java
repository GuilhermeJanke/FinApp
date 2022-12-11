package com.example.finapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

}
