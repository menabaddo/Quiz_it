package com.example.quizit;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.example.quizit.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =

            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {


                    switch (item.getItemId()){
                        case R.id.nav_home:
                            setFragement(new CategoryFragment());
                            return true;

                        case R.id.nav_leaderboard:
                            setFragement(new LeaderBoardFragment());
                            return true;

                        case R.id.nav_account:
                            setFragement(new AccountFragment());
                            return true;

                    }

                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        main_frame = findViewById(R.id.main_frame);


        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;







        setFragement(new CategoryFragment());
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setFragement(Fragment fragement){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(),fragement);
        transaction.commit();

    }
}