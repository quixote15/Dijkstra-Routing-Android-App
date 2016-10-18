package com.example.alanalucia.airplane;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alanalucia.airplane.data_processing.Dijkstra;
import com.example.alanalucia.airplane.data_processing.Grafo;

public class Rota extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner destino;
    Button go_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        spinner = (Spinner) findViewById(R.id.spinner);
        destino = (Spinner) findViewById(R.id.spinner2);
        go_btn = (Button) findViewById(R.id.go_button);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.cidades,R.layout.simple_spinner);
        spinner.setAdapter(adapter);
        destino.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        destino.setOnItemSelectedListener(this);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!spinner.getSelectedItem().equals(destino.getSelectedItem())){
                    String cidade = spinner.getSelectedItem().toString();
                    int vertice = spinner.getSelectedItemPosition();
                    int v_final =destino.getSelectedItemPosition();

                    Log.i("Origem",cidade);
                    Grafo g = new Grafo(16);
                    Dijkstra dk = new Dijkstra();
                    int[] r = Dijkstra.dijkstra(g, 0);
                    g.setDistancia(r);
                    int[] path = g.getPath(v_final);
                    String conections = "Rota: ";
                    for(int x: path){
                        if(x != -1)
                            conections+= spinner.getItemAt(x).toString();
                        else break;
                    }

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(id == R.id.spinner)
            Toast.makeText(this, "Você escolheu a cidade de Origem! Agora qual o seu destino?", Toast.LENGTH_SHORT).show();
        else if(id == R.id.spinner2)
            Toast.makeText(this, "Você escolheu a cidade de Destino! Agora Vamos calcular sua rota?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
