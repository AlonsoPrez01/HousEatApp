package com.tarea.houseatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.tarea.houseatapp.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reemplazarFragment(new InicioFragment());

        Toolbar mToolbar =findViewById(R.id.topAppBar);
        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();

        binding.bottomNavigation.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){

                case R.id.inicio:
                    reemplazarFragment(new InicioFragment());
                    break;
                case R.id.buscar:
                    reemplazarFragment(new BuscarFragment());
                    break;
                case R.id.pedidos:
                    reemplazarFragment(new PedidosFragment());
                    break;
                case R.id.perfil:
                    reemplazarFragment(new PerfilFragment());
                    break;
            }

            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void reemplazarFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void cerrarSesion(MenuItem item){
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        finish();
        startActivity(new Intent(Dashboard.this, MainActivity.class));
        Toast.makeText(Dashboard.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}