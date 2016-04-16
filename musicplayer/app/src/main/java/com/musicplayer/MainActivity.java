package com.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private static MediaPlayer mp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //((Button) v).setEnabled(false);
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        playBGSound();
                    }

                });
                thread.start();
            }
        });

    }

    public  void playBGSound(){
        if(mp!=null)
        {
            mp.release();
        }

        mp=MediaPlayer.create(MainActivity.this,R.raw.mp3);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                playBGSound();


            }
        });



    }



    protected void onDestory(){

        if(mp!=null)
        {
            mp.stop();
            mp.release();
            mp=null;
        }
        if(thread!=null)
        {
            thread=null;
        }
        super.onDestroy();
    }


    public void stop(View view) {
        if(thread!=null)
        {
            thread.interrupt();
            thread=null;
            mp.release();

        }
    }
}



