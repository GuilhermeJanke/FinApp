package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCadastroDeOperacoes_Click(View view){
        Intent it = new Intent(this, ActivityCadastroDeOperacoes.class);
        startActivity(it);
    }

    public void btnExtrato_Click(View view){
        Intent it = new Intent(this, ActivityExtrato.class);
        startActivity(it);
    }

    public void btnPesquisar_Click(View view){
        Intent it = new Intent(this, ActivityPesquisar.class);
        startActivity(it);
    }

    public void btnListaClassificada_Click(View view){
        Intent it = new Intent(this, ActivityListaClassificada.class);
        startActivity(it);
    }

    public void btnSair_Click(View view){
        finish();
    }

}