package com.example.finapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.database.Operation;

import java.util.List;

public class AdapterPesquisa extends RecyclerView.Adapter<AdapterPesquisa.MyViewHolder>{

    private List<Operation> listOperation;

    public AdapterPesquisa (List<Operation> list) {
        this.listOperation = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filter;
        TextView type;
        TextView value;

        public MyViewHolder(View view){
            super(view);
            filter = view.findViewById(R.id.textViewOperacao);
            type = view.findViewById(R.id.textViewCategoria);
            value = view.findViewById(R.id.textViewValor);
        }
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_pesquisa, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Operation obj = listOperation.get(position);
        holder.filter.setText(obj.getFilter());
        holder.type.setText(obj.getType());
        holder.value.setText(Double.toString(obj.getValue()));

    }

    @Override
    public int getItemCount() {
        return listOperation.size();
    }
}
