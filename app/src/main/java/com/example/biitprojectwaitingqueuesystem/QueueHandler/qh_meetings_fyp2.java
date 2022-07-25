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

public class qh_meetings_fyp2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView fyp2_meetingrv;
    Button sforsfyp2,delaym;
    ImageView fmgroupsfyp2,mgroupsfyp2;
    Spinner spsupervisorsfyp2;
    List<qh_fyp2_meeting_schedules_list_tbl> fyp2_meetings;
    qh_fyp2_meeting_schedules_rv_adapter adapter2;
    TextView timepickerfyp2;
    int thour2,tmin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meetings_fyp2);
        fyp2_meetingrv=findViewById(R.id.qh_meetings_fypii_rv);
        spsupervisorsfyp2=findViewById(R.id.qh_spsupervisors_fyp2_sp);
        sforsfyp2=findViewById(R.id.searchforsupervisorbtnfyp2);
        fmgroupsfyp2=findViewById(R.id.female_group_meetingsimgfyp2);
        mgroupsfyp2=findViewById(R.id.malegroup_meetingsimgfyp2);
        timepickerfyp2=findViewById(R.id.timpickerfyp2);
        delaym=findViewById(R.id.delay_mfyp2);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        delaym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/delaygroupfyp2";
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
//        ---TIme picker
        timepickerfyp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        qh_meetings_fyp2.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                thour2=hourOfDay;
                                tmin2=minute;
                                String time=thour2+":"+tmin2;
                                SimpleDateFormat f24Hours=new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours=new SimpleDateFormat(
                                            "hh:mm"
                                    );
                                    timepickerfyp2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(thour2,tmin2);

                timePickerDialog.show();
            }
        });


        //        Populating Recycler View
        fyp2_meetings =new ArrayList<>();
        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/allMeetingsfyp2";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                qh_fyp2_meeting_schedules_list_tbl data = new qh_fyp2_meeting_schedules_list_tbl();
                                data.setFyp2_projecttitle(jsonObject.getString("project_title").toString());
                                data.setFyp2_stdsupervisor(jsonObject.getString("std_supervisor").toString());
                                data.setFyp2_groupno(jsonObject.getInt("group_no"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                fyp2_meetings.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        fyp2_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter2 = new qh_fyp2_meeting_schedules_rv_adapter(getApplicationContext(), fyp2_meetings);
                        fyp2_meetingrv.setAdapter(adapter2);
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
        fmgroupsfyp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp2_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/femalegroupMeetingsfyp2";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp2_meeting_schedules_list_tbl data = new qh_fyp2_meeting_schedules_list_tbl();
                                        data.setFyp2_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp2_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp2_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp2_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter2 = new qh_fyp2_meeting_schedules_rv_adapter(getApplicationContext(), fyp2_meetings);
                                fyp2_meetingrv.setAdapter(adapter2);
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
        mgroupsfyp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp2_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/malegroupMeetingsfyp2";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp2_meeting_schedules_list_tbl data = new qh_fyp2_meeting_schedules_list_tbl();
                                        data.setFyp2_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp2_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp2_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp2_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter2 = new qh_fyp2_meeting_schedules_rv_adapter(getApplicationContext(), fyp2_meetings);
                                fyp2_meetingrv.setAdapter(adapter2);
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
        spsupervisorsfyp2.setAdapter(spadapter);
        spsupervisorsfyp2.setOnItemSelectedListener(this);
//        Toast.makeText(getApplicationContext(), spsupervisors.getSelectedItem().toString()+"", Toast.LENGTH_SHORT).show();
        sforsfyp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                fyp2_meetings =new ArrayList<>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/QueueHandler/sortbySupervisorsMeetingsfyp2?sname="+spsupervisorsfyp2.getSelectedItem().toString();
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        qh_fyp2_meeting_schedules_list_tbl data = new qh_fyp2_meeting_schedules_list_tbl();
                                        data.setFyp2_projecttitle(jsonObject.getString("project_title").toString());
                                        data.setFyp2_groupno(jsonObject.getInt("group_no"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        fyp2_meetings.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                fyp2_meetingrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter2 = new qh_fyp2_meeting_schedules_rv_adapter(getApplicationContext(), fyp2_meetings);
                                fyp2_meetingrv.setAdapter(adapter2);
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