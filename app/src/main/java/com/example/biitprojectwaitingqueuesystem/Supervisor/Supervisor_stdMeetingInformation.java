package com.example.biitprojectwaitingqueuesystem.Supervisor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_meetings_fyp1;
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Supervisor_stdMeetingInformation extends AppCompatActivity {
    TextView freetime;
    TextView regno,name,cls,sup,projname,projtech,mt,md;
    int thour,tmin;
    Button senttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_std_meeting_information);
        freetime=findViewById(R.id.txt_sentFreetime);
        regno=findViewById(R.id.ssmi_regno);
        name=findViewById(R.id.ssmi_stdname);
        cls=findViewById(R.id.ssmi_stdcls);
        sup=findViewById(R.id.ssmi_stdsupervisor);
        projname=findViewById(R.id.ssmi_stdprojtitle);
        projtech=findViewById(R.id.ssmi_stdprojecttech);
        mt=findViewById(R.id.ssmi_meetingtime);
        md=findViewById(R.id.ssmi_meetingdate);
        senttime=findViewById(R.id.btnsenttimetopq);
        //-----------------------------------
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/stdMInfo?stdarid="+supervisor_static_data.std_regno;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                supervisor_meeting_schedule_list_tbl data = new supervisor_meeting_schedule_list_tbl();
                                projname.setText(jsonObject.getString("project_title").toString());
                                name.setText(jsonObject.getString("std_name").toString());
                                regno.setText(jsonObject.getString("reg_no").toString());
                                projtech.setText(jsonObject.getString("technology").toString());
                                cls.setText(jsonObject.getString("std_class").toString());
                                sup.setText(jsonObject.getString("std_supervisor").toString());
                                mt.setText(jsonObject.getString("meeting_time").toString());
                                md.setText(jsonObject.getString("meeting_date").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);
        //-----------------------------------


        freetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        Supervisor_stdMeetingInformation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                thour=hourOfDay;
                                tmin=minute;
                                String time=thour+":"+tmin;
                                SimpleDateFormat f24Hours=new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours=new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    freetime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(thour,tmin);
                timePickerDialog.show();
            }
        });
        senttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/supFreetime?stdarid="+supervisor_static_data.std_regno+"&time="+freetime.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Time Sent", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
    }
}