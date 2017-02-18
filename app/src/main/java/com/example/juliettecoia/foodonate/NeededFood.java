package com.example.juliettecoia.foodonate;
//DISPLAYS THE REQUESTED FOOD//
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */

public class NeededFood extends android.support.v4.app.Fragment {
    public static String foodList = "";

    // public static StringBuilder donateSent = new StringBuilder();

    public NeededFood() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_needed_food, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showRequestFood();

    }

      /*
       * DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
       * final DatabaseReference newPost = mDatabase.push();
       * newPost.child("title".setValue(title_val);
       * newPost.child("desc").setValue(desc_val);
       */

    public void showRequestFood(){

        //original comment: one string for server from request food
        final TextView food = (TextView) getView().findViewById(R.id.editText5);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("donations");
        FirebaseAuth mAuth;
        String donations;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String email = user.getEmail();

        if(MapsActivity.FBTitle != "empty" && DonateFragment2.foodListt != "")

        {
            foodList += email;
            foodList += " is donating ";
            foodList += DonateFragment2.foodListt;
            foodList += " to ";
            foodList += MapsActivity.FBTitle;


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mRef = database.getReference("donation sentence");
            final HashMap<String, String> donateMap = new HashMap<>();
            donateMap.put("sentence", foodList);
            mRef.push().setValue(donateMap);


            DonateFragment2.foodListt = "";


        }
        food.append(foodList);



    }

/*
    public void showRequestFood() {
        final TextView food = (TextView) getView().findViewById(R.id.editText5);
        //One string for server
        //From request food

        //Toast.makeText(getActivity(), "test"+HomeActivity.food, Toast.LENGTH_SHORT).show();

        if (NavigationDrawer.logged_in_user.getAcceptDon() == true) {
            foodList += "$";
            foodList += NavigationDrawer.logged_in_user.getName();
            foodList += " is requesting ";
            foodList += HomeActivity.food;
            foodList += "$";
        } else
        //From donated food
        {
            foodList += "$";
            foodList += ActivityFragment.donateSent;

            if (NavigationDrawer.logged_in_user.getOrg().equals("")) {
                foodList += NavigationDrawer.logged_in_user.getName();
            } else {
                foodList += NavigationDrawer.logged_in_user.getOrg();
            }
            foodList += " is donating ";
            foodList += DonateFragment.foodListt;
            foodList += " to ";
            foodList += MapsActivity.FBTitle;
            foodList += "$";


        }



        food.append(foodList);

        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        backgroundTask.execute("food", NavigationDrawer.logged_in_user.getEmail(), foodList);

            /*food.append(food1);
            food.setTextSize(18);
            //add " - company name" when server is connected
            food.append("\n");*/


        //food.append(foodList);
        // food.append(donateSent);
    // */

    }

