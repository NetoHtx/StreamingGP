package com.example.streaminggp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
git init


public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Filme>>,
        FilmeAdapter.FilmeItemClickListener {

    private static final int FILME_LOADER_ID = 1;

    private RecyclerView recyclerView;
    private FilmeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Criação do adaptador (vazio por enquanto)
        adapter = new FilmeAdapter(new ArrayList<>());
        adapter.setFilmeItemClickListener(this); // Definindo o ouvinte de clique
        recyclerView.setAdapter(adapter);

        // Inicializa o LoaderManager
        getSupportLoaderManager().initLoader(FILME_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<List<Filme>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FilmeLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Filme>> loader, List<Filme> filmes) {
        // Atualize o adaptador com os filmes após o término da execução em segundo plano
        adapter.setFilmes(filmes);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Filme>> loader) {
        // Limpe os dados quando o Loader for redefinido
        adapter.setFilmes(new ArrayList<>());
    }

    @Override
    public void onFilmeItemClick(Filme filme) {
        // iniciar uma nova atividade para exibir detalhes do filme

        Intent intent = new Intent(this, descripition.class);
        intent.putExtra("filme", (CharSequence) filme);
        startActivity(intent);
    }

    private static class FilmeLoader extends AsyncTaskLoader<List<Filme>> {
        public FilmeLoader(MainActivity context) {
            super(context);
        }

        @Nullable
        @Override
        public List<Filme> loadInBackground() {
            List<Filme> filmes = new ArrayList<>();

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                        .get()
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZDVmMjllYTA5YjVmZTQ5N2QwMjc0ODdkNjk0ODQ4OSIsInN1YiI6IjY1NWVhZWViMWRiYzg4MDEzYmUzZWQ2YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tE726x-nX7kW3I4HppdD0mXXSRAkdLEd3FZhdU_8c5M")
                        .addHeader("Experimental-API-Key", "0d5f29ea09b5fe497d027487d6948489")  // Adiciona a chave da API experimental
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    filmes = parseJSON(jsonData);
                } else {
                    // Tratar erro na resposta
                    Log.e("MainActivity", "Erro na resposta da API: " + response.code());
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return filmes;
        }

        private List<Filme> parseJSON(String jsonData) throws JSONException {
            List<Filme> filmes = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray resultsArray = jsonObject.optJSONArray("results");

            if (resultsArray != null) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject filmeJson = resultsArray.getJSONObject(i);

                    // Extrair informações do filme
                    String originalTitle = filmeJson.optString("original_title", "");
                    String overview = filmeJson.optString("overview", "");
                    String posterPath = filmeJson.optString("poster_path", "");

                    // Criar objeto Filme e adicioná-lo à lista
                    Filme filme = new Filme(originalTitle, overview, posterPath);
                    filmes.add(filme);
                }
            }

            return filmes;
        }
    }
}
