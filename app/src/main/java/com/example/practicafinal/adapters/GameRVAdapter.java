package com.example.practicafinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicafinal.R;
import com.example.practicafinal.databases.DataHelper;
import com.example.practicafinal.databases.SchemaDB;
import com.example.practicafinal.model.GameModel;

import java.util.ArrayList;

public class GameRVAdapter extends RecyclerView.Adapter<GameRVAdapter.ViewHolder> {
    private ArrayList<GameModel> gameModelArrayList;
    private Context context;
    private View view;

    public GameRVAdapter(ArrayList<GameModel> gameModelArrayList, Context context) {
        this.gameModelArrayList = gameModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameRVAdapter.ViewHolder holder, int position) {
        GameModel modal = gameModelArrayList.get(position);
        Log.v("basedatos", modal.toString());
        holder.nombreTxt.setText(modal.getName());
        holder.precioTxt.setText(String.format("%d€", modal.getPrecio()));
        holder.imageView.setImageResource(modal.getImagen());
        holder.checkBox.setChecked(modal.getBuy());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cheked =  holder.checkBox.isChecked();
                String nombre = modal.getName();
                DataHelper dataHelper = new DataHelper(context, SchemaDB.DB_NAME, null, SchemaDB.VERSION);
                // TODO: Borrar log
                Log.v("cheked",nombre + " " + (cheked ? "true" : "false"));
                dataHelper.buyGame(nombre, (cheked ? 1 : 0), context);
            }
        });
    }

    @Override
    public int getItemCount() { return gameModelArrayList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTxt, precioTxt;
        ImageView imageView;
        CheckBox checkBox;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            checkBox = itemView.findViewById(R.id.favoriteCheckbox);
            nombreTxt = itemView.findViewById(R.id.item_text_nombre);
            precioTxt = itemView.findViewById(R.id.item_text_precio);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }

    public void  favoriteOnClick (View v) {
        boolean cheked = ((CheckBox)v.findViewById(R.id.favoriteCheckbox)).isChecked();
        String nombre = (String) ((TextView)v.findViewById(R.id.item_text_nombre)).getText();
        // TODO: Borrar log
        Log.v("cheked",nombre + " " + (cheked ? "true" : "false"));
    }
}
