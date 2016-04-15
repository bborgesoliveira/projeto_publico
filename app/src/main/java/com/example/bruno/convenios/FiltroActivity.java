package com.example.bruno.convenios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class FiltroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerRegiao;
    Spinner spinnerUF;
    Spinner spinnerMunicipio;
    Spinner spinnerAnoInicio;
    Spinner spinnerAnoFim;
    Spinner spinnerSituacao;
    String regiaoEscolhida;
    String ufEscolhida;
    String municipioEscolhido;
    String anoInicioEscolhido;
    String anoFimEscolhido;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Impedindo teclado de abrir automaticamente
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inicializando Spinners
        spinnerRegiao = (Spinner) findViewById(R.id.spinnerRegiao);

        spinnerUF = (Spinner) findViewById(R.id.spinnerUF);
        spinnerUF.setVisibility(View.INVISIBLE);

        spinnerMunicipio = (Spinner) findViewById(R.id.spinnerMunicipio);
        spinnerMunicipio.setVisibility(View.INVISIBLE);

        spinnerAnoInicio = (Spinner) findViewById(R.id.spinnerAnoInicio);
        spinnerAnoFim = (Spinner) findViewById(R.id.spinnerAnoFim);

        spinnerSituacao = (Spinner) findViewById(R.id.spinnerSituacao);

        ArrayAdapter adapterRegiao = ArrayAdapter.createFromResource(this, R.array.regiao, R.layout.spinner_item);
        spinnerRegiao.setAdapter(adapterRegiao);
        spinnerRegiao.setOnItemSelectedListener(this);

        ArrayAdapter adapterUF = ArrayAdapter.createFromResource(this, R.array.uf, R.layout.spinner_item);
        spinnerUF.setAdapter(adapterUF);
        spinnerUF.setOnItemSelectedListener(this);

        ArrayAdapter adapterMunicipio = ArrayAdapter.createFromResource(this, R.array.municipio, R.layout.spinner_item);
        spinnerMunicipio.setAdapter(adapterMunicipio);
        spinnerMunicipio.setOnItemSelectedListener(this);

        ArrayAdapter adapterAnoInicio = ArrayAdapter.createFromResource(this, R.array.ano_inicio, R.layout.spinner_item);
        spinnerAnoInicio.setAdapter(adapterAnoInicio);
        spinnerAnoInicio.setOnItemSelectedListener(this);

        ArrayAdapter adapterAnoFim = ArrayAdapter.createFromResource(this, R.array.ano_fim, R.layout.spinner_item);
        spinnerAnoFim.setAdapter(adapterAnoFim);
        spinnerAnoFim.setOnItemSelectedListener(this);

        ArrayAdapter adapterSituacao = ArrayAdapter.createFromResource(this, R.array.situacao, R.layout.spinner_item);
        spinnerSituacao.setAdapter(adapterSituacao);
        spinnerSituacao.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView texto = (TextView) view;

        switch (parent.getId()){
            case R.id.spinnerRegiao:
                if (position != 0){
                    spinnerUF.setVisibility(View.VISIBLE);
                    regiaoEscolhida = texto.getText().toString();
                }
                else {
                    spinnerUF.setVisibility(View.INVISIBLE);
                    spinnerMunicipio.setVisibility(View.INVISIBLE);
                    regiaoEscolhida = "Brasil";
                }
                break;
            case R.id.spinnerUF:
                if (position != 0){
                    spinnerMunicipio.setVisibility(View.VISIBLE);
                    ufEscolhida = " - " + texto.getText().toString();
                }
                else{
                    spinnerMunicipio.setVisibility(View.INVISIBLE);
                    ufEscolhida = "";
                }
                break;
            case R.id.spinnerMunicipio:
                if (position != 0){
                    municipioEscolhido = texto.getText().toString();
                }
                else{
                    municipioEscolhido = "";
                }
                break;
            case R.id.spinnerAnoInicio:
                if(position != 0){
                    anoInicioEscolhido = texto.getText().toString();
                }
                else{
                    anoInicioEscolhido = "";
                }
                break;
            case R.id.spinnerAnoFim:
                if(position != 0){
                    anoFimEscolhido = " - " + texto.getText().toString();
                }
                else{
                    anoFimEscolhido = "";
                }
                break;
        }

        //Toast.makeText(this, "Você escolheu " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void AbreResumoActivity(View view){
        // Criando bundle para passar dados para ResumoActivity
        Bundle bundleRegiao = new Bundle();
        bundleRegiao.putString("regiao", regiaoEscolhida + ufEscolhida);

        // Criando intent e chamando ResumoActivity
        Intent resumoActivity = new Intent(this, ResumoActivity.class);
        resumoActivity.putExtras(bundleRegiao);
        resumoActivity.putExtra("municipio", municipioEscolhido);
        resumoActivity.putExtra("periodo", anoInicioEscolhido + anoFimEscolhido);
        startActivity(resumoActivity);
    }
}
