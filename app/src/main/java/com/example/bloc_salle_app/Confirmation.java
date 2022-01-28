package com.example.bloc_salle_app;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.budiyev.android.codescanner.CodeScanner;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
public class Confirmation extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private Button btn_confirmation,scanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        btn_confirmation = findViewById(R.id.btn_confirmation);
        scanner = findViewById(R.id.btn_scanner);
        String url = "http://bloc-salle-app.herokuapp.com/insertoccupation";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String sallee =(String) bundle.get("salle");
        String[] salle = sallee.split("\"");
        Log.d(TAG, "onCreate: "+salle);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview();
            }
        });
        btn_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()>=2782 && response.length()<4000){
                            Toast.makeText(getApplicationContext(), "la salle est déja occupée", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getApplicationContext(), "occupation bien confirmée!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onResponse: "+response.length());

                        }}
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Calendar cal = Calendar.getInstance();
                        String crenau;
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String time = sdf.format(date);
                        int heure = Integer.parseInt(time.split(":")[0]);
                        Log.d(TAG, "getParams: "+heure);
                        if(heure < 10){
                            crenau = "8h30->10h20";
                        }else if(heure < 12){
                            crenau = "10h30->12h20";
                        }else if (heure < 15){
                            crenau ="13h30->15h20";
                        }else{
                            crenau = "15h30->17h20";
                        }
                        params.put("salle",salle[1]);
                        params.put("time",crenau);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
            }
        });
    }
}