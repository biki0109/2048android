package com.example.a2048_game;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class Box extends TextView {

    public Box(Context context) {
        super(context);
    }

    public Box(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Box(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    public void setFontSizeAndColor(int data){
        if(data < 100){
            setTextSize(40);
        } else if (data < 1000){
            setTextSize(35);
        } else {
            setTextSize(30);
        }

        if(data >= 8){
            setTextColor(Color.WHITE);
        } else {
            setTextColor(Color.BLACK);
        }

        GradientDrawable drawable = (GradientDrawable)this.getBackground();
        drawable.setColor(Data.getData().Colored(data));
        setBackground(drawable);

        if (data == 0){
            setText(" ");
        } else {
            setText(""+data);
        }
    }
}
