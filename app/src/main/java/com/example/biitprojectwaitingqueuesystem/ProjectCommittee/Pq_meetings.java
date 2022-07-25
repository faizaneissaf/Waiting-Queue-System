package com.example.biitprojectwaitingqueuesystem.ProjectCommittee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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

public class Pq_meetings extends AppCompatActivity {
    RecyclerView allg_rv;
    Button btnsearch;
    Spinner sp_fyps;
    List<pq_allgroups_list_tbl> pq_allmeetings;
    pq_allgroups_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_meetings);
        allg_rv=findViewById(R.id.pqallgroups_rv);
        btnsearch=findViewById(R.id.btn_pqsearhbtn);
        sp_fyps=findViewById(R.id.sp_pqfyp);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        pq_allmeetings =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/allgroups?fypid=1";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                pq_allgroups_list_tbl data = new pq_allgroups_list_tbl();
                                data.setPq_groupno(jsonObject.getInt("group_no"));
                                data.setPq_stdregno(jsonObject.getString("reg_no").toString());
                                data.setPq_stdname(jsonObject.getString("std_name").toString());
                                data.setPq_stdcls(jsonObject.getString("std_class").toString());
                                data.setPq_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                data.setPq_stdprojecttitle(jsonObject.getString("project_title").toString());
                                data.setPq_stdtechnology(jsonObject.getString("technology").toString());
                                data.setPq_remarks(jsonObject.getString("remarks").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                pq_allmeetings.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        allg_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new pq_allgroups_rv_adapter(getApplicationContext(), pq_allmeetings);
                        allg_rv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);
//        Populating Recycler View
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                pq_allmeetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/allgroups?fypid="+sp_fyps.getSelectedItemId();
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        pq_allgroups_list_tbl data = new pq_allgroups_list_tbl();
                                        data.setPq_groupno(jsonObject.getInt("group_no"));
                                        data.setPq_stdregno(jsonObject.getString("reg_no").toString());
                                        data.setPq_stdname(jsonObject.getString("std_name").toString());
                                        data.setPq_stdcls(jsonObject.getString("std_class").toString());
                                        data.setPq_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                        data.setPq_stdprojecttitle(jsonObject.getString("project_title").toString());
                                        data.setPq_stdtechnology(jsonObject.getString("technology").toString());
                                        data.setPq_remarks(jsonObject.getString("remarks").toString());
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        pq_allmeetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                allg_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new pq_allgroups_rv_adapter(getApplicationContext(), pq_allmeetings);
                                allg_rv.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(arrayRequest);
//        Populating Recycler View
            }
        });
    }
}