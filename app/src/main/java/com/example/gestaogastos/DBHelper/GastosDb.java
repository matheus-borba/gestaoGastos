package com.example.gestaogastos.DBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestaogastos.model.Gastos;

import java.util.ArrayList;

public class GastosDb extends SQLiteOpenHelper {

    private static  final String DATABASE ="dbgastos";
    private static  final int VERSION = 1;

    public GastosDb (Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String gasto = "CREATE TABLE gastos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, valor REAL NOT NULL, data TEXT NOT NULL, tipo TEXT NOT NULL, local TEXT NOT NULL);";
        db.execSQL(gasto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String gasto = "DROP TABLE IF EXISTS gastos";
        db.execSQL(gasto);
    }

    public void salvarGastos(Gastos gasto){
        ContentValues values = new ContentValues();

        values.put("valor",gasto.getValor());
        values.put("data",gasto.getData());
        values.put("tipo",gasto.getTipo());
        values.put("local",gasto.getLocal());

        getWritableDatabase().insert("gastos",null,values);
    }

    public void alterarGastos(Gastos gasto){
        ContentValues values = new ContentValues();

        values.put("valor",gasto.getValor());
        values.put("data",gasto.getData());
        values.put("tipo",gasto.getTipo());
        values.put("local",gasto.getLocal());

        String [] args = {gasto.getId().toString()};
        getWritableDatabase().update("gastos",values,"id=?",args);

    }

    public void deletarGasto(Gastos gasto){
        String [] args = {gasto.getId().toString()};
        getWritableDatabase().delete("gastos","id=?",args);
    }

    public ArrayList<Gastos> getLista(){
        String [] columns = {"id","valor","data","tipo","local"};
        Cursor cursor = getWritableDatabase().query("gastos",columns,null,null,null,null,null,null);
        ArrayList<Gastos> gastos = new ArrayList<Gastos>();

        while (cursor.moveToNext()){
            Gastos gasto = new Gastos();
            gasto.setId(cursor.getLong(0));
            gasto.setValor(cursor.getDouble(1));
            gasto.setData(cursor.getString(2));
            gasto.setTipo(cursor.getString(3));
            gasto.setLocal(cursor.getString(4));

            gastos.add(gasto);

        }
        cursor.close();
        return gastos;
    }
}
