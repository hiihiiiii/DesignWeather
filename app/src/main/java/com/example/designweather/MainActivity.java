package com.example.designweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    Button btnSearch, btnSeeMore;
    TextView txtCity, txtNation, txtStatus, txtTemp, txtWet, txtWind, txtRain, txtDay;
    ImageView imgIcon;
    String City="";

    ConnectionReceiver connectionReceiver;

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        GetCurrentWeatherData("Ha Noi");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= edtSearch.getText().toString();
                if(city.equals("")){
                    City="Ha Noi";
                    GetCurrentWeatherData(City);
                }else{
                    City=city;
                    GetCurrentWeatherData(City);
                }
            }
        });
        btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= edtSearch.getText().toString();
                Intent intent= new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("City",city);
                startActivity(intent);
            }
        });
//        connectionReceiver= new ConnectionReceiver();
//        intentFilter=new IntentFilter("com.example.designweather.SOME_ACTION");
//        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(connectionReceiver,intentFilter);
    }


    public  void  GetCurrentWeatherData(String data) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=bd5e378503939ddaee76f12ad7a97608";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            String day= jsonObject.getString("dt");
                            String name= jsonObject.getString("name");
                            txtCity.setText(name);

                            long l= Long.valueOf(day);
                            Date date= new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String Day= simpleDateFormat.format(date);
                            txtDay.setText(Day);

                            String urlIcon="https://openweathermap.org/img/wn/";
                            JSONArray jsonArrayWeather= jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather=jsonArrayWeather.getJSONObject(0);
                            String status= jsonObjectWeather.getString("main");
                            String icon= jsonObjectWeather.getString("icon");
                            Picasso.get().load("https://openweathermap.org/img/wn/"+icon+".png").into(imgIcon);
                            txtStatus.setText(status);

                            JSONObject jsonObjectMain= jsonObject.getJSONObject("main");
                            String temp= jsonObjectMain.getString("temp");
                            String humidity= jsonObjectMain.getString("humidity");

                            Double a= Double.valueOf(temp);
                            String Nhietdo= String.valueOf(a.intValue());
                            txtWet.setText(humidity+ "%");
                            txtTemp.setText(Nhietdo+"°C");

                            JSONObject jsonObjectWind= jsonObject.getJSONObject("wind");
                            String wind= jsonObjectWind.getString("speed");
                            txtWind.setText(wind+"m/s");
                             JSONObject jsonObjectCloud= jsonObject.getJSONObject("clouds");
                             String Cloud= jsonObjectCloud.getString("all");
                             txtRain.setText(Cloud+"%");

                             JSONObject jsonObjectSys= jsonObject.getJSONObject("sys");
                             String nation= jsonObjectSys.getString("country");
                             txtNation.setText(nation);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {
        edtSearch= (EditText) findViewById(R.id.edtCityName);
        btnSearch= (Button) findViewById(R.id.btnSearch);
        btnSeeMore= (Button) findViewById(R.id.btnnext);
        txtCity= findViewById(R.id.txtThanhpho);
        txtNation= findViewById(R.id.txtQuocgia);
        txtStatus= findViewById(R.id.txtTrangthai);
        txtTemp= findViewById(R.id.txtNhietdo);
        txtWet= findViewById(R.id.txtWet);
        txtWind= findViewById(R.id.txtWind);
        txtRain=findViewById(R.id.txtRain);
        txtDay= findViewById(R.id.txtNgay);
        imgIcon= (ImageView) findViewById(R.id.imgWeather);

    }
}