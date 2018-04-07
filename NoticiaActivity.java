package com.example.icaro.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import conexaobd.BlogDB;
import modelo.Comentario;
import modelo.Noticia;

public class NoticiaActivity extends AppCompatActivity {

    private Noticia noticia;
    private TextView txtTitulo;
    private TextView txtNoticia;
    private EditText edtNome;
    private EditText edtComentario;
    private ImageButton btnAdd;
    private ListView lstViewComentario;
    private ArrayList<Comentario> lstComentario = new ArrayList<Comentario>();
    private Comentario comentario;

    /*Dados do Banco de Dados*/
    private BlogDB blogDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blogDB = new BlogDB(this);

        final int idNoticia = getIntent().getIntExtra("IDNOTICIA", 0);
//        Log.i("NOTICIA", "ID: " + idNoticia);
        noticia = blogDB.consultarNoticia(idNoticia);
//        Log.i("NOTICIA", "DADOS: " + noticia.getAssunto());

        txtTitulo = findViewById(R.id.txtTitulo);
        txtNoticia = findViewById(R.id.txtTextoNoticia);

        txtTitulo.setText(noticia.getTitulo());
        txtNoticia.setText(noticia.getTexto());

        edtNome = findViewById(R.id.edtNome);
        edtComentario = findViewById(R.id.edtNoticia);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                comentario = new Comentario();
                comentario.setNome(edtNome.getText().toString());
                comentario.setTexto(edtComentario.getText().toString());
                comentario.setIdnoticia(idNoticia);
                blogDB.inserirComentario(comentario);
                Toast.makeText(getApplicationContext(), "Comentário inserido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        lstViewComentario = findViewById(R.id.lstComentario);

        lstComentario = blogDB.listarComentariosNoticia(idNoticia);

        ArrayAdapter<Comentario> comentarioAdapter = new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, lstComentario);
        lstViewComentario.setAdapter(comentarioAdapter);

    }

}
