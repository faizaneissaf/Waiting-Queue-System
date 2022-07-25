package com.example.biitprojectwaitingqueuesystem.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.biitprojectwaitingqueuesystem.R;

public class Student_MeetingInfoPage extends AppCompatActivity {
    TextView stdname,stdaridno,stdcls,stdsupervisor,stdprojtitle,stdtechnology,stdmeetingtime,stdmeetingdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_meeting_info_page);
        stdname=findViewById(R.id.mi_stdname);
        stdaridno=findViewById(R.id.mi_stdaridno);
        stdcls=findViewById(R.id.mi_stdsection);
        stdsupervisor=findViewById(R.id.mi_stdsupervisor);
        stdprojtitle=findViewById(R.id.mi_stdprojtitle);
        stdtechnology=findViewById(R.id.mi_stdtechnology);
        stdmeetingtime=findViewById(R.id.mi_stdmeetingtime);
        stdmeetingdate=findViewById(R.id.mi_stdmeetingdate);

        stdname.setText(student_meetinginfo_data.sm_stdname);
        stdaridno.setText(student_meetinginfo_data.sm_regno);
        stdcls.setText(student_meetinginfo_data.sm_stdcls);
        stdsupervisor.setText(student_meetinginfo_data.sm_stdsupervisor);
        stdprojtitle.setText(student_meetinginfo_data.sm_projecttitle);
        stdtechnology.setText(student_meetinginfo_data.sm_technology);
        stdmeetingtime.setText(student_meetinginfo_data.sm_meetingtime);
        stdmeetingdate.setText(student_meetinginfo_data.sm_meetingdate);
    }
}