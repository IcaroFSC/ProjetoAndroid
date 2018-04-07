package conexaobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;

import modelo.Comentario;
import modelo.Noticia;
import modelo.Post;


public class BlogDB {

    protected SQLiteDatabase db;

    public BlogDB(Context ctx){
        db = ctx.openOrCreateDatabase("Blog.db", Context.MODE_PRIVATE, null);
    }

    public void salvarPost(Post post){
        ContentValues valores = new ContentValues();
//        valores.put("id", 1);
        valores.put("assunto", post.getAssunto());
        valores.put("titulo", post.getTitulo());
        valores.put("conteudo", post.getTexto());
        db.insert("post", null, valores);
    }

    public Post consultarPost(int idP){
        Cursor c = db.rawQuery("select id, assunto, titulo, conteudo from Post where id = "+idP, null);
        c.moveToFirst();
        Post p = new Post();
        if(c.getCount()>0){
            p.setId(c.getInt(0));
            p.setAssunto(c.getString(1));
            p.setTitulo(c.getString(2));
            p.setTexto(c.getString(3));
        }
        return p;
    }

    public ArrayList<Post> listarPost(){
        Cursor c = db.rawQuery("select id, assunto, titulo, conteudo from Post", null);
        ArrayList<Post> lstPosts = new ArrayList<Post>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int titulo = c.getColumnIndex("titulo");
            int conteudo = c.getColumnIndex("conteudo");
            Log.i("ID", "ID: " + id);
            Post p = new Post();
            p.setId(c.getInt(id));
            Log.i("ID", "ID: " + p.getId());
            p.setTitulo(c.getString(titulo));
            p.setTexto(c.getString(conteudo));

            lstPosts.add(p);
            c.moveToNext();
        }
        return lstPosts;
    }

    public void salvarNoticia(Noticia noticia){
        ContentValues valores = new ContentValues();
//        valores.put("id", 1);
        valores.put("assunto", noticia.getAssunto());
        valores.put("titulo", noticia.getTitulo());
        valores.put("texto", noticia.getTexto());
        db.insert("noticia", null, valores);
    }

    public Noticia consultarNoticia(int idN){
        Cursor c = db.rawQuery("select id, assunto, titulo, texto from Noticia where id="+idN, null);
        c.moveToFirst();
        Noticia n = new Noticia();
        if(c.getCount()>0){
            n.setId(c.getInt(0));
            n.setAssunto(c.getString(1));
            n.setTitulo(c.getString(2));
            n.setTexto(c.getString(3));
        }
        return n;
    }

    public ArrayList<Noticia> listarNoticias(){
        Cursor c = db.rawQuery("select id, assunto, titulo, texto from Noticia", null);
        ArrayList<Noticia> lstNoticia = new ArrayList<Noticia>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int assunto = c.getColumnIndex("assunto");
            int titulo = c.getColumnIndex("titulo");
            int texto = c.getColumnIndex("texto");

            Noticia noticia = new Noticia();
            noticia.setId(c.getInt(id));
            noticia.setAssunto(c.getString(assunto));
            noticia.setTitulo(c.getString(titulo));
            noticia.setTexto(c.getString(texto));

            lstNoticia.add(noticia);
            c.moveToNext();
        }
        return lstNoticia;
    }

    public ArrayList<Post> novidadesPost(){
        Cursor c = db.rawQuery("select id, assunto, titulo, conteudo from Post order by id desc", null);
        ArrayList<Post> lstPosts = new ArrayList<Post>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int titulo = c.getColumnIndex("titulo");
            int assunto = c.getColumnIndex("assunto");
            int conteudo = c.getColumnIndex("conteudo");

            Post p = new Post();
            p.setId(c.getInt(id));
            p.setAssunto(c.getString(assunto));
            p.setTitulo(c.getString(titulo));
            p.setTexto(c.getString(conteudo));

            lstPosts.add(p);
            c.moveToNext();
        }
        return lstPosts;
    }

    public ArrayList<Noticia> novidadesNotícias(){
        Cursor c = db.rawQuery("select id, assunto, titulo, texto from Noticia order by id desc", null);
        ArrayList<Noticia> lstNoticia = new ArrayList<Noticia>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int assunto = c.getColumnIndex("assunto");
            int titulo = c.getColumnIndex("titulo");
            int texto = c.getColumnIndex("texto");

            Noticia noticia = new Noticia();
            noticia.setId(c.getInt(id));
            noticia.setAssunto(c.getString(assunto));
            noticia.setTitulo(c.getString(titulo));
            noticia.setTexto(c.getString(texto));

            lstNoticia.add(noticia);
            c.moveToNext();
        }
        return lstNoticia;
    }

    public void inserirComentario(Comentario comentario){
        ContentValues valores = new ContentValues();
//        valores.put("id", 1);
        valores.put("nome", comentario.getNome());
        valores.put("texto", comentario.getTexto());
        if(comentario.getIdpost()>0)
            valores.put("idpost", comentario.getIdpost());
        if(comentario.getIdnoticia()>0)
            valores.put("idnoticia", comentario.getIdnoticia());
        db.insert("comentario", null, valores);
    }

    public void excluirComentario(Comentario comentario){
        db.delete("comentario", "id="+comentario.getId(), null);
    }

    public ArrayList<Comentario> listarComentariosPost(int idP){
        Cursor c = db.rawQuery("select nome, texto from Comentario where idpost = " + idP, null);
        ArrayList<Comentario> lstComentario = new ArrayList<Comentario>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int nome = c.getColumnIndex("nome");
            int texto = c.getColumnIndex("texto");
            int idPost = c.getColumnIndex("idPost");

            Comentario coment = new Comentario();
            coment.setNome(c.getString(texto));
            coment.setTexto(c.getString(nome));

            lstComentario.add(coment);
            c.moveToNext();
        }
        return lstComentario;
    }

    public ArrayList<Comentario> listarComentariosNoticia(int idN){
        Cursor c = db.rawQuery("select nome, texto from Comentario where idnoticia = " + idN, null);
        ArrayList<Comentario> lstComentario = new ArrayList<Comentario>();
        c.moveToFirst();

        while(c.isAfterLast() == false){
            int id = c.getColumnIndex("id");
            int nome = c.getColumnIndex("nome");
            int texto = c.getColumnIndex("texto");
            int idNoticia = c.getColumnIndex("idNoticia");

            Comentario coment = new Comentario();
            coment.setNome(c.getString(texto));
            coment.setTexto(c.getString(nome));

            lstComentario.add(coment);
            c.moveToNext();
        }
        return lstComentario;
    }

}
