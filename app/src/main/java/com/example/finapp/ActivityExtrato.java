package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.adapter.AdapterExtrato;
import com.example.finapp.database.Operation;
import com.example.finapp.database.OperationsDAO;
import com.example.finapp.model.Extrato;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityExtrato extends AppCompatActivity {

    private RecyclerView recyclerViewExtrato;
    private List<Extrato> listExtrato = new ArrayList<>();
    private OperationsDAO operationDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        recyclerViewExtrato = findViewById(R.id.recyclerViewExtrato);

        //Configurar o Adapter
        this.createExtrato();
        Collections.sort(listExtrato);
        while(listExtrato.size()>15){
            listExtrato.remove(listExtrato.size()-1);
        }
        AdapterExtrato adapter = new AdapterExtrato(listExtrato);

        //Configurar RecyclerView utilizando um layout linear
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerViewExtrato.setLayoutManager(layoutManager);
        //Otimizar o RecyclerView
        recyclerViewExtrato.setHasFixedSize(true);

        //Insere um divisor entre as células
        recyclerViewExtrato.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //Liga o adapter ao recycler
        recyclerViewExtrato.setAdapter(adapter);

    }

    public void createExtrato() {
        operationDAO = new OperationsDAO(getApplicationContext());
        try {
            listExtrato = operationDAO.listExtrato();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        operationDAO.close();
    }
}