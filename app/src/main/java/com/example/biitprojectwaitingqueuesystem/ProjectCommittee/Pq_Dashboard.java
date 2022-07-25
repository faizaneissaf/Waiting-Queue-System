package com.example.biitprojectwaitingqueuesystem.ProjectCommittee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_Groups;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_History;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_MeetingDetailsPage;
import com.example.biitprojectwaitingqueuesystem.Supervisor.Supervisor_Profile;

public class Pq_Dashboard extends AppCompatActivity {
    ImageView qhlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_dashboard);
        qhlogout=findViewById(R.id.pq_logout);
        qhlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.pqaddMetting_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Pq_ScheduleMeetings.class);
                startActivity(i);
            }
        });
        findViewById(R.id.pqProfile_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Pq_Profile.class);
                startActivity(i);
            }
        });
        findViewById(R.id.pqaddqh_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Pq_Addqh.class);
                startActivity(i);
            }
        });
        findViewById(R.id.pqmeetings_Box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Pq_meetings.class);
                startActivity(i);
            }
        });
    }
}