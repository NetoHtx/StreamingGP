package com.example.streaminggp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.streaminggp.MainActivity;
import com.example.streaminggp.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Após o tempo de exibição da tela splash, inicie a MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                // Fecha a atividade da tela splash para que o usuário não possa voltar a ela pressionando o botão "Back"
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
