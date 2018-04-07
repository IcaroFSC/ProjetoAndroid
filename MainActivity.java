package com.example.icaro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//import conexaobd.HttpService;

public class MainActivity extends AppCompatActivity {

    private Button btnPost;
    private Button btnNoticia;
    private Button btnNovidade;
    private SearchView barraPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraPesquisa = findViewById(R.id.barraPesquisa);
        btnPost = findViewById(R.id.btnPost);
        btnNoticia = findViewById(R.id.btnNoticia);
        btnNovidade = findViewById(R.id.btnTutorial);

        btnPost.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), ListarPostActivity.class);
                startActivity(i);
            }
        });
        btnNoticia.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), ListarNoticiaActivity.class);
                startActivity(i);
            }
        });
        btnNovidade.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), NovidadeActivity.class);
                startActivity(i);
            }
        });

        // Binds the Adapter to the ListView
        barraPesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(), "PESQUISADO: " + s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ConteudoActivity.class);
                intent.putExtra("CONTEUDO_PESQUISADO", s);
                startActivity(intent);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                String text = "";
                return false;
            }
        });

    }

    /*@Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this, "PESQUISADO: " + s, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ConteudoActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text = "";
//        adapter.
        return false;
    }*/
}
