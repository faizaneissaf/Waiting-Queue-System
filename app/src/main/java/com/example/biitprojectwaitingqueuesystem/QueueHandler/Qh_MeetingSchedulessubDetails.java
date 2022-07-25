package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.biitprojectwaitingqueuesystem.R;

public class Qh_MeetingSchedulessubDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meeting_schedulessub_details);

        findViewById(R.id.qhmoreinfo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Qh_MeetingInformation.class);
                startActivity(i);
            }
        });
    }
}