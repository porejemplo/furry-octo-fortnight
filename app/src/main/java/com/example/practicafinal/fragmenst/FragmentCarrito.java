package com.example.practicafinal.fragmenst;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button button;

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
        button = view.findViewById(R.id.carrito_button);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter( new CarritoRVAdapter(dataHelper.readGames(" WHERE " + SchemaDB.GAMES_BUY + " = 1"), getContext()));
        String precio = String.valueOf(dataHelper.precioCarrito()) + "€";
        textView.setText("Total: " + precio);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"direccion@ext.live.u-tad.es"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Compra Juegos");
                intent.putExtra(Intent.EXTRA_TEXT, "La compra de losjuegos se ha procesado, el pago deberá ser de " + precio);
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
    }
}