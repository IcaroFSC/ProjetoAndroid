package com.example.icaro.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import conexaobd.BlogDB;
import modelo.Post;

public class ListarPostActivity extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<String> lista;
//    private ArrayAdapter<Post> adapter;
    private ArrayAdapter<Post> adapter;
    private ArrayList<String> assuntos = new ArrayList<String>();
    private Spinner combo;
    private ImageButton btnAdd;

    /*Dados do Banco de Dados*/
    private BlogDB blogDB;
    private ArrayList<Post> lstPost = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blogDB = new BlogDB(this);

        btnAdd = findViewById(R.id.btnAdd);

        assuntos.add("Tutorial Rest API");
        assuntos.add("NodeJS");
        assuntos.add("WebSockets");

        combo = findViewById(R.id.spinLstAssunto);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assuntos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo.setAdapter(adaptador);

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(view.getContext(), "Voce selecionou: " + assuntos.get(i), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lstPost = blogDB.listarPost();
//        lista = new ArrayList<>();
//        for(Post p : lstPost){
//            lista.add(p.getTitulo());
//        }

        //Adaptador customizado
//        adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1, lstPost);
        adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1, lstPost);

        lstView = findViewById(R.id.lstViewConteudo);
        lstView.setAdapter(adapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent postIntent = new Intent(view.getContext(), PostActivity.class);
                Log.i("ID","ID: " + lstPost.get(i).getId());
                postIntent.putExtra("IDPOST", lstPost.get(i).getId());
                startActivity(postIntent);
            }
        });


        btnAdd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(view.getContext(), InserirPost.class);
                startActivity(postIntent);
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
