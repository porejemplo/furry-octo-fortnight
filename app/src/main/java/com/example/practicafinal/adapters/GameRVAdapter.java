package com.example.practicafinal.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicafinal.R;
import com.example.practicafinal.model.GameModel;

import java.util.ArrayList;

public class GameRVAdapter extends RecyclerView.Adapter<GameRVAdapter.ViewHolder> {
    private ArrayList<GameModel> gameModelArrayList;
    private Context context;

    public GameRVAdapter(ArrayList<GameModel> gameModelArrayList, Context context) {
        this.gameModelArrayList = gameModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameRVAdapter.ViewHolder holder, int position) {
        GameModel modal = gameModelArrayList.get(position);
        Log.v("basedatos", modal.toString());
        holder.nombreTxt.setText(modal.getName());
        holder.precioTxt.setText(String.format("%d€", modal.getPrecio()));
        holder.imageView.setImageResource(modal.getImagen());
    }

    @Override
    public int getItemCount() { return gameModelArrayList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTxt, precioTxt;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            nombreTxt = itemView.findViewById(R.id.item_text_nombre);
            precioTxt = itemView.findViewById(R.id.item_text_precio);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }
}
