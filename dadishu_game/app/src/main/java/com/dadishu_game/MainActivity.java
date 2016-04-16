package com.dadishu_game;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  int i=0;
    private ImageView mouse;

    private Handler handler;


    public  int[][] position=new int [][]{{80,210},{190,210},{300,220},{60,350},{190,350}
    ,{300,350},{60,500},{190,500},{320,500}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建线程
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                int index = 0;
                while (!Thread.currentThread().isInterrupted()) {

                    index = new Random().nextInt(position.length);
                    //获得Message
                    Message m = handler.obtainMessage();
                    m.arg1 = index;
                    m.what = 0x101;
                    handler.sendMessage(m);

                    try {
                        Thread.sleep(new Random().nextInt(200) + 400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        t.start();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                int index = 0;
                if (msg.what == 0x101) {
                    index = msg.arg1;
                    mouse.setX(position[index][0]);
                    mouse.setY(position[index][1]);
                    mouse.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };

        mouse = (ImageView) findViewById(R.id.imageView);


        mouse.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);
                i++;
                Toast.makeText(MainActivity.this, "已打到" + i + "只地鼠", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
