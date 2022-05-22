package com.example.practicafinal.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.practicafinal.R;
import com.example.practicafinal.adapters.AdaptadorFragments;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ViewPager pager;
    private AdaptadorFragments adaptadorFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
             this,
             drawerLayout,
             toolbar,
             0,
             0
        );

         drawerLayout.addDrawerListener(drawerToggle);
         drawerToggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.navigation_view);
         navigationView.setNavigationItemSelectedListener(this);

         pager = findViewById(R.id.view_pager);
         adaptadorFragments = new AdaptadorFragments(getSupportFragmentManager());
         pager.setAdapter(adaptadorFragments);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_juegos:
                pager.setCurrentItem(1);
                toolbar.setTitle("Juegos");
                break;
            case R.id.nav_ofertas:
                pager.setCurrentItem(2);
                toolbar.setTitle("Ofertas");
                break;
            case R.id.nav_carrito:
                pager.setCurrentItem(3);
                toolbar.setTitle("Carrito");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}