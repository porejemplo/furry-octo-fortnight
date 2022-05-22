package com.example.practicafinal.fragmenst;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.practicafinal.R;
import com.example.practicafinal.adapters.GameRVAdapter;
import com.example.practicafinal.databases.DataHelper;
import com.example.practicafinal.databases.SchemaDB;
import com.example.practicafinal.model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentJuegos extends Fragment {
    private View view;
    private DataHelper dataHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    private RecyclerView recyclerView;
    private List<GameModel> gameModelList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataHelper = new DataHelper(context, SchemaDB.DB_NAME, null, SchemaDB.VERSION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_juegos, container, false);

        recyclerView = view.findViewById(R.id.recycler_juegos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new GameRVAdapter(dataHelper.readGames(), getContext()));
    }
}