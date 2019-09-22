package com.example.gestaogastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestaogastos.DBHelper.GastosDb;
import com.example.gestaogastos.model.Gastos;

public class Cadastro extends AppCompatActivity {

    EditText editText_Valor, editText_Data, editText_TipoGasto, editText_Local;
    Button btn_Cadastrar;
    Gastos editarGasto, gasto;
    GastosDb dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        gasto = new Gastos();
        dbHelper = new GastosDb(Cadastro.this);

        Intent intent = getIntent();
        editarGasto = (Gastos) intent.getSerializableExtra("gasto_escolhido");

        editText_Valor = (EditText) findViewById(R.id.editText_Valor);
        editText_Data = (EditText) findViewById(R.id.editText_Data);
        editText_TipoGasto = (EditText) findViewById(R.id.editText_TipoGasto);
        editText_Local = (EditText) findViewById(R.id.editText_Local);

        btn_Cadastrar = (Button) findViewById(R.id.button_Cadastrar);

        if(editarGasto != null){
            btn_Cadastrar.setText("Modificar");

            editText_Valor.setText(editarGasto.getValor()+"");
            editText_Data.setText(editarGasto.getData());
            editText_TipoGasto.setText(editarGasto.getTipo());
            editText_Local.setText(editarGasto.getLocal());

            gasto.setId(editarGasto.getId());

        }else{
            btn_Cadastrar.setText("Cadastrar");
        }

        btn_Cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            
            public void onClick(View v) {
                gasto.setValor(Double.parseDouble(editText_Valor.getText().toString()));
                gasto.setData(editText_Data.getText().toString());
                gasto.setTipo(editText_TipoGasto.getText().toString());
                gasto.setLocal(editText_Local.getText().toString());

                if(btn_Cadastrar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarGastos(gasto);
                    dbHelper.close();
                }else{
                    dbHelper.alterarGastos(gasto);
                    dbHelper.close();
                }
            }
        });
    }
}
