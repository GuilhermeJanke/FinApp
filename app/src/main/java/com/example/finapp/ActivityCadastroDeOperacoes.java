package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.finapp.database.Operation;
import com.example.finapp.database.OperationsDAO;
import com.santalu.maskara.widget.MaskEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityCadastroDeOperacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_operacoes);
    }

    public void addOperation(View view){
        RadioButton moradiaDeb = findViewById(R.id.radioButtonDebMoradia);
        RadioButton saudeDeb = findViewById(R.id.radioButtonDebSaude);
        RadioButton outroDeb = findViewById(R.id.radioButtonDebOutros);

        RadioButton salarioCred = findViewById(R.id.radioButtonCredSalario);
        RadioButton outroCred = findViewById(R.id.radioButtonCredOutros);

        EditText valueInput = findViewById(R.id.editTextValue);
        MaskEditText dateInput = findViewById(R.id.editTextDate);

        double value = Double.parseDouble(valueInput.getText().toString());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dateInput.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(valueInput.length() == 0 | dateInput.length() == 0){
            Toast.makeText(this, "Digite todos os valores para continuar!", Toast.LENGTH_SHORT).show();
        }else{
            OperationsDAO operationsDAO = new OperationsDAO(getApplicationContext());
            Operation operation = new Operation();
            if(outroCred.isChecked()) {
                operation.setFilter("credito");
                operation.setType("outro");
            }else if(salarioCred.isChecked()){
                operation.setFilter("credito");
                operation.setType("salario");
            }else if(outroDeb.isChecked()){
                operation.setFilter("debito");
                operation.setType("outro");
            }else if(saudeDeb.isChecked()){
                operation.setFilter("debito");
                operation.setType("saude");
            }else if(moradiaDeb.isChecked()){
                operation.setFilter("debito");
                operation.setType("moradia");
            }
            operation.setValue(value);
            operation.setDate(date);
            operationsDAO.addOperation(operation);
            Toast.makeText(this, "Operação adicionada!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}