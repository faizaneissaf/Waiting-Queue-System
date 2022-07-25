package com.example.biitprojectwaitingqueuesystem.Student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.biitprojectwaitingqueuesystem.loginuser_staticdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Student_Dashboard extends AppCompatActivity {
    TextView stdname,stdaridno,mymeetingstatus,expectedtime,exptime;
    String regno;
    ImageView notificationbtn,logoutstd;
    String start_date,end_date;
    Date d1,d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        stdaridno=findViewById(R.id.std_aridnotitle);
        stdname=findViewById(R.id.std_fullname);
        mymeetingstatus=findViewById(R.id.std_meetingstatus);
        expectedtime=findViewById(R.id.std_expectedtime);
        exptime=findViewById(R.id.expectedtimestd);
        notificationbtn=findViewById(R.id.stdnotificaionbtn);
        logoutstd=findViewById(R.id.std_logout);
        logoutstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//----User Profile
        String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Student/userProfile?email="+loginuser_staticdata.useremail;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int n = 0; n < response.length(); n++)
                {
                    try {
                        JSONObject object = response.getJSONObject(n);
                        student_static_data.stdid= object.getInt("user_id");
                        student_static_data.std_fullname= object.getString("user_name");
                        student_static_data.std_emailid= object.getString("user_email");
//                        student_static_data.std_aridno= object.getString("std_regno");
//                        student_static_data.std_supervisor=object.getString("std_supervisor");
                        stdname.setText(student_static_data.std_fullname);
                        stdaridno.setText(student_static_data.std_aridno);

                        ////---Student Meeting Information
                        String url2=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Student/meetingInfoStudent?regno="+student_static_data.std_aridno;
                        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(
                                Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int n = 0; n < response.length(); n++)
                                {
                                    try {
                                        JSONObject object = response.getJSONObject(n);
                                        student_meetinginfo_data.sm_meetingid=object.getInt("meeting_id");
                                        student_meetinginfo_data.sm_groupno=object.getInt("group_no");
                                        student_meetinginfo_data.sm_regno=object.getString("reg_no");
                                        student_meetinginfo_data.sm_stdname=object.getString("std_name");
                                        student_meetinginfo_data.sm_stdcls=object.getString("std_class");
                                        student_meetinginfo_data.sm_stdsupervisor=object.getString("std_supervisor");
                                        student_meetinginfo_data.sm_projecttitle=object.getString("project_title");
                                        student_meetinginfo_data.sm_technology=object.getString("technology");
                                        student_meetinginfo_data.sm_meetingtime=object.getString("meeting_time");
                                        student_meetinginfo_data.sm_meetingdate=object.getString("meeting_date");
                                        student_meetinginfo_data.sm_meetingstaus=object.getInt("meeting_status");

                                        mymeetingstatus.setText("Your Meeting will be on\n"+student_meetinginfo_data.sm_meetingdate+" , "+student_meetinginfo_data.sm_meetingtime);
                                        String et=student_meetinginfo_data.sm_meetingdate;

                                        //Displaying current time in 12 hour format with AM/PM
                                        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                                        String timeString = timeFormat.format(new Date()).toString();

                                        //Displaying current date and time in 12 hour format with AM/PM
                                        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                                        String dateString2 = dateFormat2.format(new Date()).toString();
                                        String[] t=student_meetinginfo_data.sm_meetingtime.split(" ",2);
                                        ///---------------Expected Time
                                        start_date=student_meetinginfo_data.sm_meetingdate+" "+t[0];
                                        end_date=dateString2+" "+timeString;
                                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                                        Date d1 = null;
                                        Date d2 = null;
                                        try {
                                            d1 = format.parse(end_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            d2 = format.parse(start_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        long diff = d2.getTime() - d1.getTime();
                                        long diffHours = diff / (60 * 60 * 1000) % 24;
                                        long diffMinutes = diff / (60 * 1000) % 60;
                                        //---days
                                        long diffDays = diff / (24 * 60 * 60 * 1000);
                                        exptime.setText(String.valueOf(diffHours)+" Hours and "+String.valueOf(diffMinutes)+" Minutes");
                                        //----------------------------


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
                        requestQueue.add(jsonArrayRequest2);
                        ////---Student Meeting Information

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
        if(student_meetinginfo_data.sm_meetingstaus!=0){
            notificationbtn.setImageResource(R.drawable.notificationsolidbadged);
        }
        //-----------Notification Button
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student_meetinginfo_data.sm_meetingstaus==0){
                    AlertDialog alertDialog = new AlertDialog.Builder(Student_Dashboard.this)
                            .setTitle("No New Notification Found!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                    //            Alert Dialog
                }
                else if (student_meetinginfo_data.sm_meetingstaus==1){
                    AlertDialog alertDialog = new AlertDialog.Builder(Student_Dashboard.this)
                            .setTitle("You Have been Called from Meeting Room")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                    //            Alert Dialog
                }
                else if (student_meetinginfo_data.sm_meetingstaus==2){
                    AlertDialog alertDialog = new AlertDialog.Builder(Student_Dashboard.this)
                            .setTitle("Your Meeting Will be skipped")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                    //            Alert Dialog
                }
                else    if (student_meetinginfo_data.sm_meetingstaus==3){
                    AlertDialog alertDialog = new AlertDialog.Builder(Student_Dashboard.this)
                            .setTitle("Your Meeting Will be canceled")
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
//        --Notification

        findViewById(R.id.Mettinginfo_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Student_MeetingInfoPage.class);
                startActivity(i);
            }
        });
        findViewById(R.id.Profile_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Student_ProfilePage.class);
                startActivity(i);
            }
        });
        findViewById(R.id.History_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Student_HistoryPage.class);
                startActivity(i);
            }
        });
        findViewById(R.id.Groups_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Student_AddRequestPage.class);
                startActivity(i);
            }
        });
    }
}