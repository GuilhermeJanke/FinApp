package com.example.finapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
        recyclerViewListaPesquisa = findViewById(R.id.recyclerViewPesquisar);
    }

    public void onClick(View view){

        RadioButton radioButtonTodos = findViewById(R.id.todas);
        RadioButton radioButtonCredito = findViewById(R.id.credito);
        RadioButton radioButtonDebito = findViewById(R.id.debito);

        MaskEditText dateInputInicial = findViewById(R.id.dataInicial);

        if(radioButtonTodos.isChecked()) {
            this.createListaPesquisa("todos");

        }
        else if(radioButtonCredito.isChecked()) {
            this.createListaPesquisa("credito");
        }
        else if(radioButtonDebito.isChecked()) {
            this.createListaPesquisa("debito");
        }


        AdapterPesquisa adapterPesquisa = new AdapterPesquisa(listOperation);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewListaPesquisa.setLayoutManager(layoutManager);

        recyclerViewListaPesquisa.setHasFixedSize(true);

        recyclerViewListaPesquisa.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewListaPesquisa.setAdapter(adapterPesquisa);

    }
    public void createListaPesquisa(String operacao) {
        operationDAO = new OperationsDAO(getApplicationContext());
        listOperation = operationDAO.listListaPesquisar(operacao);
    }

    @Override
    protected void onPause() {
        super.onPause();
        operationDAO.close();
    }
}