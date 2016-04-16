package com.codeview;

import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import ywq.ywq;

public class MainActivity extends AppCompatActivity {

    private static final int SHOW_TEXT =2 ;
    private android.os.Handler handler=new android.os.Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what){
                case SHOW_TEXT:
                    String text= (String) msg.obj;
                    textView.setText(text);

            }


        };
    };
    private TextView textView;
    private EditText ed_path;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        ed_path= (EditText) findViewById(R.id.ed_path);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.codeview/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.codeview/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void click(View view) {
        final String path=ed_path.getText().toString().trim();
        if(TextUtils.isEmpty(path))
        {
            Toast.makeText(this,"路径不能为空",Toast.LENGTH_SHORT).show();
        }
        else
        {
            new Thread(){
              public void run(){
                  try {
                      //操作
                      URL url=new URL(path);
                        HttpURLConnection com= (HttpURLConnection) url.openConnection();
                        com.setRequestMethod("GET");
                      com.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
                      com.setConnectTimeout(500);
                      int code=com.getResponseCode();

                      Log.e("ASD",code+"");
                        if(code==200) {
                            InputStream is = com.getInputStream();
                            String result = ywq.readInputStream(is);


                            Message msg=new Message();

                            msg.what=SHOW_TEXT;
                            msg.obj=result;
                            handler.sendMessage(msg);




                        }
                  } catch (MalformedURLException e) {
                      e.printStackTrace();
                      Message msg=new Message();

                  } catch (IOException e) {
                      e.printStackTrace();
                  }


              }  ;

            }.start();




        }
    }
}
