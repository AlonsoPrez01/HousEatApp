package com.tarea.houseatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void olvidoContra(View view){
        Intent recuperarContra = new Intent(this, RecuperarContra.class);
        startActivity(recuperarContra);
    }

    public void registrarUsuario(View view){
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

    public void iniciarSesion(View view){
        Intent iniciar_sesion = new Intent(this, Dashboard.class);
        startActivity(iniciar_sesion);
    }
}