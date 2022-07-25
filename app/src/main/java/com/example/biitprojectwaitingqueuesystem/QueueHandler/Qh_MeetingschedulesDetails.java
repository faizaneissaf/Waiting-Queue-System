package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.biitprojectwaitingqueuesystem.R;

public class Qh_MeetingschedulesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_meetingschedules_details);

        findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Qh_MeetingSchedulessubDetails.class);
                startActivity(i);
            }
        });
    }
}