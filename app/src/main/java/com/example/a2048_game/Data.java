package com.example.a2048_game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class Data {
    private static Data data;
    private ArrayList<Integer> arrData = new ArrayList<>();
    private int[] color_array;
    private int [][] arr_2_Dimension = new int [4][4];
    private Random randomSpawn = new Random();
    private int score = 0;
    static {
        data =new Data();
    }

    public static Data getData(){
        return data;
    }
    public int getScore() { return score; }
    public int[][] getArr() { return arr_2_Dimension; }

    public void init(Context context){
        score = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                arr_2_Dimension[i][j] = 0;
                arrData.add(0);
            }
        }

        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.titleColor);
        color_array = new int[typedArray.length()];

        for (int i = 0; i < typedArray.length(); i++){
            color_array[i] = typedArray.getColor(i, 0);
        }
        typedArray.recycle();

        spawnRandomTitle();
        dataAdjust();
    }

    public ArrayList<Integer> getArrData(){
        return arrData;
    }

    public int Colored(int data){
        if(data == 0){
            return Color.rgb(205,193,180);
        } else {
            int a = (int) (Math.log(data)/Math.log(2));

            if(a > 14){
                return color_array[14];
            }

            return color_array[a-1];
        }
    }

    public void spawnRandomTitle(){
        int freeTitle = 0;

        for (int i = 0; i < 16; i++){
            if(arrData.get(i) == 0){
                freeTitle++;
            }
        }

        int createTitle;

        if(freeTitle == 16){
            createTitle = 2;
        }
        else if(freeTitle > 1){
            createTitle = randomSpawn.nextInt(2) + 1;
        }
        else {
            if(freeTitle == 1){
                createTitle = 1;
            } else {
                createTitle = 0;
            }
        }

        while (createTitle != 0){
            int i = randomSpawn.nextInt(4), j = randomSpawn.nextInt(4);
            if (arr_2_Dimension[i][j] == 0){
                arr_2_Dimension[i][j] = randomSpawn.nextInt(10) > 0 ? 2 : 4;
                createTitle--;
            }
        }
    }

    public void dataAdjust(){
        arrData.clear();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                arrData.add(arr_2_Dimension[i][j]);
            }
        }
    }

    public void swipeRight(){
        int move = 0;

        for (int i = 3; i >= 0; i--){
            for (int j = 3; j >= 0; j--){
                int firstTitle = arr_2_Dimension[i][j];

                if(firstTitle == 0){
                    continue;
                } else {
                    //Cộng các số
                    for (int k = j - 1; k >= 0; k--){
                        int secondTitle = arr_2_Dimension[i][k];

                        if(secondTitle == 0){
                            continue;
                        } else {
                            if(secondTitle == firstTitle) {
                                arr_2_Dimension[i][j] = firstTitle * 2;
                                arr_2_Dimension[i][k] = 0;
                                move = 1;
                                break;
                            }

                            break;
                        }
                    }
                }
            }
        }


        //Đổi số 0 về đúng vị trí sau khi vuốt (0402 -> 4200)
        for (int i = 3; i >= 0; i--){
            for (int j = 3; j >= 0; j--){
                int title = arr_2_Dimension[i][j];

                if(title == 0){
                    for (int k = j - 1; k >= 0; k--){
                        int secondTitle =  arr_2_Dimension[i][k];

                        if (secondTitle == 0){
                            continue;
                        } else {
                            arr_2_Dimension[i][j] = arr_2_Dimension[i][k];
                            arr_2_Dimension[i][k] = 0;
                            move =1;
                            break;
                        }
                    }
                }
            }
        }

        if(move == 1){
            spawnRandomTitle();
        }

        dataAdjust();
    }

    public void swipeLeft(){
        int move = 0;

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                int firstTitle = arr_2_Dimension[i][j];

                if(firstTitle == 0){
                    continue;
                } else {
                    //Cộng các số
                    for (int k = j + 1; k < 4; k++){
                        int secondTitle = arr_2_Dimension[i][k];

                        if(secondTitle == 0){
                            continue;
                        } else {
                            if(secondTitle == firstTitle){
                                arr_2_Dimension[i][j] = firstTitle * 2;
                                score += firstTitle * 2;
                                arr_2_Dimension[i][k] = 0;
                                move = 1;
                                break;
                            }

                            break;
                        }
                    }
                }
            }
        }


        //Đổi số 0 về đúng vị trí sau khi vuốt (0402 -> 4200)
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int title = arr_2_Dimension[i][j];

                if(title == 0){
                    for (int k = j + 1; k < 4; k++){
                        int secondTitle =  arr_2_Dimension[i][k];

                        if (secondTitle == 0){
                            continue;
                        } else {
                            arr_2_Dimension[i][j] = arr_2_Dimension[i][k];
                            arr_2_Dimension[i][k] = 0;
                            move = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(move == 1){
            spawnRandomTitle();
        }

        dataAdjust();
    }

    public void swipeUp(){
        int move = 0;

        for (int i = 3; i >= 0; i--){
            for (int j = 3; j >= 0; j--){
                int firstTitle = arr_2_Dimension[j][i];

                if(firstTitle == 0){
                    continue;
                } else {
                    //Cộng các số
                    for (int k = j - 1; k >= 0; k--){
                        int secondTitle = arr_2_Dimension[k][i];

                        if(secondTitle == 0){
                            continue;
                        } else {
                            if(secondTitle == firstTitle){
                                arr_2_Dimension[j][i] = firstTitle * 2;
                                score += firstTitle * 2;
                                arr_2_Dimension[k][i] = 0;
                                move = 1;
                                break;
                            }

                            break;
                        }
                    }
                }
            }
        }


        //Đổi số 0 về đúng vị trí sau khi vuốt (0402 -> 4200)
        for (int i = 3; i >= 0; i--){
            for (int j = 3; j >= 0; j--){
                int title = arr_2_Dimension[j][i];

                if(title == 0){
                    for (int k = j - 1; k >= 0; k--){
                        int secondTitle =  arr_2_Dimension[k][i];

                        if (secondTitle == 0){
                            continue;
                        } else {
                            arr_2_Dimension[j][i] = arr_2_Dimension[k][i];

                            arr_2_Dimension[k][i] = 0;
                            move = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(move == 1){
            spawnRandomTitle();
        }

        dataAdjust();
    }

    public void swipeDown(){
        int move = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                int firstTitle = arr_2_Dimension[j][i];

                if(firstTitle == 0){
                    continue;
                } else {
                    //Cộng các số
                    for (int k = j + 1; k < 4; k++){
                        int secondTitle = arr_2_Dimension[k][i];

                        if(secondTitle == 0){
                            continue;
                        } else {
                            if(secondTitle == firstTitle){
                                arr_2_Dimension[j][i] = firstTitle * 2;
                                score += firstTitle * 2;
                                arr_2_Dimension[k][i] = 0;
                                move = 1;
                                break;
                            }

                            break;
                        }
                    }
                }
            }
        }


        //Đổi số 0 về đúng vị trí sau khi vuốt (0402 -> 4200)
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int title = arr_2_Dimension[j][i];

                if(title == 0){
                    for (int k = j + 1; k < 4; k++){
                        int secondTitle =  arr_2_Dimension[k][i];

                        if (secondTitle == 0){
                            continue;
                        } else {
                            arr_2_Dimension[j][i] = arr_2_Dimension[k][i];
                            arr_2_Dimension[k][i] = 0;
                            move = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(move == 1){
            spawnRandomTitle();
        }

        dataAdjust();
    }

    public int getTotalPoint(){
        int totalPoint = 0;

        if(arr_2_Dimension.length != 0){
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    totalPoint += arr_2_Dimension[i][j];
                }
            }
        }

        return  totalPoint;
    }

    public boolean checkOutOfMove(){
        int temp = 0;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if(arr_2_Dimension[i][j] == 0){
                    temp = 1;
                }
            }
        }

        if(temp == 0){
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    if(i == 0){
                        if(j == 0){
                            if((arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else if(j == 3) {
                            if((arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else {
                            if((arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j])){
                                return false;
                            }
                        }
                    } else if(i == 1 || i == 2){
                        if(j == 0){
                            if((arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else if(j == 3) {
                            if((arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else {
                            if((arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i+1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j])){
                                return false;
                            }
                        }
                    } else {
                        if(j == 0){
                            if((arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else if(j == 3) {
                            if((arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]))
                                return false;
                        } else {
                            if((arr_2_Dimension[i][j+1] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i-1][j] == arr_2_Dimension[i][j]) || (arr_2_Dimension[i][j-1] == arr_2_Dimension[i][j])){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
