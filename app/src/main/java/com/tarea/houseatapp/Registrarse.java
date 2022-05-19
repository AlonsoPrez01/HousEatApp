package com.tarea.houseatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registrarse extends AppCompatActivity {

    Button btnRegistrar;
    EditText nombre, apellido, telefono, direccion, correo, contra, confirmarContra;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        nombre = findViewById(R.id.txt_nombre);
        apellido = findViewById(R.id.txt_apellido);
        telefono = findViewById(R.id.txt_telefono);
        direccion = findViewById(R.id.txt_direccion);
        correo = findViewById(R.id.txt_registrar_correo);
        contra = findViewById(R.id.txt_registrar_contra);
        confirmarContra = findViewById(R.id.txt_confirmar_contra);
        btnRegistrar = findViewById(R.id.btn_registrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUser = nombre.getText().toString().trim();
                String apellidoUser = apellido.getText().toString().trim();
                String telUser = telefono.getText().toString().trim();
                String direccionUser = direccion.getText().toString().trim();
                String correoUser = correo.getText().toString().trim();
                String contraUser = contra.getText().toString().trim();
                String confirmarUser = confirmarContra.getText().toString().trim();

                if(nombreUser.isEmpty() && apellidoUser.isEmpty() && telUser.isEmpty() && direccionUser.isEmpty() && correoUser.isEmpty() && contraUser.isEmpty() && confirmarUser.isEmpty()){
                    Toast.makeText(Registrarse.this, "Por favor ingrese los datos.", Toast.LENGTH_SHORT).show();
                }
                else if(!contraUser.equals(confirmarUser)) {
                    Toast.makeText(Registrarse.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else{
                    registrarUsuario(nombreUser, apellidoUser, telUser, direccionUser, correoUser, confirmarUser);
                }
            }
        });
    }

    private void registrarUsuario(String nombreUser, String apellidoUser, String telUser, String direccionUser, String correoUser, String confirmarUser) {
        mAuth.createUserWithEmailAndPassword(correoUser, confirmarUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("Nombre", nombreUser);
                map.put("Apellido", apellidoUser);
                map.put("Teléfono", telUser);
                map.put("Dirección", direccionUser);
                map.put("Correo", correoUser);
                map.put("Contraseña", confirmarUser);

                mFirestore.collection("Usuarios").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Registrarse.this, MainActivity.class));
                        Toast.makeText(Registrarse.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registrarse.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registrarse.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}