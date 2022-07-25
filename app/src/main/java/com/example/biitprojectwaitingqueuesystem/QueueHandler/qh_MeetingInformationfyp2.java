package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class qh_MeetingInformationfyp2 extends AppCompatActivity {
//    Button btnmoreoptfyp2;
    Button btncall,btnskip,btncancel;
    RecyclerView qh_meetinginfosfyp2;
    List<qh_fy2_meetinginfo_tbl_list> mettinginfofyp2;
    qh_fy2_meetinginfo_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meeting_informationfyp2);
        btncall=findViewById(R.id.btncallfyp2);
        btnskip=findViewById(R.id.btnskipfyp2);
        btncancel=findViewById(R.id.btncancelfyp2);
//        btnmoreoptfyp2=findViewById(R.id.Button_optionsfyp2);
        qh_meetinginfosfyp2=findViewById(R.id.qh_groupInfo_rvfyp2);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //--call
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/callgroupfyp2?groupid="+qh_static_data.qhfyp2_groupno;
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
        //--skip
        //----Skip
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/skipgroupfyp2?groupid="+qh_static_data.qhfyp2_groupno;
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
        //---cancel
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/cancelgroupfyp2?groupid="+qh_static_data.qhfyp2_groupno;
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
        mettinginfofyp2 =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/meetingDetailsfyp2?groupid="+qh_static_data.qhfyp2_groupno;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                qh_fy2_meetinginfo_tbl_list data = new qh_fy2_meetinginfo_tbl_list();
                                data.setMi_fyp2_stdprojecttitle(jsonObject.getString("project_title").toString());
                                data.setMi_fyp2_groupno(jsonObject.getInt("group_no"));
                                data.setMi_fyp2_meetingtime(jsonObject.getString("meeting_time").toString());
                                data.setMi_fyp2_stdcls(jsonObject.getString("std_class").toString());
                                data.setMi_fyp2_stdname(jsonObject.getString("std_name").toString());
                                data.setMi_fyp2_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                data.setMi_fyp2_technology(jsonObject.getString("technology").toString());
                                data.setMi_fyp2_regno(jsonObject.getString("reg_no").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                mettinginfofyp2.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        qh_meetinginfosfyp2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new qh_fy2_meetinginfo_rv_adapter(getApplicationContext(), mettinginfofyp2);
                        qh_meetinginfosfyp2.setAdapter(adapter);
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
//        btnmoreoptfyp2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //--
//                LayoutInflater li = LayoutInflater.from(getApplicationContext());
//                View promptsView = li.inflate(R.layout.qh_optionbuttons, null);
//                AlertDialog.Builder alertDialog=new AlertDialog.Builder(qh_MeetingInformationfyp2.this);
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