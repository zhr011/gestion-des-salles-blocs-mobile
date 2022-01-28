package com.example.bloc_salle_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloc_salle_app.R;
import com.example.bloc_salle_app.beans.Bloc;
import com.example.bloc_salle_app.beans.Salle;

import java.util.ArrayList;

public class RecyclerBlocAdapter extends RecyclerView.Adapter<RecyclerBlocAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btn_bloc;
        public ViewHolder(View v){
            super(v);
            btn_bloc = v.findViewById(R.id.btn_bloc);

        }
    }
    Context context;
    ArrayList<Bloc> blocs = new ArrayList<>();
    public RecyclerBlocAdapter(Context context, ArrayList<Bloc> blocs){
        this.context = context;
        this.blocs = blocs;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_bloc,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btn_bloc.setText(blocs.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return blocs.size();
    }
}
