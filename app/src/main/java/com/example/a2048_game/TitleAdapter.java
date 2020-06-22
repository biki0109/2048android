package com.example.a2048_game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends ArrayAdapter<Integer> {
    private Context data_context;
    private ArrayList<Integer> arr;

    public TitleAdapter(@NonNull Context context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.data_context = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public void notifyDataSetChanged() {
        arr = Data.getData().getArrData();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)data_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.title,null);
        }

        if(arr.size()>0){
            Box box = convertView.findViewById(R.id.titleInBox);
            box.setFontSizeAndColor(arr.get(position));
        }

        return convertView;
    }
}