package com.tarea.houseatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button iniciar_sesion;
    EditText correo, contra;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        correo = findViewById(R.id.txtCorreo);
        contra = findViewById(R.id.txtPassword);

        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoUser = correo.getText().toString().trim();
                String contraUser = contra.getText().toString().trim();

                if(correoUser.isEmpty() && contraUser.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show();

                }else{
                    loginUser(correoUser, contraUser);
                }
            }
        });
    }

    private void loginUser(String correoUser, String contraUser){
        mAuth.signInWithEmailAndPassword(correoUser, contraUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, Dashboard.class));
                    Toast.makeText(MainActivity.this, "Incio de sesión exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Intente de nuevo", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error al iniciar sesión, intente de nuevo", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void olvidoContra(View view){
        Intent recuperarContra = new Intent(this, RecuperarContra.class);
        startActivity(recuperarContra);
    }

    public void registrarUsuario(View view){
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }
}