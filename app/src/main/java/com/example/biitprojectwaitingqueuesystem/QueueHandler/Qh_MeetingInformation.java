package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.ProjectCommittee.Pq_Dashboard;
import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.Student.Student_Dashboard;
import com.example.biitprojectwaitingqueuesystem.Student.student_static_data;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_Dashboard;
import com.example.biitprojectwaitingqueuesystem.loginuser_staticdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Qh_MeetingInformation extends AppCompatActivity {
//    Button btnmoreopt;
    Button call,skip,cancel;
    RecyclerView qh_meetinginfos;
    List<qh_fyp1_meetinginfo_tbl_list> mettinginfofyp1;
    qh_fyp1_meetinginfo_rv_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meeting_information);
//        btnmoreopt=findViewById(R.id.Button_options);
        qh_meetinginfos=findViewById(R.id.qh_groupInfo_rv);
        call=findViewById(R.id.btncallfyp1);
        skip=findViewById(R.id.btnskipmtngfyp1);
        cancel=findViewById(R.id.btncancelfyp1);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        ///-------
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/callgroupfyp1?groupid="+qh_static_data.qhfyp1_groupno;
                StringRequest stringRequest=new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        //----Skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/skipgroupfyp1?groupid="+qh_static_data.qhfyp1_groupno;
                StringRequest stringRequest=new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
//-------Cancel
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/cancelgroupfyp1?groupid="+qh_static_data.qhfyp1_groupno;
                StringRequest stringRequest=new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        //        Populating Recycler View
        mettinginfofyp1 =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/meetingDetails?groupid="+qh_static_data.qhfyp1_groupno;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                qh_fyp1_meetinginfo_tbl_list data = new qh_fyp1_meetinginfo_tbl_list();
                                data.setMi_fyp1_stdprojecttitle(jsonObject.getString("project_title").toString());
                                data.setMi_fyp1_groupno(jsonObject.getInt("group_no"));
                                data.setMi_fyp1_meetingtime(jsonObject.getString("meeting_time").toString());
                                data.setMi_fyp1_stdcls(jsonObject.getString("std_class").toString());
                                data.setMi_fyp1_stdname(jsonObject.getString("std_name").toString());
                                data.setMi_fyp1_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                data.setMi_fyp1_technology(jsonObject.getString("technology").toString());
                                data.setMi_fyp1_regno(jsonObject.getString("reg_no").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                mettinginfofyp1.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        qh_meetinginfos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new qh_fyp1_meetinginfo_rv_adapter(getApplicationContext(), mettinginfofyp1);
                        qh_meetinginfos.setAdapter(adapter);
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
//        btnmoreopt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //--
//                LayoutInflater li = LayoutInflater.from(getApplicationContext());
//                View promptsView = li.inflate(R.layout.qh_optionbuttons, null);
//                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Qh_MeetingInformation.this);
//                alertDialog.setView(promptsView);
//                alertDialog.setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                // create alert dialog
//                AlertDialog alertDialog1 = alertDialog.create();
//                alertDialog.show();
//                //--
//            }
//        });
    }
}