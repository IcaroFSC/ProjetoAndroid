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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import conexaobd.BlogDB;
import modelo.Post;

public class InserirPost extends AppCompatActivity {

    private Spinner comboAssuntos;
    private String[] assuntos = new String[] {"Tecnologia", "Tutoriais", "Notícias"};
    private EditText edtTitulo;
    private EditText edtConteudo;
    private Button btnSalvar;
    private Button btnLimpar;
    private String assuntoSelecionado;

    /*Dados do Banco de Dados*/
    private BlogDB blogDB;
    private Post post = new Post();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        blogDB = new BlogDB(this);

        comboAssuntos = findViewById(R.id.spinLstAssunto);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assuntos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboAssuntos.setAdapter(adaptador);

        comboAssuntos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                assuntoSelecionado = assuntos[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtTitulo = findViewById(R.id.edtConteudo);
        edtConteudo = findViewById(R.id.edtConteudo);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnCancelar);

        btnSalvar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                post.setAssunto(assuntoSelecionado);
                post.setTitulo(edtTitulo.getText().toString());
                post.setTexto(edtConteudo.getText().toString());
                blogDB.salvarPost(post);
                Toast.makeText(view.getContext(), "Post: " + edtTitulo.getText() + " / " + edtConteudo.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btnLimpar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                edtTitulo.setText("");
                edtConteudo.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(this, ListarPostActivity.class);
                startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
