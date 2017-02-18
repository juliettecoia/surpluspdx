package com.example.juliettecoia.foodonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by juliettecoia on 7/28/16.
 */

public class LoginActivity extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Login";

    Button Login;
    Button psswrd;

    EditText USERNAME, USERPASS;
    String username, userpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Login = (Button) findViewById(R.id.btnLogin);

        USERNAME = (EditText) findViewById(R.id.editEmail);
        USERPASS = (EditText) findViewById(R.id.editPass);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = USERNAME.getText().toString();
                userpass = USERPASS.getText().toString();

                signIn(username, userpass);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //String uid = user.getUid();


                //Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                //startActivity(intent);

               // BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
               // backgroundTask.execute("login", username, userpass);

                //mConditionRef.setValue("");

            }
        });
//Why is this not going into the next activity? The app crashes when you click on Forgot Password
//What if the onClick was the other type? 
        psswrd = (Button) findViewById(R.id.forgotPassBtn);
        psswrd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Got to new activity
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
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
                // ...
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

    public void signIn (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                            //startActivity(intent);
                        }

                        // ...
                    }
                });

    }


}

