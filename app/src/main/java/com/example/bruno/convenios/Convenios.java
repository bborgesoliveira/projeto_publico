package com.example.bruno.convenios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.TextView;

public class Convenios extends AppCompatActivity {

    ProgressBar progressBarInicio;
    int progresso = 0;

    long startTime = 0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int segundos = (int)(millis / 1000);
            int minutos = segundos / 60;
            segundos = segundos % 60;

            timerHandler.postDelayed(this, 500);

            if(segundos < 4){
                // Incrementa progressBar
                progressBarInicio.setProgress(progresso);
                progresso = progresso + 15;
            }
            else{
                // Para timer
                timerHandler.removeCallbacks(timerRunnable);

                AbreFiltroView();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBarInicio = (ProgressBar) findViewById(R.id.progressBarInicio);

        // Inicializando Timer para preencher a progressBar por 5 segundos
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_convenios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AbreFiltroView(){
        Intent FiltroActivity = new Intent(this, FiltroActivity.class);
        startActivity(FiltroActivity);
        finish();
    }
}
