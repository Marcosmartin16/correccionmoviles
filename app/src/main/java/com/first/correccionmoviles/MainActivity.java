package com.first.correccionmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> divisas;
    private ArrayList<Double> factoresCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaDivisas();
        inicializaFactoresCambio();

        RecyclerView rvDivisas = findViewById(R.id.rvDivisas);

        LinearLayoutManager managerLayout = new LinearLayoutManager(this);
        rvDivisas.setLayoutManager(managerLayout);

        MiAdaptador adaptador = new MiAdaptador(this, divisas);
        rvDivisas.setAdapter(adaptador);

        DividerItemDecoration decorador = new DividerItemDecoration(rvDivisas.getContext(), managerLayout.getOrientation());
        rvDivisas.addItemDecoration(decorador);

        Switch swVIP = (Switch) findViewById(R.id.switch1);
        swVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamar al metodo que hace el cambio
                actualizarCambio(swVIP.isChecked(), adaptador.getElementoSeleccion());
            }
        });

        Button bConvertir = (Button) findViewById(R.id.boton1);
        bConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamar al metodo que hace el cambio
                actualizarCambio(swVIP.isChecked(), adaptador.getElementoSeleccion());

            }
        });
    }

    public void actualizarCambio(boolean VIP, int cambioSeleccionado) {

        TextView tvResultado = findViewById(R.id.tvresult);

        if(cambioSeleccionado != -1){
            try {
                EditText etCantidad = (EditText) findViewById(R.id.importe);
                double euros = Double.parseDouble(etCantidad.getText().toString());
                double cambio = VIP? factoresCambio.get(cambioSeleccionado): factoresCambio.get(cambioSeleccionado) * 1.01;
                tvResultado.setText(" " + cambio * euros);
            }catch (NumberFormatException ex){
                tvResultado.setText("");
            }
        }else{
            tvResultado.setText("");
        }
    }

    private void inicializaFactoresCambio(){
        factoresCambio = new ArrayList<Double>();

        factoresCambio.add(1.1293946);
        factoresCambio.add(0.85447758);
        factoresCambio.add(1.4339265);
        factoresCambio.add(1.5788175);
        factoresCambio.add(128.17384);
        factoresCambio.add(85.36992);
        factoresCambio.add(1.6631981);
        factoresCambio.add(1.0441295);
        factoresCambio.add(18.030472);
        factoresCambio.add(83.219626);
    }

    private void inicializaDivisas(){

        divisas = new ArrayList<>();

        divisas.add("USD");
        divisas.add("GBP");
        divisas.add("CAD");
        divisas.add("AUD");
        divisas.add("JPY");
        divisas.add("INR");
        divisas.add("NZD");
        divisas.add("CHF");
        divisas.add("ZAR");
        divisas.add("RUB");
    }
}