package com.example.hectorjavier.db_sqlite.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HectorJavier on 03/01/2016.
 */
public class BD_Tareas extends SQLiteOpenHelper {

    private static String BD_NOMBRE = "Tareas";
    private static int VERSION = 1;

    public static String TB_TAREAS = "Tarea";
    public static String ID_TAREA = "id_tarea";
    public static String TITULO_TAREA = "titulo_tarea";
    public static String DESC_TAREA = "desc_tarea";
    //public static String FECHA_TAREA = "fecha_tarea"; segunda parte
    public static String CREA_TAREA = "CREATE TABLE "+TB_TAREAS+" ("
            +ID_TAREA+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TITULO_TAREA+" TEXT NOT NULL,"
            +DESC_TAREA+" TEXT NOT NULL);"/*segunda parte","
            +FECHA_TAREA+" TEXT NOT NULL DEFAULT '31-12-2000');"*/;

    public BD_Tareas(Context context) {
        super(context, BD_NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("BaseDatos","Base de datos creada versión: "+VERSION);
        db.execSQL(CREA_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("BaseDatos","Base de datos actualizada a la versión "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+TB_TAREAS);
        onCreate(db);
    }
}
