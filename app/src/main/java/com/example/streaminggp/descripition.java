package com.example.streaminggp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class descripition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripition);

        // Verifica se a Intent possui extras
        if (getIntent().hasExtra("filme")) {
            // Recupera os detalhes do filme da Intent
            Filme filme = (Filme) getIntent().getSerializableExtra("filme");

            // Configura os elementos do layout com base nos detalhes do filme
            TextView textTitleDescription = findViewById(R.id.textTitleDescription);
            TextView textTitle1 = findViewById(R.id.textTitle1);
            TextView textDetail1 = findViewById(R.id.textDetail1);

            textTitleDescription.setText(filme.getTitulo());
            textTitle1.setText("Original Title: " + filme.getTitulo());
            textDetail1.setText("Overview: " + filme.getDescricao());
        } else {
            // Caso não tenha detalhes do filme, pode tratar como desejar
            // Exemplo: fecha a atividade ou exibe uma mensagem de erro
            finish();
        }
    }
}
