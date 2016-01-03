package com.example.hectorjavier.db_sqlite.modelos;

import java.sql.Time;
import java.util.Date;

/**
 * Created by HectorJavier on 03/01/2016.
 */
public class Tarea {

    private long id;
    private String titulo;
    private String descripcion;
    //private String fecha; segunda parte

    public Tarea(long id, String titulo, String descripcion/*segunda parte, String fecha*/) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        //this.fecha = fecha; segunda parte
    }

    public Tarea(String titulo, String descripcion/*segunda parte, String fecha*/) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        //this.fecha = fecha; segunda parte
    }
    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /*
    //segunda partes
    public String getFecha() {
        return fecha;
    }
    */
}
