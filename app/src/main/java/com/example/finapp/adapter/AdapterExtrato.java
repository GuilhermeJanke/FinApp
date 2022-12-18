package com.example.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;

public class AdapterExtrato extends RecyclerView.Adapter<AdapterExtrato.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView classificacao, data, valor;

        public MyViewHolder(View view){
            super(view);
            classificacao = view.findViewById(R.id.textViewHeaderClassificacao);
            data = view.findViewById(R.id.textViewHeaderData);
            valor = view.findViewById(R.id.textViewHeaderValor);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_extrato, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.classificacao.setText("Teste");
        holder.data.setText("Teste2");
        holder.valor.setText("Teste3");

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
