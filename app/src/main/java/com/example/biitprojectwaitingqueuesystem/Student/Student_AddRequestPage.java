package com.example.biitprojectwaitingqueuesystem.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Student_AddRequestPage extends AppCompatActivity {
    EditText reqmsg;
    Button btnsubmitreq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_request_page);
        reqmsg=findViewById(R.id.editTextTextPersonName_reqmsg);
        btnsubmitreq=findViewById(R.id.button_submitreqmsg);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //--------
        btnsubmitreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("req_msg",reqmsg.getText().toString());
                    obj.put("std_name",student_static_data.std_fullname);
                    obj.put("std_reg_no",student_static_data.std_aridno);
                    obj.put("std_cls",student_meetinginfo_data.sm_stdcls);
                    obj.put("msg_status",0);
                    obj.put("std_supervisor",student_meetinginfo_data.sm_stdsupervisor);
                    String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/Student/sendreqMessage";
//                    Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Request Submit Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Student_Dashboard.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "No Approved", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    requestQueue.add(jsonObjectRequest);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}