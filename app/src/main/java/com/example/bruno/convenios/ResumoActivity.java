package com.example.bruno.convenios;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResumoActivity extends AppCompatActivity {

    TextView textViewApresentaRegiaoUF;
    TextView textViewApresentaMunicipio;
    TextView textViewApresentaPeriodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewApresentaRegiaoUF = (TextView) findViewById(R.id.textViewApresentaRegiaoUF);
        textViewApresentaMunicipio = (TextView) findViewById(R.id.textViewApresentaMunicipio);
        textViewApresentaPeriodo = (TextView) findViewById(R.id.textViewApresentaPeriodo);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textViewApresentaRegiaoUF.setText(extras.getString("regiao"));
            textViewApresentaMunicipio.setText(extras.getString("municipio"));
            textViewApresentaPeriodo.setText(extras.getString("periodo"));
        }

    }

    public void AbreTabelaConveniosActivity (View view){
        Intent tabelaActivity = new Intent(this, TabelaActivity.class);
        startActivity(tabelaActivity);
    }

    public void Infos (View view){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ResumoActivity.this);
        switch (view.getId()){
            case R.id.buttonInfoConvenios:
                dialogo.setTitle("Convênio");
                dialogo.setMessage(R.string.infoConvenios);
                break;
            case R.id.buttonInfoVlGlobal:
                dialogo.setTitle("Valor Global");
                dialogo.setMessage(R.string.infoValorGlobal);
                break;
            case R.id.buttonInfoVlLiberado:
                dialogo.setTitle("Valor Liberado");
                dialogo.setMessage(R.string.infoValorLiberado);
                break;
        }
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
