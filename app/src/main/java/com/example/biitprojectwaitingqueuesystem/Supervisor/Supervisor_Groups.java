package com.example.biitprojectwaitingqueuesystem.Supervisor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.List;

public class Supervisor_Groups extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnsearh;
    RecyclerView schedules_rv;
    Spinner sp_f;
    List<supervisor_meeting_schedule_list_tbl> sm_schedules;
    supervisor_meeting_schedule_rv_adapter adapter;
    int thour,tmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_groups);
        schedules_rv=findViewById(R.id.sup_meetings_rv_list);
        sp_f=findViewById(R.id.sp_F);
        btnsearh=findViewById(R.id.spm_sbtn);
        //        Spinner
        ArrayAdapter<CharSequence> spadapter=ArrayAdapter.createFromResource(this,R.array.fyp,R.layout.support_simple_spinner_dropdown_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_f.setAdapter(spadapter);
        sp_f.setOnItemSelectedListener(this);
        //---spinner
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        sm_schedules =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/myGroups?sup="+supervisor_static_data.spname;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                supervisor_meeting_schedule_list_tbl data = new supervisor_meeting_schedule_list_tbl();
                                data.setSpm_stdprojecttitle(jsonObject.getString("project_title").toString());
                                data.setSpm_stdname(jsonObject.getString("std_name").toString());
                                data.setSpm_stdregno(jsonObject.getString("reg_no").toString());
                                data.setSpm_stdtechnology(jsonObject.getString("technology").toString());
                                data.setSpm_stdcls(jsonObject.getString("std_class").toString());
                                data.setSpm_stdtime(jsonObject.getString("meeting_time").toString());
                                data.setSpm_stddate(jsonObject.getString("meeting_date").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                sm_schedules.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        schedules_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new supervisor_meeting_schedule_rv_adapter(getApplicationContext(), sm_schedules);
                        schedules_rv.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);
        //---search btn
        btnsearh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm_schedules =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/myGroupsfyp?sup="+supervisor_static_data.spname+"&fypid="+sp_f.getSelectedItemId();

                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        supervisor_meeting_schedule_list_tbl data = new supervisor_meeting_schedule_list_tbl();
                                        data.setSpm_stdprojecttitle(jsonObject.getString("project_title").toString());
                                        data.setSpm_stdname(jsonObject.getString("std_name").toString());
                                        data.setSpm_stdregno(jsonObject.getString("reg_no").toString());
                                        data.setSpm_stdtechnology(jsonObject.getString("technology").toString());
                                        data.setSpm_stdcls(jsonObject.getString("std_class").toString());
                                        data.setSpm_stdtime(jsonObject.getString("meeting_time").toString());
                                        data.setSpm_stddate(jsonObject.getString("meeting_date").toString());
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        sm_schedules.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                schedules_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new supervisor_meeting_schedule_rv_adapter(getApplicationContext(), sm_schedules);
                                schedules_rv.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(arrayRequest);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}