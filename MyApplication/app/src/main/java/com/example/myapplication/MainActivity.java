package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String line="check";
        Process logcat;
        final StringBuilder log = new StringBuilder();
        try {
            logcat = Runtime.getRuntime().exec(new String[]{"logcat", "-d"});
            BufferedReader br = new BufferedReader(new InputStreamReader(logcat.getInputStream()),1024);

            String separator = System.getProperty("line.separator");
            while ((line = br.readLine()) != null) {
                log.append(line);
                log.append(separator);
//                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Snehith logs",log.toString());
        TextView myAwesomeTextView = (TextView)findViewById(R.id.textview);
        myAwesomeTextView.setText(log.toString());
        myAwesomeTextView.setOnTouchListener(handleTouch);
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Log.i("Snehith ", "touch: (" + x + ", " + y + ")");
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    Log.i("Snehith  ", "touched down");
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    Log.i("Snehith ", "moving: (" + x + ", " + y + ")");
//                    break;
//                case MotionEvent.ACTION_UP:
//                    Log.i("Snehith ", "touched up");
//                    break;
//            }
            String line="check";
            Process logcat;
            final StringBuilder log = new StringBuilder();
            try {
                logcat = Runtime.getRuntime().exec(new String[]{"logcat", "-d"});
                BufferedReader br = new BufferedReader(new InputStreamReader(logcat.getInputStream()),1024);

                String separator = System.getProperty("line.separator");
                while ((line = br.readLine()) != null) {
                    log.append(line);
                    log.append(separator);
//                System.out.println(line);
                }
                logcat = Runtime.getRuntime().exec(new String[]{"logcat", "-c"});
                logcat = Runtime.getRuntime().exec(new String[]{"shell", "input","tap", String.valueOf(x), String.valueOf(y)});
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("Snehith logs",log.toString());
            TextView myAwesomeTextView = (TextView)findViewById(R.id.textview);
            myAwesomeTextView.setText(log.toString());
            return true;
        }
    };


}