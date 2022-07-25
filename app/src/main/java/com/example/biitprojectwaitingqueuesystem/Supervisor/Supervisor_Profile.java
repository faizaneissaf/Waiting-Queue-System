package com.example.biitprojectwaitingqueuesystem.Supervisor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.biitprojectwaitingqueuesystem.R;

public class Supervisor_Profile extends AppCompatActivity {
    EditText spname,spemail,sppass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_profile);
        spname=findViewById(R.id.editTextTextPersonName_spname);
        spemail=findViewById(R.id.editTextTextEmailAddress_spemail);
        sppass=findViewById(R.id.editTextTextPassword2_sppass);
        spname.setText(supervisor_static_data.spname);
        spname.setEnabled(false);
        spemail.setText(supervisor_static_data.spemail);
        spemail.setEnabled(false);
    }
}