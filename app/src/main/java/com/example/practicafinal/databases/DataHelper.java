package com.example.practicafinal.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.practicafinal.R;
import com.example.practicafinal.model.GameModel;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +SchemaDB.TAB_NAME+ " ("
                + SchemaDB.GAMES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SchemaDB.GAMES_NAME + " TEXT,"
                + SchemaDB.GAMES_COMPANY + " TEXT,"
                + SchemaDB.GAMES_CONSOLE + " TEXT,"
                + SchemaDB.GAMES_BUY + " INTEGER,"
                + SchemaDB.GAMES_IMAGE + " INTEGER,"
                + SchemaDB.GAMES_PRICE + " INTEGER);");

        addVideogame(sqLiteDatabase, "Gran Turismo","Sony", "PS4", R.drawable.gt7, 70);
        addVideogame(sqLiteDatabase, "Residen Evil","Campcpm", "PS4", R.drawable.resident, 40);
        addVideogame(sqLiteDatabase, "Forza","Playground Games", "XBOX", R.drawable.forza, 70);
        addVideogame(sqLiteDatabase, "Halo","Bungie Studios", "XBOX", R.drawable.halo, 60);
        addVideogame(sqLiteDatabase, "Killer Instict","Double Helix", "XBOX", R.drawable.killer, 15);
        addVideogame(sqLiteDatabase, "Scrom","Ebb Software ", "XBOX", R.drawable.scrom, 20);
        addVideogame(sqLiteDatabase, "Horizon","Guerrilla Games", "PC", R.drawable.horizon, 20);
        addVideogame(sqLiteDatabase, "BloodBorme","From Software ", "PC", R.drawable.blood, 30);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SchemaDB.TAB_NAME);
        onCreate(sqLiteDatabase);
    }

    public static void addVideogame (SQLiteDatabase db, String name, String company, String consola, int imageID, float price) {
        ContentValues gameData = new ContentValues();
        gameData.put(SchemaDB.GAMES_NAME, name);
        gameData.put(SchemaDB.GAMES_COMPANY, company);
        gameData.put(SchemaDB.GAMES_CONSOLE, consola);
        gameData.put(SchemaDB.GAMES_BUY, Boolean.FALSE);
        gameData.put(SchemaDB.GAMES_IMAGE, imageID);
        gameData.put(SchemaDB.GAMES_PRICE, price);
        db.insert(SchemaDB.TAB_NAME, null, gameData);
    }

    public ArrayList<GameModel> readGames() {
        return readGames("");
    }

    public ArrayList<GameModel> readGames(String where) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor gameCourses = db.rawQuery("SELECT * FROM " + SchemaDB.TAB_NAME + where, null);

        // on below line we are creating a new array list.
        ArrayList<GameModel> gameModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (gameCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                //gameModelArrayList.add(new GameModel());
                gameModelArrayList.add(new GameModel(gameCourses.getString(1),
                        gameCourses.getString(2),
                        gameCourses.getString(3),
                        gameCourses.getInt(5),
                        gameCourses.getInt(6),
                        gameCourses.getInt(4)));
            } while (gameCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        gameCourses.close();
        db.close();
        return gameModelArrayList;
    }

    public int precioCarrito() {
        int precio = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String querty = String.format("SELECT SUM(%s) FROM %s WHERE %s = 1",
                SchemaDB.GAMES_PRICE, SchemaDB.TAB_NAME, SchemaDB.GAMES_BUY);
        Cursor gameCourses = db.rawQuery(querty, null);

        if (gameCourses.moveToFirst()) {
            precio = gameCourses.getInt(0);
            // TODO: borrar log
            Log.v("database", String.valueOf(precio));
        }

        db.close();
        return precio;
    }
}
