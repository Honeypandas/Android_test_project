package com.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Ywq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ywq);


        Bundle bundle=this.getIntent().getExtras();
        String ywq=bundle.getString("NAME");
        Log.e("Name:", ywq);

    }
}
