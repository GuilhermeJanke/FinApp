package com.example.finapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.model.Extrato;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OperationsDAO {
  private SimpleDBWrapper db;
  //private String[] OPERATION_TABLE_COLUMNS = {SimpleDBWrapper.OPERATION_ID, SimpleDBWrapper.OPERATION_FILTER, SimpleDBWrapper.OPERATION_TYPE, SimpleDBWrapper.OPERATION_VALUE, SimpleDBWrapper.OPERATION_DATE};
  //private SQLiteDatabase db;
  private SQLiteDatabase write, read;

  public OperationsDAO(Context context){
    db = new SimpleDBWrapper(context);
    write = db.getWritableDatabase();
    read = db.getReadableDatabase();
  }

  public void close(){
    db.close();
  }

  public boolean addOperation(Operation operation){
    ContentValues values = new ContentValues();
    values.put(SimpleDBWrapper.OPERATION_FILTER, operation.getFilter());
    values.put(SimpleDBWrapper.OPERATION_TYPE, operation.getType());
    values.put(SimpleDBWrapper.OPERATION_VALUE, operation.getValue());

    Date date = operation.getDate();

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    String strDate = formatter.format(date);

    values.put(SimpleDBWrapper.OPERATION_DATE, strDate);

    try{
      write.insert(SimpleDBWrapper.TABLE_NAME_OPER, null, values);
      Log.i("INFO", "Operação adicionada!");
    }catch (Exception e){
      Log.e("INFO", "Erro ao salvar operação." + e.getMessage());
      return false;
    }
    return true;
  }

  public List<Extrato> listExtrato() throws ParseException {
    List<Extrato> extratoList = new ArrayList<>();
    Cursor cursor = read.query(SimpleDBWrapper.TABLE_NAME_OPER, new String[]{"type", "value", "date"},
            null, null, null, null, null );
    while(cursor.moveToNext()){
      Extrato extrato = new Extrato();
      @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
      @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
      @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
      extrato.setType(type);
      extrato.setValue(value);
      extrato.setDate(date);

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      Date dateDate = null;
      try {
        dateDate = format.parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      extrato.setDateDate(dateDate);
      extratoList.add(extrato);
    }
    cursor.close();
    return extratoList;
  }

  public List<Operation>listListaClassificada() {
    List<Operation> operationList = new ArrayList<>();
    String sql = "SELECT filter, type, SUM(value) as value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " GROUP BY type ORDER BY filter DESC;";
    Cursor cursor = read.rawQuery(sql, null);

    while (cursor.moveToNext()) {
      Operation operation = new Operation();
      @SuppressLint("Range") String filter = cursor.getString(cursor.getColumnIndex("filter"));
      @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
      @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
      operation.setFilter(filter);
      operation.setType(type);
      operation.setValue(value);
      operationList.add(operation);
    }
    cursor.close();
    return operationList;
  }

  public List<Operation>listListaPesquisar(String tipoOperacao){

    List<Operation> operationList = new ArrayList<>();
    String sql = "";

    if(tipoOperacao == "todos" ){
      sql = "SELECT filter, type, value, date FROM " + SimpleDBWrapper.TABLE_NAME_OPER;
      Cursor cursor = read.rawQuery(sql,null);

      while (cursor.moveToNext()) {
        Operation operation = new Operation();
        @SuppressLint("Range") String filter = cursor.getString(cursor.getColumnIndex("filter"));
        @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
        @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
        @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
        operation.setFilter(filter);
        operation.setType(type);
        operation.setValue(value);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDate = null;
        try {
          dateDate = format.parse(date);
        } catch (ParseException e) {
          e.printStackTrace();
        }
        operation.setDate(dateDate);
        operationList.add(operation);
      }

      cursor.close();
      return operationList;
    }
    if(tipoOperacao == "credito"){
      sql = "SELECT filter, type, value, date FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE filter = 'credito'";
      Cursor cursor = read.rawQuery(sql,null);

      while (cursor.moveToNext()) {
        Operation operation = new Operation();
        @SuppressLint("Range") String filter = cursor.getString(cursor.getColumnIndex("filter"));
        @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
        @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
        @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
        operation.setFilter(filter);
        operation.setType(type);
        operation.setValue(value);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDate = null;
        try {
          dateDate = format.parse(date);
        } catch (ParseException e) {
          e.printStackTrace();
        }
        operation.setDate(dateDate);
        operationList.add(operation);
      }

      cursor.close();
      return operationList;
    }
    if(tipoOperacao == "debito"){
      sql = "SELECT filter, type, value, date FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE filter = 'debito'";
      Cursor cursor = read.rawQuery(sql,null);

      while (cursor.moveToNext()) {
        Operation operation = new Operation();
        @SuppressLint("Range") String filter = cursor.getString(cursor.getColumnIndex("filter"));
        @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
        @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
        @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
        operation.setFilter(filter);
        operation.setType(type);
        operation.setValue(value);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDate = null;
        try {
          dateDate = format.parse(date);
        } catch (ParseException e) {
          e.printStackTrace();
        }
        operation.setDate(dateDate);
        operationList.add(operation);
      }

      cursor.close();
      return operationList;
    }

  return null;
  }
}
