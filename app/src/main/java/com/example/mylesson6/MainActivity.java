package com.example.mylesson6;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.mylesson6.observe.Publisher;
import com.example.mylesson6.ui.AboutFragment;
import com.example.mylesson6.ui.WeekdayEngNoteFragment;
import com.example.mylesson6.ui.WeekdayFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Navigation navigation ;
    private final Publisher publisher = new Publisher();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbarAndDrawer();
        navigation = new Navigation(getSupportFragmentManager());
        getNavigation().addFragment(WeekdayFragment.newInstance(), true);








        //создаю фрагмент
        WeekdayFragment weekdayFragment = new WeekdayFragment();
        //вызов FragmentManadger
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, weekdayFragment);
        //fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.eng_container, WeekdayEngNoteFragment.newInstance(0)).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new WeekdayFragment()).commit();
        }
    }







    private void initToolbarAndDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Fragment backStackFragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (backStackFragment instanceof WeekdayFragment) {
            onBackPressed();
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Publisher getPublisher() {
        return publisher;
    }



    private void initDrawer(Toolbar toolbar){
        final DrawerLayout drawerLayout = findViewById(R.id.drawers);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name,
                R.string.exit);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                switch (id){
                    case R.id.add_said:
                        openAboutFragment();
                        drawerLayout.closeDrawers();
                        return (true);
                    case R.id.eexit_said:

                        finish();
                        return (true);
                }
                return false;
            }
        });


    }

    private void openAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, new AboutFragment()).commit();
    }





    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);

        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .add(R.id.fragment_container, new AboutFragment()).commit();
                return true;
            case R.id.eexit:
                finish();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }


}










