package com.example.biitprojectwaitingqueuesystem.Supervisor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.biitprojectwaitingqueuesystem.Student.Student_Dashboard;
import com.example.biitprojectwaitingqueuesystem.loginuser_staticdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supervisor_Dashboard extends AppCompatActivity {
    TextView titlename;
    ImageView logout,supnotification;
    int isMeeting=0;
    List<String> date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_dashboard);
        titlename=findViewById(R.id.sptitlename);
        logout=findViewById(R.id.supervisor_logout);
        supnotification=findViewById(R.id.sup_notificationBtn);
        logout.setOnClickListener(new View.OnClickListener() {
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
        supervisor_static_data.ipaddress=getString(R.string.ip);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int n=0;n<response.length();n++){
                    try {
                        JSONObject object = response.getJSONObject(n);
                        supervisor_static_data.spid= object.getInt("user_id");
                        supervisor_static_data.spname= object.getString("user_name");
                        supervisor_static_data.spemail= object.getString("user_email");
                        titlename.setText(supervisor_static_data.spname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        ////-------spinner items

                ///---------------
                findViewById(R.id.sMettingdetail_Box).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), Supervisor_MeetingDetailsPage.class);
                        startActivity(i);
                    }
                });
        findViewById(R.id.sProfile_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Supervisor_Groups.class);
                startActivity(i);
            }
        });
        findViewById(R.id.sGroups_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Supervisor_Groups.class);
                startActivity(i);
            }
        });
        findViewById(R.id.sHistory_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Supervisor_History.class);
                startActivity(i);
            }
        });
        findViewById(R.id.supervisor_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ////------------Checking Meetings
        supnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Displaying current date and time in 12 hour format with AM/PM
                DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                String currentdate = dateFormat2.format(new Date()).toString();
//                Toast.makeText(getApplicationContext(), currentdate+"", Toast.LENGTH_SHORT).show();
                date=new ArrayList<String>();
                String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Supervisor/myGroups?sup="+supervisor_static_data.spname;
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        date.add(jsonObject.getString("meeting_date").toString());
//                                        Toast.makeText(getApplicationContext(), jsonObject.getString("meeting_date")+"", Toast.LENGTH_SHORT).show();
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
//                                Toast.makeText(getApplicationContext(), date.size()+"", Toast.LENGTH_SHORT).show();
                                if (date.contains(currentdate)){
                                    isMeeting=1;
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
                if (isMeeting==1){
                    AlertDialog alertDialog = new AlertDialog.Builder(Supervisor_Dashboard.this)
                            .setTitle("Your Students Have Meetings Today")
                            .setPositiveButton("See Details", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(getApplicationContext(),Supervisor_Groups.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                    //            Alert Dialog
                }else if (isMeeting==0){
                    AlertDialog alertDialog = new AlertDialog.Builder(Supervisor_Dashboard.this)
                            .setTitle("Today No meetings Scheduled")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                    //            Alert Dialog
                }
            }
        });
        /////-----------------------------------
    }
}