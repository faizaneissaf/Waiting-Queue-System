package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class qh_meetings_fyp1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView fyp1_meetingrv;
    Button sfors,delayfyp1;
    ImageView fmgroups,mgroups;
    Spinner spsupervisors;
    TextView timepickerfyp1;
    int thour,tmin;
    List<qh_fyp1_meeting_schedules_list_tbl> fyp1_meetings;
    qh_fyp1_meeting_schedules_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meetings_fyp1);
        fyp1_meetingrv=findViewById(R.id.qh_meetings_fypi_rv);
        fmgroups=findViewById(R.id.female_group_meetingsimg);
        mgroups=findViewById(R.id.malegroup_meetingsimg);
        spsupervisors=findViewById(R.id.qh_spsupervisors_sp);
        sfors=findViewById(R.id.searchforsupervisorbtn);
        timepickerfyp1=findViewById(R.id.timepickerfyp1);
        delayfyp1=findViewById(R.id.dtimefyp1);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        delayfyp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/delaygroupfyp1";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Meeting Time Updated To All Students", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        //----------timepicker
        timepickerfyp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        qh_meetings_fyp1.this,
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
                                            "hh:mm"
                                    );
                                    timepickerfyp1.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(thour,tmin);
                timePickerDialog.show();
            }
        });

        //        Populating Recycler View
        fyp1_meetings =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/allMeetings";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                qh_fyp1_meeting_schedules_list_tbl data = new qh_fyp1_meeting_schedules_list_tbl();
                                data.setFyp1_projecttitle(jsonObject.getString("project_title").toString());
                                data.setFyp1_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                data.setFyp1_groupno(jsonObject.getInt("group_no"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                fyp1_meetings.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        fyp1_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new qh_fyp1_meeting_schedules_rv_adapter(getApplicationContext(), fyp1_meetings);
                        fyp1_meetingrv.setAdapter(adapter);
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
        fmgroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp1_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/femalegroupMeetings";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp1_meeting_schedules_list_tbl data = new qh_fyp1_meeting_schedules_list_tbl();
                                        data.setFyp1_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp1_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp1_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp1_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new qh_fyp1_meeting_schedules_rv_adapter(getApplicationContext(), fyp1_meetings);
                                fyp1_meetingrv.setAdapter(adapter);
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
        mgroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp1_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/malegroupMeetings";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp1_meeting_schedules_list_tbl data = new qh_fyp1_meeting_schedules_list_tbl();
                                        data.setFyp1_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp1_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp1_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp1_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new qh_fyp1_meeting_schedules_rv_adapter(getApplicationContext(), fyp1_meetings);
                                fyp1_meetingrv.setAdapter(adapter);
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
        //        Spinner
        ArrayAdapter<CharSequence> spadapter=ArrayAdapter.createFromResource(this,R.array.sp_supervisors,R.layout.support_simple_spinner_dropdown_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsupervisors.setAdapter(spadapter);
        spsupervisors.setOnItemSelectedListener(this);
//        Toast.makeText(getApplicationContext(), spsupervisors.getSelectedItem().toString()+"", Toast.LENGTH_SHORT).show();
        sfors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp1_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/sortbySupervisorsMeetings?sname="+spsupervisors.getSelectedItem().toString();
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp1_meeting_schedules_list_tbl data = new qh_fyp1_meeting_schedules_list_tbl();
                                        data.setFyp1_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp1_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp1_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp1_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new qh_fyp1_meeting_schedules_rv_adapter(getApplicationContext(), fyp1_meetings);
                                fyp1_meetingrv.setAdapter(adapter);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}