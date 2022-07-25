package com.example.biitprojectwaitingqueuesystem.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biitprojectwaitingqueuesystem.R;

public class Student_ProfilePage extends AppCompatActivity {
    EditText stdname,stdemail,stdsupervisor,stdaridno;
    Button updatesprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_page);
        stdname=findViewById(R.id.editTextTextPersonName_stdname);
        stdemail=findViewById(R.id.editTextTextEmailAddress_stdemail);
        stdaridno=findViewById(R.id.editTextTextPersonName_stdaridno);
        stdsupervisor=findViewById(R.id.editTextPhone_supervisoer);
        updatesprofile=findViewById(R.id.button_updatesprofile);


        stdname.setText(student_static_data.std_fullname);
        stdname.setEnabled(false);
        stdemail.setText(student_static_data.std_emailid);
        stdemail.setEnabled(false);
        stdaridno.setText(student_static_data.std_aridno);
        stdaridno.setEnabled(false);
        stdsupervisor.setText(student_static_data.std_supervisor);
        stdsupervisor.setEnabled(false);

    }
}