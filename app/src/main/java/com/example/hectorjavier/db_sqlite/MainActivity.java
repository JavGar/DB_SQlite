package com.example.hectorjavier.db_sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hectorjavier.db_sqlite.adaptador.MiAdaptador;
import com.example.hectorjavier.db_sqlite.basedatos.CRUD_BD;
import com.example.hectorjavier.db_sqlite.dialogos.AgregaTarea;
import com.example.hectorjavier.db_sqlite.modelos.Tarea;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_tareas;
    List tareas;

    CRUD_BD crud_bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //abrimos base de datos
        crud_bd = new CRUD_BD(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), AgregaTarea.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        muestraTareas();
    }

    public void muestraTareas(){
        lv_tareas = (ListView) findViewById(R.id.lv_tareas);
        crud_bd.abrirBD();
        tareas = crud_bd.listarTareas();
        if(tareas == null){
            tareas = new ArrayList();
            tareas.add("Sin tareas");
            lv_tareas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareas));
        }else {
            lv_tareas.setAdapter(new MiAdaptador(tareas,R.layout.tarea ,getApplicationContext()) {
                @Override
                public View paraElElemento(final Object elemento, View view) {
                    ((TextView) view.findViewById(R.id.tv_titulo)).setText(((Tarea) elemento).getTitulo());
                    ((TextView) view.findViewById(R.id.tv_descripcion)).setText(((Tarea) elemento).getDescripcion());
                    //((TextView) view.findViewById(R.id.tv_fecha)).setText(((Tarea) elemento).getFecha()); segunda parte

                    ((ImageButton) view.findViewById(R.id.ib_borrar)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            crud_bd.abrirBD();
                            crud_bd.eliminarTarea((Tarea) elemento);
                            crud_bd.cerrarDB();
                            muestraTareas();
                        }
                    });
                    return view;
                }
            });
        }
        crud_bd.cerrarDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
