package com.example.icaro.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ConteudoActivity extends AppCompatActivity {

    private ArrayAdapter conteudoAdapter;
    private ListView lstConteudo;
    private ArrayList<String> lstConteudoPesquisado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstConteudo = findViewById(R.id.lstConteudo);

        lstConteudoPesquisado = new ArrayList<>();
        lstConteudoPesquisado.add("Conteúdo 1");
        lstConteudoPesquisado.add("Conteúdo 2");
        lstConteudoPesquisado.add("Conteúdo 3");
        lstConteudoPesquisado.add("Conteúdo 4");

        String s = getIntent().getStringExtra("CONTEUDO_PESQUISADO");
        lstConteudoPesquisado.add(s);

        conteudoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstConteudoPesquisado);
        lstConteudo.setAdapter(conteudoAdapter);

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
