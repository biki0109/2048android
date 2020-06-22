package com.example.a2048_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gameGrid;
    private TitleAdapter adapter;
    private View.OnTouchListener listener;
    private float X, Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compareAndGet();
        init();
        setData();

//        GridView gameBox = (GridView) findViewById(R.id.gameGrid);
//        gameBox.setOnTouchListener(
//                new OnSwipeListener(this) {
//                    {
//                        setDragHorizontal(true);
//                        setExitScreenOnSwipe(true);
//                        setAnimationDelay(1000);
//                    }
//
//                    @Override
//                    public void onSwipeLeft(float distance) {
//                        Toast.makeText(MainActivity.this, "swiped left!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onSwipeRight(float distance) {
//                        Toast.makeText(MainActivity.this, "swiped right!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
    }

    private void compareAndGet(){
        gameGrid = (GridView)findViewById(R.id.gameGrid);
    }

    private void init(){
        Data.getData().init(MainActivity.this);
        adapter = new TitleAdapter(MainActivity.this, 0, Data.getData().getArrData());
        listener = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView curScore = findViewById(R.id.score);
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        X = event.getX();
                        Y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(Math.abs(event.getX()- X) > Math.abs(event.getY()-Y)){
                            if(event.getX() > X){
                                Data.getData().swipeRight();
                                adapter.notifyDataSetChanged();
                                curScore.setText(Integer.toString(Data.getData().getScore()));
                            } else {
                                Data.getData().swipeLeft();
                                adapter.notifyDataSetChanged();
                                curScore.setText(Integer.toString(Data.getData().getScore()));
                            }
                        } else {
                            if(event.getY() > Y){
                                Data.getData().swipeUp();
                                adapter.notifyDataSetChanged();
                                curScore.setText(Integer.toString(Data.getData().getScore()));

                            } else {
                                Data.getData().swipeDown();
                                adapter.notifyDataSetChanged();
                                curScore.setText(Integer.toString(Data.getData().getScore()));

                            }
                        }

//                        TextView point = findViewById(R.id.score);
//                        int totalPoint = Data.getData().getTotalPoint();
//                        point.setText(totalPoint);
                }
                return true;
            }
        };
    }

    private void setData(){
        gameGrid.setAdapter(adapter);
        gameGrid.setOnTouchListener(listener);
    }

}
