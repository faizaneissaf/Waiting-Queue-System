package com.example.biitprojectwaitingqueuesystem;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.ProjectCommittee.Pq_Dashboard;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.QueueHandler_Dashboard;
import com.example.biitprojectwaitingqueuesystem.Services.APIClient;
import com.example.biitprojectwaitingqueuesystem.Student.Student_Dashboard;
import com.example.biitprojectwaitingqueuesystem.Student.student_static_data;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_Dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_Page extends AppCompatActivity {
    EditText loginpass,loginmail;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnlogin=findViewById(R.id.mainloginbtn_v);
        loginpass=findViewById(R.id.loginpass);
        loginmail=findViewById(R.id.et_loginemail);

        findViewById(R.id.textView6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Signup_Page.class);
                startActivity(i);
            }
        });
//        Starting Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.start();
                //        Getting ip from Strings
                String ip= getString(R.string.ip);
        APIClient.ipa=ip;
//        Getting ip from Strings
//        Login Button
                btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email=loginmail.getText().toString();
                        String password=loginpass.getText().toString();
                        String url=ip+"/BIITWaitingQueueSystem/api/LoginSignup/login?email="+email+"&password="+password;
                        StringRequest stringRequest=new StringRequest(
                                Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                                loginuser_staticdata.useremail=email;
                                String[] arr=email.split("@");
                                String rno=arr[0];
//                                Toast.makeText(getApplicationContext(), rno+"", Toast.LENGTH_SHORT).show();
                                student_static_data.std_aridno=rno;
                                if(response.contains("Student")){
                                    Intent intent =new Intent(getApplicationContext(),Student_Dashboard.class);
                                    startActivity(intent);
                                }else if (response.contains("Supervisor")){
                                    Intent intent =new Intent(getApplicationContext(),Supervisor_Dashboard.class);
                                    startActivity(intent);
                                }else if(response.contains("QueueHandler")){
                                    Intent intent =new Intent(getApplicationContext(),QueueHandler_Dashboard.class);
                                    startActivity(intent);
                                }else if (response.contains("ProjectCommittie")){
                                    Intent intent =new Intent(getApplicationContext(),Pq_Dashboard.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Invalid Email/Password", Toast.LENGTH_SHORT).show();
                                }
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
            }
}