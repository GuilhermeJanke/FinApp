package com.example.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.model.Extrato;

import java.util.List;

public class AdapterExtrato extends RecyclerView.Adapter<AdapterExtrato.MyViewHolder> {

    private List<Extrato> listExtrato;

    public AdapterExtrato(List<Extrato> list) {
        this.listExtrato = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView type;
        TextView date;
        TextView value;

        public MyViewHolder(View view){
            super(view);
            type = view.findViewById(R.id.textViewClassificacao);
            date = view.findViewById(R.id.textViewData);
            value = view.findViewById(R.id.textViewValor);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_extrato, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Extrato obj = listExtrato.get(position);
        holder.type.setText(obj.getType());
        holder.date.setText(obj.getDate());
        holder.value.setText(Double.toString(obj.getValue()));

    }

    @Override
    public int getItemCount() {
        return listExtrato.size();
    }
}
