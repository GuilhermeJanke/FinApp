package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.finapp.adapter.AdapterPesquisa;
import com.example.finapp.database.Operation;
import com.example.finapp.database.OperationsDAO;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;

public class ActivityPesquisar extends AppCompatActivity {

    private OperationsDAO operationDAO;
    private List<Operation> listOperation = new ArrayList<>();
    private RecyclerView recyclerViewListaPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
    }

    public void onClick(View view){
        String dataInicial = String.valueOf(findViewById(R.id.dataInicial));
        String dataFinal = String.valueOf(findViewById(R.id.dataFinal));

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rgPesquisar);
        String radiovalue =((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        this.createListaPesquisa(dataInicial,dataFinal,radiovalue);
        AdapterPesquisa adapterPesquisa = new AdapterPesquisa(listOperation);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());

        recyclerViewListaPesquisa.setLayoutManager(layoutManager);
        recyclerViewListaPesquisa.setHasFixedSize(true);

        recyclerViewListaPesquisa.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerViewListaPesquisa.setAdapter(adapterPesquisa);

    }

    public void createListaPesquisa(String dataInicial, String dataFinal, String operacao) {
        operationDAO = new OperationsDAO(getApplicationContext());
        listOperation = operationDAO.listListaPesquisar(dataInicial,dataFinal,operacao);
    }

    @Override
    protected void onPause() {
        super.onPause();
        operationDAO.close();
    }
}