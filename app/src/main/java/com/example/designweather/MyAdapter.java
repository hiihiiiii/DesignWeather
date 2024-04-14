package com.example.designweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Weather> weatherArrayList;

    public MyAdapter(Context context, ArrayList<Weather> weatherArrayList) {
        this.context = context;
        this.weatherArrayList = weatherArrayList;
    }

    @Override
    public int getCount() {
        return weatherArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =layoutInflater.inflate(R.layout.layout_item,null);
        Weather weather= weatherArrayList.get(position);
        TextView txtDay;
        txtDay= convertView.findViewById(R.id.txtDay);
        TextView txtStatus= convertView.findViewById(R.id.txtStatus);
        ImageView img= convertView.findViewById(R.id.img_status);
        TextView txtMin= convertView.findViewById(R.id.txtMin);
        TextView txtMax= convertView.findViewById(R.id.txtMax);
        txtDay.setText(weather.getDay());
        txtStatus.setText(weather.getStatus());
        txtMin.setText(weather.getMinTemp()+"°C");
        txtMax.setText(weather.getMaxTemp()+"°C");
        Picasso.get().load("https://openweathermap.org/img/wn/"+weather.image+".png").into(img);

        return convertView;
    }
}
