package com.tarea.houseatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.tarea.houseatapp.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reemplazarFragment(new InicioFragment());

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

    private void reemplazarFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}