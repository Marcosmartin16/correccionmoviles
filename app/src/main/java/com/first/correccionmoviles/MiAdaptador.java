package com.first.correccionmoviles;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {


    private TextView ultimaSeleccion;
    private int posicionUltimaSeleccion;

    private List<String> divisas;
    private LayoutInflater inflador;

    MiAdaptador(Context contexto, List<String> divisas){
        this.divisas = divisas;
        this.inflador= LayoutInflater.from(contexto);
        posicionUltimaSeleccion = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflador.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String divisa = divisas.get(position);
        holder.tvDivisa.setText(divisa);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView divisa = view.findViewById(R.id.tvDivisa);

                //desmarcar si esta marcado
                if(divisa == ultimaSeleccion){
                    posicionUltimaSeleccion = -1;
                    ultimaSeleccion = null;
                    divisa.setBackgroundColor(Color.WHITE);
                }else {
                    //si hay uno marcado antes lo desmarco
                    if(ultimaSeleccion != null) ultimaSeleccion.setBackgroundColor(Color.WHITE);
                    divisa.setBackgroundColor(Color.BLUE);
                    posicionUltimaSeleccion = position;
                    ultimaSeleccion = divisa;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return divisas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDivisa;

        ViewHolder(View item){
            super(item);
            tvDivisa = item.findViewById(R.id.tvDivisa);
        }
    }

    public int getElementoSeleccion() {
        return this.posicionUltimaSeleccion;
    }


}
