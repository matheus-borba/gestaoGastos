package com.example.gestaogastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gestaogastos.DBHelper.GastosDb;
import com.example.gestaogastos.model.Gastos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    GastosDb dbHelper;
    ArrayList<Gastos> listview_Gastos;
    Gastos gasto;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listview_Gastos);
        registerForContextMenu(lista);


        Button btn_novoCadastro = (Button) findViewById(R.id.btn_novoCadastro);
        btn_novoCadastro.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Gastos gastoEscolhido = (Gastos) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, Cadastro.class);
                i.putExtra("gasto_escolhido", gastoEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                gasto = (Gastos)adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Gasto");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                dbHelper = new GastosDb(MainActivity.this);
                dbHelper.deletarGasto(gasto);
                dbHelper.close();
                carregarGasto();

                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarGasto();
    }

    public void carregarGasto(){
        dbHelper = new GastosDb(MainActivity.this);
        listview_Gastos = dbHelper.getLista();
        dbHelper.close();

        if(listview_Gastos != null){
            adapter = new ArrayAdapter<Gastos>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Gastos);
            lista.setAdapter(adapter);
        }
        //finish();
    }

}









