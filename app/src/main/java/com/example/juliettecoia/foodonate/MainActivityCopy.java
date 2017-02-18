package com.example.juliettecoia.foodonate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.R.attr.name;

/**
 * Created by juliettecoia on 7/28/16.
 *
 * This is also the "Create Account Activity"
 */

public class MainActivityCopy extends FragmentActivity{
    Button REG;
    boolean user_accptdon;
    CheckBox USER_ACCPTDON;
    AlertDialog.Builder builder;
    EditText USER_NAME, USER_ORG, USER_PHONE, USER_ADDRESS, USER_EMAIL, USER_PASS;
    String user_name, user_org, user_phone, user_address, user_email, user_pass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Create Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        REG = (Button)findViewById(R.id.btnLogin);

        USER_NAME = (EditText)findViewById(R.id.edtName);
        USER_ORG = (EditText)findViewById(R.id.editEmail);
        USER_PHONE = (EditText)findViewById(R.id.edtPhone);
        USER_ADDRESS = (EditText)findViewById(R.id.edtAddress);
        USER_EMAIL = (EditText)findViewById(R.id.editPass);
        USER_PASS = (EditText)findViewById(R.id.edtPass);
        USER_ACCPTDON = (CheckBox)findViewById(R.id.chkAcceptDon);

        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_name = USER_NAME.getText().toString();
                user_org = USER_ORG.getText().toString();
                user_address = USER_ADDRESS.getText().toString();
                user_phone = USER_PHONE.getText().toString();
                user_email = USER_EMAIL.getText().toString();
                user_pass = USER_PASS.getText().toString();
                user_accptdon = USER_ACCPTDON.isChecked(); //convert the boolean to a string before it is sent to the Hash Map

                createAccount(user_email, user_pass, user_name, user_org, user_address, user_phone);

                //..This is not working AT ALL :(

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //String uid = user.getUid();

                DatabaseReference parentRef = database.getReference("change to uid later");
                final HashMap<String, String> mapHash = new HashMap<>();
                mapHash.put("name", user_name);
                mapHash.put("organization", user_org);
                mapHash.put("address", user_address);
                mapHash.put("phone", user_phone);
                parentRef.push().setValue(mapHash);

                //  parentRef.child("message").setValue("help me");

                openApp();
                // boolean entries = validEntries(user_name, user_address, user_phone, user_email, user_pass);

                //Bool is converted to String here
                // if(entries)
                // {
                //      String acceptsDon;
                //     if(user_accptdon) {
                //         acceptsDon = "true"; }
                //     else {
                //         acceptsDon = "false"; }
                // }
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(String email, String password, String name, String organization, String address, String phone) {
        Log.d(TAG, "createAccount:" + email);
        /*if (!validEntries()) {
            return;
        }*/
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivityCopy.this, "auth failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivityCopy.this, "account created successfully",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });

//removed from here --> openApp();

        // [END create_user_with_email]
    }

    private void openApp(){

        Intent intent = new Intent(MainActivityCopy.this, NavigationDrawer.class);
        Bundle nextras = new Bundle();
        nextras.putString("name",user_name);
        nextras.putString("org", user_org);
        nextras.putString("address", user_address);
        nextras.putString("phone", user_phone);
        nextras.putString("email", user_email);
        intent.putExtras(nextras);
        startActivity(intent);
    }

    private boolean validEntries(String name, String address, String phone, String email, String pass)
    {
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(address)|| TextUtils.isEmpty(phone)||TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)) {

            builder = new AlertDialog.Builder(MainActivityCopy.this);
            builder.setTitle("Something went wrong...");
            builder.setMessage("Please fill in all fields..");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return false;
        }
        if(!isPhoneValid(phone))
        {
            Toast.makeText(getBaseContext(), "------Invalid Phone Number------", Toast.LENGTH_LONG).show();
            USER_PHONE.setText("");
            return false;
        }
        if (!isEmailValid(email))
        {
            Toast.makeText(getBaseContext(), "------Invalid Email------", Toast.LENGTH_LONG).show();
            USER_EMAIL.setText("");
            return false;
        }
        if (!isPasswordValid(pass))
        {
            Toast.makeText(getBaseContext(), "------Password Too Short------" , Toast.LENGTH_LONG).show();
            USER_PASS.setText("");
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isPhoneValid(String phone)
    {
        return phone.length() == 10;
    }

}