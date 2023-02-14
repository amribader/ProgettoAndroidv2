package com.example.simplenav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.CommucationController.onSidReadyListener;
import com.example.simplenav.DB.PictureDB.Sid;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //private static final Object PREFS_NAME = ;
    public static final String mypreference = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.another2, R.id.profilo,R.id.utentiSeguiti,R.id.mapsFragment)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if(sharedpreferences.contains("sid")){
            //ce sullo storage
            System.err.println("sif già presente sullo storage");
            System.err.println("sif già presente sullo storage"+sharedpreferences.getString("sid",""));


        }else{
            SharedPreferences.Editor editor = sharedpreferences.edit();

            communicationController.register(body -> {
                editor.putString("sid",body.getSid());
                editor.commit();
            });
            //non ce sullo storage
            System.err.println("sif non presente sullo storage NONONO");
        }

        System.err.println("prova SID"+sharedpreferences.contains("sid"));
        System.err.println("prova SID"+sharedpreferences.getString("sid",""));




    }
}