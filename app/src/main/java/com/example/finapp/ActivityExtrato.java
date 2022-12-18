package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finapp.adapter.AdapterExtrato;

public class ActivityExtrato extends AppCompatActivity {

    private RecyclerView recyclerViewExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        recyclerViewExtrato = findViewById(R.id.recyclerViewExtrato);

        AdapterExtrato adapter = new AdapterExtrato();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewExtrato.setLayoutManager(layoutManager);

        recyclerViewExtrato.setHasFixedSize(true);

        recyclerViewExtrato.setAdapter(adapter);

    }
}