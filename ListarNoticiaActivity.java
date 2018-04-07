package com.example.icaro.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import conexaobd.BlogDB;
import modelo.Noticia;

public class ListarNoticiaActivity extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<Noticia> lista;
    private ArrayAdapter<String> adaptador;
    private ArrayAdapter<Noticia> noticiasDiaAdpater;
    private ArrayList<String> assuntos = new ArrayList<String>();
    private Spinner combo;

    /*Dados do Banco de Dados*/
    private BlogDB blogDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_noticia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blogDB = new BlogDB(this);

        lstView = findViewById(R.id.lstViewConteudo);
        assuntos.add("Tecnologias");
        assuntos.add("Qualidade de Software");
        assuntos.add("Redes de Computadores");
        assuntos.add("Desenvolvimento de Software");
        assuntos.add("Teste de Software");
        assuntos.add("Gerência");
        assuntos.add("Engenharia de Software");

        combo = findViewById(R.id.spinnerNoticias);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assuntos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo.setAdapter(adaptador);

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(view.getContext(), "Voce selecionou: " + assuntos.get(i), Toast.LENGTH_SHORT);
                toast.show();
                /*Buscar no banco*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lista = blogDB.listarNoticias();
//        lista.add("Conteúdo 1");
//        lista.add("Conteúdo 2");
//        lista.add("Conteúdo 3");
//        lista.add("Conteúdo 4");

        //Adaptador customizado
        noticiasDiaAdpater = new ArrayAdapter<Noticia>(this, android.R.layout.simple_list_item_1, lista);

        lstView = (ListView) findViewById(R.id.lstNoticias);
        lstView.setAdapter(noticiasDiaAdpater);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent noticiaIntent = new Intent(view.getContext(), NoticiaActivity.class);
                noticiaIntent.putExtra("IDNOTICIA",lista.get(i).getId());
                startActivity(noticiaIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
