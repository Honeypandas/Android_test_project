package com.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Context context=MainActivity.this;
        SharedPreferences sp=context.getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name","ywq");
        editor.commit();



    }
}
