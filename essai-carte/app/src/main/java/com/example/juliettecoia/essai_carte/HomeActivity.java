package com.example.juliettecoia.foodonate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by juliettecoia on 7/28/16.
 */
public class HomeActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void choose (View view){
        String button_text;
        button_text = ((Button) view).getText().toString();

        if(button_text.equals("login"))
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if (button_text.equals("create account"))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
