package com.example.projectandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    TextView txtTemperature, txtCountry, txtStatus, txtday,
             txtday1, txtday2, txtday3, txtday4, txtday5, txtday6, txtday7,
             txtMin1, txtMin2, txtMin3, txtMin4, txtMin5, txtMin6, txtMin7,
             txtMax1, txtMax2, txtMax3, txtMax4, txtMax5, txtMax6, txtMax7,
             txtHour1, txtHour2, txtHour3, txtHour4, txtHour5, txtHour6, txtHour7,
             txtMaxHour1, txtMaxHour2, txtMaxHour3, txtMaxHour4, txtMaxHour5, txtMaxHour6, txtMaxHour7,
             txtMinHour1, txtMinHour2, txtMinHour3, txtMinHour4, txtMinHour5, txtMinHour6, txtMinHour7,
    txthumidity, txtPressure;
    ImageButton imageButton;
    EditText editText;
    ImageView curentImageView, imageViewDay1, imageViewDay2, imageViewDay3, imageViewDay4, imageViewDay5, imageViewDay6, imageViewDay7,
              imgHour1, imgHour2, imgHour3, imgHour4, imgHour5, imgHour6, imgHour7;
    String defaultCity = "";
    String data = "Hanoi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextCity);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtCountry = findViewById(R.id.txtCountry);
        txtStatus = findViewById(R.id.txtStatus);
        txtday = findViewById(R.id.txtday);
        imageButton = findViewById(R.id.btnAdd);
        curentImageView = findViewById(R.id.imageIcon);
        txtday1 = findViewById(R.id.txtday1);
        txtday2 = findViewById(R.id.txtday2);
        txtday3 = findViewById(R.id.txtday3);
        txtday4 = findViewById(R.id.txtday4);
        txtday5 = findViewById(R.id.txtday5);
        txtday6 = findViewById(R.id.txtday6);
        txtday7 = findViewById(R.id.txtday7);
        imageViewDay1 = findViewById(R.id.imageViewDay1);
        imageViewDay2 = findViewById(R.id.imageViewDay2);
        imageViewDay3 = findViewById(R.id.imageViewDay3);
        imageViewDay4 = findViewById(R.id.imageViewDay4);
        imageViewDay5 = findViewById(R.id.imageViewDay5);
        imageViewDay6 = findViewById(R.id.imageViewDay6);
        imageViewDay7 = findViewById(R.id.imageViewDay7);
        txtMin1 = findViewById(R.id.txtMin1);
        txtMin2 = findViewById(R.id.txtMin2);
        txtMin3 = findViewById(R.id.txtMin3);
        txtMin4 = findViewById(R.id.txtMin4);
        txtMin5 = findViewById(R.id.txtMin5);
        txtMin6 = findViewById(R.id.txtMin6);
        txtMin7 = findViewById(R.id.txtMin7);
        txtMax1 = findViewById(R.id.txtMax1);
        txtMax2 = findViewById(R.id.txtMax2);
        txtMax3 = findViewById(R.id.txtMax3);
        txtMax4 = findViewById(R.id.txtMax4);
        txtMax5 = findViewById(R.id.txtMax5);
        txtMax6 = findViewById(R.id.txtMax6);
        txtMax7 = findViewById(R.id.txtMax7);
        txtHour1 = findViewById(R.id.txtHour1);
        txtHour2 = findViewById(R.id.txtHour2);
        txtHour3 = findViewById(R.id.txtHour3);
        txtHour4 = findViewById(R.id.txtHour4);
        txtHour5 = findViewById(R.id.txtHour5);
        txtHour6 = findViewById(R.id.txtHour6);
        txtHour7 = findViewById(R.id.txtHour7);
        imgHour1 = findViewById(R.id.imgHour1);
        imgHour2 = findViewById(R.id.imgHour2);
        imgHour3 = findViewById(R.id.imgHour3);
        imgHour4 = findViewById(R.id.imgHour4);
        imgHour5 = findViewById(R.id.imgHour5);
        imgHour6 = findViewById(R.id.imgHour6);
        imgHour7 = findViewById(R.id.imgHour7);
        txtMaxHour1 = findViewById(R.id.txtMaxHour1);
        txtMaxHour2 = findViewById(R.id.txtMaxHour2);
        txtMaxHour3 = findViewById(R.id.txtMaxHour3);
        txtMaxHour4 = findViewById(R.id.txtMaxHour4);
        txtMaxHour5 = findViewById(R.id.txtMaxHour5);
        txtMaxHour6 = findViewById(R.id.txtMaxHour6);
        txtMaxHour7 = findViewById(R.id.txtMaxHour7);
        txtMinHour1 = findViewById(R.id.txtMinHour1);
        txtMinHour2 = findViewById(R.id.txtMinHour2);
        txtMinHour3 = findViewById(R.id.txtMinHour3);
        txtMinHour4 = findViewById(R.id.txtMinHour4);
        txtMinHour5 = findViewById(R.id.txtMinHour5);
        txtMinHour6 = findViewById(R.id.txtMinHour6);
        txtMinHour7 = findViewById(R.id.txtMinHour7);

        txthumidity = findViewById(R.id.txthumidity);
        txtPressure = findViewById(R.id.txtPressure);



        getDataWeather("Hanoi");
        getDailyWeather("Hanoi");
        getHourlyWeather("Hanoi");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editText.getText().toString();
                if (city.equals("")) {
                    Toast.makeText(getApplicationContext(),"Data can be null, pls try again",Toast.LENGTH_LONG).show();
                } else {
                    defaultCity = city;
                    getDataWeather(defaultCity);
                    getDailyWeather(defaultCity);
                    getHourlyWeather(defaultCity);

                }

            }
        });
    }


    public void getDataWeather(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=c26c78de6aab9fb2905c7cc351791be3";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String dayJson = jsonObject.getString("dt");
                            long realtime = Long.valueOf(dayJson);
                            Date date = new Date(realtime * 1000);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, yyyy-MM-dd" + "\n" + "HH:mm:ss");
                            String day = simpleDateFormat.format(date);
                            txtday.setText(day);

                            JSONArray jsonArray = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                            String status = jsonObjectWeather.getString("description");
                            txtStatus.setText(status);

                            String icon = "num" + jsonObjectWeather.getString("icon");
                            int imgid = getResId(icon,R.drawable.class);
                            curentImageView.setImageResource(imgid);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String temperature = jsonObjectMain.getString("temp");
                            Double x = Double.valueOf(temperature);
                            String realtemperature = String.valueOf(x.intValue());
                            txtTemperature.setText(realtemperature + "°");

                            String humidity = jsonObjectMain.getString("humidity") +"%";
                            txthumidity.setText(humidity);

                            String pressure = jsonObjectMain.getString("pressure");
                            txtPressure.setText(pressure);

                            String name = jsonObject.getString("name");
                            JSONObject jsonObjectCountry = jsonObject.getJSONObject("sys");
                            String country = jsonObjectCountry.getString("country");
                            txtCountry.setText(name + ", " + country);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Input false, pls pick another city!!!",Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void getDailyWeather(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + data + "&cnt=7&appid=53fbf527d52d4d773e828243b90c1f8e";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                            ArrayList<items> list = new ArrayList<>();
                            for (int i = 0; i < 7; i++) {
                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                                String trueDay = jsonObjectList.getString("dt");
                                long realtime = Long.valueOf(trueDay);
                                Date date = new Date(realtime * 1000);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                                String day = simpleDateFormat.format(date).substring(0,3);
                                JSONArray jsonArray = jsonObjectList.getJSONArray("weather");
                                JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                                String icon = "num" + jsonObjectWeather.getString("icon");
                                int imgid = getResId(icon,R.drawable.class);

                                JSONObject temperature = jsonObjectList.getJSONObject("temp");
                                double x = temperature.getDouble("min") /10;
                                double y = temperature.getDouble("max") /10;
                                String min = String.format("%.00f",x) + "°";
                                String max = String.format("%.00f",y) + "°";
                                list.add(new items(day, imgid, min, max));
                            }
                                txtday1.setText(list.get(0).day);
                                txtday2.setText(list.get(1).day);
                                txtday3.setText(list.get(2).day);
                                txtday4.setText(list.get(3).day);
                                txtday5.setText(list.get(4).day);
                                txtday6.setText(list.get(5).day);
                                txtday7.setText(list.get(6).day);
                                imageViewDay1.setImageResource(list.get(0).imgID);
                                imageViewDay2.setImageResource(list.get(1).imgID);
                                imageViewDay3.setImageResource(list.get(2).imgID);
                                imageViewDay4.setImageResource(list.get(3).imgID);
                                imageViewDay5.setImageResource(list.get(4).imgID);
                                imageViewDay6.setImageResource(list.get(5).imgID);
                                imageViewDay7.setImageResource(list.get(6).imgID);
                                txtMax1.setText(list.get(0).max);
                                txtMax2.setText(list.get(1).max);
                                txtMax3.setText(list.get(2).max);
                                txtMax4.setText(list.get(3).max);
                                txtMax5.setText(list.get(4).max);
                                txtMax6.setText(list.get(5).max);
                                txtMax7.setText(list.get(6).max);
                                txtMin1.setText(list.get(0).min);
                                txtMin2.setText(list.get(1).min);
                                txtMin3.setText(list.get(2).min);
                                txtMin4.setText(list.get(3).min);
                                txtMin5.setText(list.get(4).min);
                                txtMin6.setText(list.get(5).min);
                                txtMin7.setText(list.get(6).min);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    public void getHourlyWeather(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + data+ "&units=metric&appid=c26c78de6aab9fb2905c7cc351791be3";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                    ArrayList<Hourly> hourlist = new ArrayList<>();
                    for (int i = 0; i < 7; i++) {
                        JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                        String dailyHour = jsonObjectList.getString("dt_txt").substring(11,16);


                        JSONArray jsonArray = jsonObjectList.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String icon = "num" + jsonObjectWeather.getString("icon");
                        int imgid = getResId(icon,R.drawable.class);


                        JSONObject temperature = jsonObjectList.getJSONObject("main");
                        double x = temperature.getDouble("temp_min") ;
                        double y = temperature.getDouble("temp_max") ;
                        String min = String.format("%.0f",x) + "°";
                        String max = String.format("%.0f",y) + "°";

                        hourlist.add(new Hourly(dailyHour,min,max,imgid));

                    }


                        txtHour1.setText(hourlist.get(0).hour);
                        txtHour2.setText(hourlist.get(1).hour);
                        txtHour3.setText(hourlist.get(2).hour);
                        txtHour4.setText(hourlist.get(3).hour);
                        txtHour5.setText(hourlist.get(4).hour);
                        txtHour6.setText(hourlist.get(5).hour);
                        txtHour7.setText(hourlist.get(6).hour);
                        imgHour1.setImageResource(hourlist.get(0).imgID);
                        imgHour2.setImageResource(hourlist.get(1).imgID);
                        imgHour3.setImageResource(hourlist.get(2).imgID);
                        imgHour4.setImageResource(hourlist.get(3).imgID);
                        imgHour5.setImageResource(hourlist.get(4).imgID);
                        imgHour6.setImageResource(hourlist.get(5).imgID);
                        imgHour7.setImageResource(hourlist.get(6).imgID);
                        txtMaxHour1.setText(hourlist.get(0).max);
                        txtMaxHour2.setText(hourlist.get(1).max);
                        txtMaxHour3.setText(hourlist.get(2).max);
                        txtMaxHour4.setText(hourlist.get(3).max);
                        txtMaxHour5.setText(hourlist.get(4).max);
                        txtMaxHour6.setText(hourlist.get(5).max);
                        txtMaxHour7.setText(hourlist.get(6).max);
                        txtMinHour1.setText(hourlist.get(0).min);
                        txtMinHour2.setText(hourlist.get(1).min);
                        txtMinHour3.setText(hourlist.get(2).min);
                        txtMinHour4.setText(hourlist.get(3).min);
                        txtMinHour5.setText(hourlist.get(4).min);
                        txtMinHour6.setText(hourlist.get(5).min);
                        txtMinHour7.setText(hourlist.get(6).min);






                } catch (Exception e) {
                    e.printStackTrace();
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

    public static int getResId(String resName, Class<?> c){
        try {
            Field field = c.getDeclaredField(resName);
            return field.getInt(field);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}