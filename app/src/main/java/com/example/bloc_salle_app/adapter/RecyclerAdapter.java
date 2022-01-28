package com.example.bloc_salle_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bloc_salle_app.R;
import com.example.bloc_salle_app.beans.Salle;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView salle,bloc,etat;
        public ViewHolder(View v){
            super(v);
            salle = v.findViewById(R.id.salle);
            bloc = v.findViewById(R.id.bloc);
            etat = v.findViewById(R.id.etat);

        }

    }
    Context context;
    ArrayList<Salle> salles = new ArrayList<>();
    public RecyclerAdapter(Context context,ArrayList<Salle> salles){
        this.context = context;
        this.salles = salles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_salle,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.salle.setText(salles.get(position).getName());
        holder.bloc.setText(salles.get(position).getBloc());
        holder.etat.setText(salles.get(position).getEtat());
    }
    @Override
    public int getItemCount() {
        return salles.size();
    }
}
