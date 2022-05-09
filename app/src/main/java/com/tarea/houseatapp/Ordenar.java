package com.tarea.houseatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Ordenar extends AppCompatActivity {

    String[] cantidad = {"1", "2", "3", "4", "5", "6"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenar);

        autoCompleteTextView = findViewById(R.id.cantidad);

        adapter = new ArrayAdapter<String>(this, R.layout.cantidades, cantidad);
        autoCompleteTextView.setAdapter(adapter);
    }

    public void Orden(View view){
        Intent orden = new Intent(this, OrdenExitosa.class);
        startActivity(orden);
    }
}