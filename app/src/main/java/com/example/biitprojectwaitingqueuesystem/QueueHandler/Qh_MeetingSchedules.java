package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.biitprojectwaitingqueuesystem.R;

public class Qh_MeetingSchedules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meeting_schedules);

        findViewById(R.id.qfypi_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), qh_meetings_fyp1.class);
                startActivity(i);
            }
        });
        findViewById(R.id.qfypii_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), qh_meetings_fyp2.class);
                startActivity(i);
            }
        });
    }
}