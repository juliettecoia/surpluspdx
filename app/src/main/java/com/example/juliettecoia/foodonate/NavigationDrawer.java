package com.example.juliettecoia.foodonate;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by juliettecoia on 7/28/16.
 */
public class NavigationDrawer extends AppCompatActivity{

    public static TheAccount logged_in_user;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    int move;
    boolean foodBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null) {

            String userName = extras.getString("name");
            String userOrg = extras.getString("org");
            String userAddress = extras.getString("address");
            String userPhone = extras.getString("phone");
            String userEmail = extras.getString("email");
            //Boolean userAccptDon = extras.getBoolean("acceptDon");
            //String userFood = extras.getString("food");
            //String userVerified = extras.getString("verified");
            //logged_in_user = new TheAccount(userName, userOrg, userAddress, userPhone, userEmail, userAccptDon, userFood, userVerified);
            logged_in_user = new TheAccount(userName, userOrg, userAddress, userPhone, userEmail);
        }

       // foodBank = logged_in_user.getAcceptDon();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View hView = navigationView.getHeaderView(0);
        TextView name = (TextView) hView.findViewById(R.id.name2);
        name.setText(logged_in_user.getName());
        TextView email = (TextView) hView.findViewById(R.id.email);
        email.setText(logged_in_user.getEmail());

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new NeededFood());
        fragmentTransaction.commit();
        //if(foodBank == true)
            //getSupportActionBar().setTitle("Request");
       // else if(foodBank == false)
            getSupportActionBar().setTitle("FooDonate");

        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        /*if(MapsActivity.FBTitle != "empty")
            moveActivity();*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.donate_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new DonateFragment2());
                        fragmentTransaction.commit();
                       if(foodBank == true)
                           getSupportActionBar().setTitle("FooDonate");

                        else if(foodBank == false)
                           getSupportActionBar().setTitle("FooDonate");

                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_information:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new InformationFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("FooDonate");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.requested_food:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new NeededFood());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("FooDonate");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.map_id:
                        move = 1;
                        logOut();
                        break;

                    case R.id.log_out:
                        move = 2;
                        logOut();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void logOut ()
    {
        Intent intent;

        if(move == 2) {
            logged_in_user = null;
            //set text null
            intent = new Intent(this, HomeActivity.class);

        }
        else
            intent = new Intent(this, MapsActivityTwo.class);
        startActivity(intent);
    }

    /*public void moveActivity()
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new NeededFood());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("FooDonate");
        drawerLayout.closeDrawers();
    }*/

}