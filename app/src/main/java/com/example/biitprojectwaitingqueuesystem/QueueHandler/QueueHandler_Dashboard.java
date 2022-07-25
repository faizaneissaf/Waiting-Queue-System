package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.Student.student_meetinginfo_data;
import com.example.biitprojectwaitingqueuesystem.Student.student_static_data;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_MeetingDetailsPage;
import com.example.biitprojectwaitingqueuesystem.loginuser_staticdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueueHandler_Dashboard extends AppCompatActivity {
    TextView nametitle;
    ImageView qhlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_handler_dashboard);
        nametitle=findViewById(R.id.qh_dbnametitle);
        qhlogout=findViewById(R.id.qh_logout);
        qhlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//----User Profile
        String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Student/userProfile?email="+ loginuser_staticdata.useremail;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int n = 0; n < response.length(); n++)
                {
                    try {
                        JSONObject object = response.getJSONObject(n);
                        qh_static_data.qh_id= object.getInt("user_id");
                        qh_static_data.qh_username= object.getString("user_name");
                        qh_static_data.qh_useremail= object.getString("user_email");
                        nametitle.setText(qh_static_data.qh_username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        ///------------------------------------------

        findViewById(R.id.qMettingschedule_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Qh_MeetingSchedules.class);
                startActivity(i);
            }
        });
        findViewById(R.id.qProfile_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Qh_Profile.class);
                startActivity(i);
            }
        });
    }
}