package com.example.designweather;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SubActivity extends AppCompatActivity {
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        Intent intent= getIntent();
        String city= intent.getStringExtra("city");
        Get7DaysData("");
    }
    public  void  Get7DaysData(String data){
        url="https://api.openweathermap.org/data/2.5/forecast/daily?q="+data+"&units=metric&cnt=7&appid=6d14d73aa1fa5ae8651b7c345af7a37a";
        RequestQueue requestQueue= Volley.newRequestQueue(SubActivity.this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        requestQueue.add(stringRequest);
    }
}