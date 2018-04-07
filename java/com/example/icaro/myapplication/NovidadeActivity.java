package com.example.icaro.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import conexaobd.BlogDB;
//import conexaobd.HttpService;
//import controle.RetrofitConfig;
import modelo.Post;
//import modelo.CEP;
//import modelo.Carro;
//import modelo.Carros;
import modelo.Noticia;
//import remote.APIService;
//import remote.ApiUtils;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

public class NovidadeActivity extends AppCompatActivity {

    private ListView lstViewNovidade;
//    private List lstCarros;
//    private TextView mResponseTv;
//    private APIService mAPIService;
//    private TextView resposta;

    /*Dados do Banco de Dados*/
    private BlogDB blogDB;
    private ArrayList<Post> lstPost = new ArrayList<Post>();
    private ArrayList<Noticia> lstNoticia = new ArrayList<Noticia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novidade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blogDB = new BlogDB(this);
        lstViewNovidade = findViewById(R.id.lstNovidade);

        lstPost = blogDB.novidadesPost();
        lstNoticia = blogDB.novidadesNotícias();

        ArrayList<String> lstNovidades = new ArrayList<String>();
        lstNovidades.add(lstPost.get(0).getTitulo());
        lstNovidades.add(lstNoticia.get(0).getTitulo());

        ArrayAdapter<String> novidadesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstNovidades);
        lstViewNovidade.setAdapter(novidadesAdapter);

        /*try {
            ArrayList<Carro> retorno= new HttpService().execute().get();
            Log.i("JSON ", "RETORNO: " + retorno);
            lstCarros = new ArrayList();
            lstCarros.add(retorno.get(0));
            lstCarros.add(retorno.get(1));
            lstCarros.add(retorno.get(2));
            ArrayAdapter<Carro> adapter = new ArrayAdapter<Carro>(this, android.R.layout.simple_list_item_1, lstCarros);

            lstNovidade = findViewById(R.id.lstNovidade);
            lstNovidade.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        /*final EditText cep = findViewById(R.id.etMain_cep);
        resposta = findViewById(R.id.etMain_resposta);
        Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // aqui devemos utilizar o retrofit
//                new RetrofitConfig().getCEPService().buscarCEP(cep.getText().toString());
                pesquisarCEP(cep.getText().toString());
            }
        });*/

        /*final EditText titleEt = findViewById(R.id.et_title);
        final EditText bodyEt = findViewById(R.id.et_body);
        Button submitBtn = findViewById(R.id.btn_submit);
        mResponseTv = findViewById(R.id.tv_response);
        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){
                    sendPost(title, body);
                }
            }
        });*/

    }

    /*public void pesquisarCEP(String cep){
        new RetrofitConfig().getCEPService().buscarCEP(cep).enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                CEP cep = response.body();
                resposta.setText(cep.toString());
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                Log.e("CEPService   ", "Erro ao buscar o cep:" + t.getMessage());
            }
        });
    }*/

    /*public void sendPost(String title, String body){
        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                showResponse(response.body().toString());
                Log.i("WEB SERVICE", "Post enviado para API." + response.body().toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("WEB SERVICE", "Post não enviado para a API.");
            }
        });
    }

    public void showResponse(String response){
        if(mResponseTv.getVisibility() == View.GONE){
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }*/

}
