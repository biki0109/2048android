package com.example.a2048_game;

import android.graphics.Color;
import android.widget.GridView;

public class Title {
    private int title_data, background, x_row, y_column, text_color;

    public void init(int data, int x_row, int y_column){
        this.setData(data, x_row, y_column);
    }

    public void setData(int data, int x_row, int y_column){
        this.title_data = data;
        this.y_column = y_column;
        this.x_row = x_row;

        if(data == 2){
            background = Color.rgb(233, 233, 233);
            text_color = Color.rgb(0, 0 ,0);
        }
        else if(data == 4){
            background = Color.rgb(237,224,200);
            text_color = Color.rgb(0, 0 ,0);
        }
        else if(data == 8){
            background = Color.rgb(242,177,121);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 16){
            background = Color.rgb(245,149,99);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 32){
            background = Color.rgb(246,124,95);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 64){
            background = Color.rgb(246,94,59);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 128){
            background = Color.rgb(237,207,115);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 256){
            background = Color.rgb(237,204,98);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 512){
            background = Color.rgb(237,200,80);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 1024){
            background = Color.rgb(237,197,63);
            text_color = Color.rgb(255, 255, 255);
        }
        else if(data == 2048){
            background = Color.rgb(237,194,45);
            text_color = Color.rgb(255, 255, 255);
        }
    }

    public Title getTitle_data(){
        Title title = this;
        return title;
    }
}
