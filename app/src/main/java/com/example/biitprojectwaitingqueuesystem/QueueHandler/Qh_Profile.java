package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.biitprojectwaitingqueuesystem.R;

public class Qh_Profile extends AppCompatActivity {
    EditText qhname,qhemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qh_profile);
        qhname=findViewById(R.id.editTextTextPersonName_qhname);
        qhemail=findViewById(R.id.editTextTextEmailAddress_qhemail);
        qhname.setText(qh_static_data.qh_username);
        qhname.setEnabled(false);
        qhemail.setText(qh_static_data.qh_useremail);
        qhemail.setEnabled(false);
    }
}