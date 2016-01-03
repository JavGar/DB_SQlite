package com.example.hectorjavier.db_sqlite.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hectorjavier.db_sqlite.modelos.Tarea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HectorJavier on 03/01/2016.
 */
public class CRUD_BD {
    private BD_Tareas bd_tareas;
    private SQLiteDatabase sql;
    private Context contexto;

    public CRUD_BD(Context context) {
        contexto = context;
        bd_tareas = new BD_Tareas(context);
    }

    public void abrirBD(){
        sql = bd_tareas.getWritableDatabase();
    }

    public void cerrarDB(){
        sql.close();
        bd_tareas.close();
    }

    public long insertarTarea(Tarea tarea){
        ContentValues cv = new ContentValues();
        cv.put(BD_Tareas.TITULO_TAREA, tarea.getTitulo());
        cv.put(BD_Tareas.DESC_TAREA, tarea.getDescripcion());
        //cv.put(BD_Tareas.FECHA_TAREA, tarea.getFecha()); segunda parte
        return sql.insert(BD_Tareas.TB_TAREAS,null,cv);
    }

    public int eliminarTarea(Tarea tarea){
        return sql.delete(BD_Tareas.TB_TAREAS, BD_Tareas.ID_TAREA + " = " + tarea.getId(), null);
    }

    public List<Tarea> listarTareas(){
        List<Tarea> tareas = new ArrayList<>();
        Tarea t;
        Cursor c = sql.rawQuery("SELECT * FROM "+BD_Tareas.TB_TAREAS,null);

        if(c.moveToFirst()){
            do{
                t = new Tarea(
                        c.getInt(c.getColumnIndex(BD_Tareas.ID_TAREA)),
                        c.getString(c.getColumnIndex(BD_Tareas.TITULO_TAREA)),
                        c.getString(c.getColumnIndex(BD_Tareas.DESC_TAREA))//,segunda parte
                        //c.getString(c.getColumnIndex(BD_Tareas.FECHA_TAREA))
                );
                tareas.add(t);
            }while (c.moveToNext());
            return tareas;
        }else{
            return null;
        }
    }
}
