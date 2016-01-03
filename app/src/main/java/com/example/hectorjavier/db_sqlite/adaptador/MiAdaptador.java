package com.example.hectorjavier.db_sqlite.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hectorjavier.db_sqlite.R;
import com.example.hectorjavier.db_sqlite.modelos.Tarea;

import java.util.List;

/**
 * Created by HectorJavier on 17/12/2015.
 */
public abstract class MiAdaptador extends BaseAdapter {

    List<?> elementos;
    int vista;
    Context context;

    public MiAdaptador(List<?> elementos, int vista, Context context) {
        this.elementos = elementos;
        this.vista = vista;
        this.context = context;
    }

    public abstract View paraElElemento(Object elemento, View view);

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Override
    public Object getItem(int position) {
        return elementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(vista,null);
        }

        paraElElemento(elementos.get(position),view);

        return view;
    }
}
