package com.example.finapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.model.Extrato;

import java.util.ArrayList;
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
    cursor.close();
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
    cursor.close();
    return operationList;
  }

  public List<Operation> getAllOperations(){
    List<Operation> operationList = new ArrayList<>();
    Cursor cursor = read.query(SimpleDBWrapper.TABLE_NAME_OPER, new String[]{"id", "filter", "type", "value", "date"},
            null, null, null, null, null );
    while(cursor.moveToNext()){
      Operation operation = new Operation();
      @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
      @SuppressLint("Range") String filter = cursor.getString(cursor.getColumnIndex("filter"));
      @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
      @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
      @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));;
      operation.setId(id);
      operation.setFilter(filter);
      operation.setType(type);
      operation.setValue(value);
      operation.setDate(date);
      operationList.add(operation);
    }
    cursor.close();
    return operationList;
  }

  public List<Extrato> listExtrato(){
    List<Extrato> extratoList = new ArrayList<>();
    Cursor cursor = read.query(SimpleDBWrapper.TABLE_NAME_OPER, new String[]{"type", "value", "date"},
            null, null, null, null, null );
    while(cursor.moveToNext()){
      Extrato extrato = new Extrato();
      @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
      @SuppressLint("Range") double value = cursor.getDouble(cursor.getColumnIndex("value"));
      @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));;
      extrato.setType(type);
      extrato.setValue(value);
      extrato.setDate(date);
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

  public List<Operation>listListaPesquisar(String dataInicial, String dataFinal, String tipoOperacao){

    List<Operation> operationList = new ArrayList<>();
    String sql = "";

    if(tipoOperacao == "Todas as Operações" ){
      sql = "SELECT filter, type, SUM(value) as value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE data BETWEEN " + dataInicial + " AND " + dataFinal;
      Cursor cursor = read.rawQuery(sql,null);
      return operationList;
    }
    if(tipoOperacao == "Crédito"){
      sql = "SELECT filter, type, SUM(value) as value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE type = 'credito' data BETWEEN " + dataInicial + " AND " + dataFinal;
      Cursor cursor = read.rawQuery(sql,null);
      return operationList;
    }
    if(tipoOperacao == "Débito"){
      sql = "SELECT filter, type, SUM(value) as value FROM " + SimpleDBWrapper.TABLE_NAME_OPER + " WHERE type = 'debito' data BETWEEN " + dataInicial + " AND " + dataFinal;
      Cursor cursor = read.rawQuery(sql,null);
      return operationList;
    }

  return null;
  }
}
