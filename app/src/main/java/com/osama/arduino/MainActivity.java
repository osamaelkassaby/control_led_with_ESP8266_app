package com.osama.arduino;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

SharedPreferences sp;
String clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Buttons ON
        Button ON1 = findViewById(R.id.ON1);
        Button ON2 = findViewById(R.id.ON2);
        Button ON3 = findViewById(R.id.ON3);
        Button ON4 = findViewById(R.id.ON4);
        // Buttons OFF
        Button OF1 = findViewById(R.id.OF1);
        Button OF2 = findViewById(R.id.OF2);
        Button OF3 = findViewById(R.id.OF3);
        Button OF4 = findViewById(R.id.OF4);

        Button alerm = findViewById(R.id.Alerm);

        EditText editText = findViewById(R.id.time);

        sp = getSharedPreferences("time" , Context.MODE_PRIVATE);

        alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 clock = editText.getText().toString();
                 SharedPreferences.Editor editor = sp.edit();
                 editor.putString("clock" , clock);
                 editor.commit();

                Intent intent = new Intent(MainActivity.this , Backgroundprocess.class);
                intent.setAction("Backgroundprocess");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent , 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP , 0,1 ,pendingIntent);
                finish();

            }
        });


        ON1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request_ON("ON1");
            }
        });


        ON2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_ON("ON2");
            }
        });

        ON3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_ON("ON3");
            }
        });

        ON4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_ON("ON4");
            }
        });


        OF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_OF("OF1");
            }
        });


        OF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_OF("OF2");

            }
        });

        OF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_OF("OF3");

            }
        });

        OF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_OF("OF4");

            }
        });


    }

    public void  request_ON(String ON){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.26/"+ON, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this , "click faild" , Toast.LENGTH_LONG).show();
            }
        }){


        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }



    public void  request_OF(String OF){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.26/"+OF, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this , "click faild" , Toast.LENGTH_LONG).show();
            }
        }){


        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}