package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    class Position {
        int x, y;

        Position(int xCor, int yCor){
            x = xCor;
            y = yCor;
        }
    }

    public static List<Position> positionList;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String line="check";
        positionList = new LinkedList<Position>();
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

    public void onStartClicked(){
        positionList = new LinkedList<Position>();
    }

    private void onPlayClicked() throws IOException {
        for (Position positionListElement : positionList) {
            Runtime.getRuntime().exec(new String[]{"shell", "input","tap", String.valueOf(positionListElement.x), String.valueOf(positionListElement.y)});
        }
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
                }
                logcat = Runtime.getRuntime().exec(new String[]{"logcat", "-c"});
//                logcat = Runtime.getRuntime().exec(new String[]{"shell", "input","tap", String.valueOf(x), String.valueOf(y)});
                positionList.add(new Position(x, y));
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