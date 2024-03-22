package edu.psu.sweng888.animalshelter.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import edu.psu.sweng888.animalshelter.R;
import edu.psu.sweng888.animalshelter.fragments.FindNewHome;
import edu.psu.sweng888.animalshelter.fragments.Landing;
import edu.psu.sweng888.animalshelter.fragments.PetDirectory;
import edu.psu.sweng888.animalshelter.fragments.SearchDirectory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // declare class variables
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // initialize class variables
        drawerLayout = findViewById(R.id.nav_drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // set listener for NavigationView
        navigationView.setNavigationItemSelectedListener(this);

        // set-up the ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_closed);

        // include the ActionBarDrawerToggle as the listener to the DrawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // set the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Landing()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_pet_directory) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PetDirectory()).commit();
        } else if (id == R.id.nav_search_directory) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchDirectory()).commit();
        } else if (id == R.id.nav_find_new_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FindNewHome()).commit();
        }

        // close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu - this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.nav_drawer_items, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
