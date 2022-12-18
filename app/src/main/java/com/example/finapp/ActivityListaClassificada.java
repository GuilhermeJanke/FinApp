package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finapp.adapter.AdapterExtrato;
import com.example.finapp.adapter.AdapterListaClassificada;
import com.example.finapp.database.Operation;
import com.example.finapp.database.OperationsDAO;
import com.example.finapp.model.Extrato;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaClassificada extends AppCompatActivity {

    private OperationsDAO operationDAO;
    private RecyclerView recyclerViewListaClassificada;
    private List<Operation> listOperation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        recyclerViewListaClassificada = findViewById(R.id.recyclerViewListaClassificada);

        //Configurar o Adapter
        this.createListaClassificada();
        AdapterListaClassificada adapter = new AdapterListaClassificada(listOperation);

        //Configurar RecyclerView utilizando um layout linear
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerViewListaClassificada.setLayoutManager(layoutManager);
        //Otimizar o RecyclerView
        recyclerViewListaClassificada.setHasFixedSize(true);

        //Insere um divisor entre as células
        recyclerViewListaClassificada.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //Liga o adapter ao recycler
        recyclerViewListaClassificada.setAdapter(adapter);

    }

    public void createListaClassificada() {
        operationDAO = new OperationsDAO(getApplicationContext());
        listOperation = operationDAO.listListaClassificada();
    }

    @Override
    protected void onPause() {
        super.onPause();
        operationDAO.close();
    }
}