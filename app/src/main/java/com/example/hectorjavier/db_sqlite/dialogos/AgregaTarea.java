package com.example.hectorjavier.db_sqlite.dialogos;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hectorjavier.db_sqlite.R;
import com.example.hectorjavier.db_sqlite.basedatos.CRUD_BD;
import com.example.hectorjavier.db_sqlite.modelos.Tarea;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgregaTarea extends AppCompatActivity {

    EditText et_titulo,et_descripcion;
    //TextView tv_fecha; segunda parte
    //ImageButton ib_fecha; segunda parte
    Button btn_agregar;

    DatePickerDialog dpd;
    CRUD_BD crud_bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_tarea);

        crud_bd = new CRUD_BD(getApplicationContext());

        et_titulo = (EditText) findViewById(R.id.et_nombre_tarea);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion_tarea);
        //tv_fecha = (TextView) findViewById(R.id.tv_fecha_tarea); segunda parte
        //ib_fecha = (ImageButton) findViewById(R.id.ib_fecha_tarea); segunda parte
        btn_agregar = (Button) findViewById(R.id.btn_agregar_tarea);


        /*
        //Segunda parte
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar newCalendar = Calendar.getInstance();
        dpd = new DatePickerDialog(AgregaTarea.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tv_fecha.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        ib_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });
        */

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_descripcion.getText().length()>0 && et_titulo.getText().length()>0 /* segunda parte && tv_fecha.getText().length()>0*/){
                    crud_bd.abrirBD();
                    crud_bd.insertarTarea(
                            new Tarea(
                                    et_titulo.getText().toString(),
                                    et_descripcion.getText().toString()//, segunda parte
                                    //tv_fecha.getText().toString()
                            )
                    );
                    crud_bd.cerrarDB();
                    onBackPressed();
                }else {
                    Toast.makeText(getApplicationContext(),"Campos vacios, no se agrego la tarea.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
