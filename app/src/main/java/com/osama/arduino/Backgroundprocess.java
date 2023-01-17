package com.osama.arduino;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.SystemClock;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class Backgroundprocess extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences sp = context.getApplicationContext().getSharedPreferences("time" , Context.MODE_PRIVATE);
        String clock = sp.getString("clock" , "");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("hh");
        String time = simpleFormatter.format(calendar.getTime());
        if(time.equals(clock)){
            Ringtone ringtone = RingtoneManager.getRingtone(context,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

            ringtone.play();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.26/ON1", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context , "click faild" , Toast.LENGTH_LONG).show();
                }
            }){


            };
            RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            requestQueue.add(request);

        }



    }
}
