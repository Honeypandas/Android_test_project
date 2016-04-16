package com.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    TextView textView2;
    RatingBar ratingBar;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup= (RadioGroup) findViewById(R.id.Group);
        textView= (TextView) findViewById(R.id.textView);
        textView2= (TextView) findViewById(R.id.textView2);

        seekBar= (SeekBar) findViewById(R.id.seekBar);

        ratingBar= (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setNumStars(5);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView2.setText("分数:" + rating);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton: {
                        Log.e("ywq:", "C罗");break;
                    }
                    case R.id.radioButton2: {
                        Log.e("ywq:","贝尔");break;
                    }
                    case R.id.radioButton3:{
                        Log.e("ywq：","本泽马");break;
                    }
                }


            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("当前值：" + progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }
}
