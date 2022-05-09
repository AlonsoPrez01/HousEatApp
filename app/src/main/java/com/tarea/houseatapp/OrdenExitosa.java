package com.tarea.houseatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OrdenExitosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_exitosa);
    }

    public void Aceptar(View view){
        Intent aceptar = new Intent(this, Dashboard.class);
        startActivity(aceptar);
    }
}