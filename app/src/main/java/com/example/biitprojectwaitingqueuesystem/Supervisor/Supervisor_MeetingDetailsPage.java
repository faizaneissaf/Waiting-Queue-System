package com.example.biitprojectwaitingqueuesystem.Supervisor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_fyp1_meeting_schedules_list_tbl;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_fyp1_meeting_schedules_rv_adapter;
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Supervisor_MeetingDetailsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnsearch;
    Spinner sp_fypssup;
    RecyclerView sp_meetings_rv;
    List<supervisor_meetings_list_tbl> sp_meetings;
    supervisor_meetings_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_meeting_details_page);
        btnsearch=findViewById(R.id.smd_supervisor);
        sp_fypssup=findViewById(R.id.spinner_sg_supervisor);
        sp_meetings_rv=findViewById(R.id.sp_meetings_rv);
        //        Spinner
        ArrayAdapter<CharSequence> spadapter=ArrayAdapter.createFromResource(this,R.array.fyp,R.layout.support_simple_spinner_dropdown_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fypssup.setAdapter(spadapter);
        sp_fypssup.setOnItemSelectedListener(this);
        //---spinner
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        sp_meetings =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/myGroups?sup="+supervisor_static_data.spname;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                supervisor_meetings_list_tbl data = new supervisor_meetings_list_tbl();
                                data.setSp_stdprojecttitle(jsonObject.getString("project_title").toString());
                                data.setSp_stdname(jsonObject.getString("std_name").toString());
                                data.setSp_stdregno(jsonObject.getString("reg_no").toString());
                                data.setSp_stdtechnology(jsonObject.getString("technology").toString());
                                data.setSp_stdcls(jsonObject.getString("std_class").toString());
                                data.setSp_stdtime(jsonObject.getString("meeting_time").toString());
                                data.setSp_stddate(jsonObject.getString("meeting_date").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                sp_meetings.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        sp_meetings_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new supervisor_meetings_rv_adapter(getApplicationContext(), sp_meetings);
                        sp_meetings_rv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);
        ////------Spinner data
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sp_meetings =new ArrayList<>();
                    String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/myGroupsfyp?sup="+supervisor_static_data.spname+"&fypid="+sp_fypssup.getSelectedItemId();

                    JsonArrayRequest arrayRequest =new JsonArrayRequest(
                            Request.Method.GET, url2, null,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {

                                    for (int i = 0; i < response.length(); i++) {
                                        try {
                                            JSONObject jsonObject = response.getJSONObject(i);
                                            supervisor_meetings_list_tbl data = new supervisor_meetings_list_tbl();
                                            data.setSp_stdprojecttitle(jsonObject.getString("project_title").toString());
                                            data.setSp_stdname(jsonObject.getString("std_name").toString());
                                            data.setSp_stdregno(jsonObject.getString("reg_no").toString());
                                            data.setSp_stdtechnology(jsonObject.getString("technology").toString());
                                            data.setSp_stdcls(jsonObject.getString("std_class").toString());
                                            data.setSp_stdtime(jsonObject.getString("meeting_time").toString());
                                            data.setSp_stddate(jsonObject.getString("meeting_date").toString());
                                            //-----------------------------------------------------------------------------------------------------------------
                                            //-----------------------------------------------------------------------------------------------------------------
                                            sp_meetings.add(data);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    sp_meetings_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    adapter = new supervisor_meetings_rv_adapter(getApplicationContext(), sp_meetings);
                                    sp_meetings_rv.setAdapter(adapter);
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
//-------spinner methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}