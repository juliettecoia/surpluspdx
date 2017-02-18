package com.example.juliettecoia.foodonate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by juliettecoia on 7/28/16.
 */
public class MainActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeText (View view){
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Submit"))
        {
            Intent intent = new Intent(this, MenuConfirmActivity.class);
            startActivity(intent);
        }

        //String message = "Done";
        //textview.setText(message);
    }
}
