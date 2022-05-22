package com.example.practicafinal.fragmenst;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practicafinal.R;
import com.example.practicafinal.adapters.GameRVAdapter;
import com.example.practicafinal.databases.DataHelper;
import com.example.practicafinal.databases.SchemaDB;
import com.example.practicafinal.model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentJuegos extends Fragment implements AdapterView.OnItemSelectedListener {
    private View view;
    private DataHelper dataHelper;

    private RecyclerView recyclerView;
    private Spinner spinner;

    private String[] courses = { "Todas", "PS4", "XBOX", "PC"};
    private int seleccion = 0;

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
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(), 2));

        spinner = (Spinner) view.findViewById(R.id.spinner_juegos);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, courses);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(ad);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarLista();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = courses[i];
        seleccion = i;

        cargarLista();
        Toast.makeText(getContext(), "Filtrando: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void cargarLista () {
        if (seleccion == 0) {
            recyclerView.setAdapter(new GameRVAdapter(dataHelper.readGames(), getContext()));
        } else {
            recyclerView.setAdapter(new GameRVAdapter(dataHelper.readGames(" WHERE " + SchemaDB.GAMES_CONSOLE + " LIKE '" + courses[seleccion] + "'"), getContext()));
        }
    }
}