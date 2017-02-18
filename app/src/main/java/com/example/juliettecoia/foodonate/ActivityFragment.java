package com.example.juliettecoia.foodonate;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends android.support.v4.app.Fragment {

    public static String donateSent ="";
    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);


    }

    private void populateListView(){
        //Create list of items

            donateSent += NavigationDrawer.logged_in_user.getName();
            donateSent += " is donating ";
            donateSent += DonateFragment.foodListt;
            donateSent += " to ";
            donateSent += MapsActivity.FBTitle;
            donateSent += "$";


        //ListView list = (ListView) getActivity().findViewById(R.id.listView);
    }
}
