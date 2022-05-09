package com.tarea.houseatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuRestaurante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurante);
    }

    public void Atras(View view){
        Intent atras = new Intent(this, Dashboard.class);
        startActivity(atras);
    }

    public void Ordenar(View View){
        Intent ordenar = new Intent(this, Ordenar.class);
        startActivity(ordenar);
    }
}