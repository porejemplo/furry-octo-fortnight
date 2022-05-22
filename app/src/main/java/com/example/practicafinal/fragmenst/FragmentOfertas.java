package com.example.practicafinal.fragmenst;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practicafinal.R;
import com.example.practicafinal.adapters.GameRVAdapter;
import com.example.practicafinal.databases.DataHelper;
import com.example.practicafinal.databases.SchemaDB;

public class FragmentOfertas extends Fragment {
    private View view;
    private DataHelper dataHelper;

    private RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataHelper = new DataHelper(context, SchemaDB.DB_NAME, null, SchemaDB.VERSION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ofertas, container, false);

        recyclerView = view.findViewById(R.id.recycler_ofertas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new GameRVAdapter(dataHelper.readGames(" WHERE " + SchemaDB.GAMES_PRICE + "<= 30"), getContext()));
    }
}