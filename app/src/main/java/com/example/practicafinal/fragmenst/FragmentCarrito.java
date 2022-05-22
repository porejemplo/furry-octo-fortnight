package com.example.practicafinal.fragmenst;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practicafinal.R;
import com.example.practicafinal.adapters.CarritoRVAdapter;
import com.example.practicafinal.adapters.GameRVAdapter;
import com.example.practicafinal.databases.DataHelper;
import com.example.practicafinal.databases.SchemaDB;

public class FragmentCarrito extends Fragment {
    private View view;
    private DataHelper dataHelper;

    private RecyclerView recyclerView;
    private TextView textView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataHelper = new DataHelper(context, SchemaDB.DB_NAME, null, SchemaDB.VERSION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_carrito, container, false);

        recyclerView = view.findViewById(R.id.recycler_carrito);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));

        textView = view.findViewById(R.id.carrito_precio_texto);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //recyclerView.setAdapter(new GameRVAdapter(dataHelper.readGames(" WHERE " + SchemaDB.GAMES_PRICE + "<= 30"), getContext()));
        // TODO: Cambiar la query
        recyclerView.setAdapter( new CarritoRVAdapter(dataHelper.readGames(), getContext()));
    }
}