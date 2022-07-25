package com.example.biitprojectwaitingqueuesystem.Supervisor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_MeetingInformationfyp2;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_meetings_fyp1;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_meetings_fyp2;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_static_data;
import com.example.biitprojectwaitingqueuesystem.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class supervisor_meeting_schedule_rv_adapter extends RecyclerView.Adapter<supervisor_meeting_schedule_rv_adapter.View_Holder>{
    Context mContext;
    LayoutInflater inflater;
    List<supervisor_meeting_schedule_list_tbl> sm_schedules;
    public supervisor_meeting_schedule_rv_adapter(Context ctx, List<supervisor_meeting_schedule_list_tbl> sm_schedules){
        this.inflater=LayoutInflater.from(ctx);
        this.sm_schedules=sm_schedules;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.supervisor_meetingschedules_rv_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.spm_stdregno.setText(sm_schedules.get(position).getSpm_stdregno());
        holder.spm_stdname.setText(sm_schedules.get(position).getSpm_stdname());
        holder.spm_stdcls.setText(sm_schedules.get(position).getSpm_stdcls());
        holder.spm_stdsupervisor.setText(sm_schedules.get(position).getSpm_stdsupervisor());
        holder.spm_stdprojecttitle.setText(sm_schedules.get(position).getSpm_stdprojecttitle());
        holder.spm_stdtechnology.setText(sm_schedules.get(position).getSpm_stdtechnology());
        holder.spm_stdmeetingtime.setText(sm_schedules.get(position).getSpm_stdtime());
        holder.spm_stdmeetingdate.setText(sm_schedules.get(position).getSpm_stddate());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return sm_schedules.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView spm_stdregno,spm_stdname,spm_stdcls,spm_stdsupervisor,spm_stdprojecttitle,spm_stdtechnology,spm_stdmeetingtime,spm_stdmeetingdate;
        Button btnsent;

        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            spm_stdregno=itemView.findViewById(R.id.spm_stdregno);
            spm_stdname=itemView.findViewById(R.id.spm_stdname);
            spm_stdcls=itemView.findViewById(R.id.spm_stdcls);
            spm_stdsupervisor=itemView.findViewById(R.id.spm_stdsupervisor);
            spm_stdprojecttitle=itemView.findViewById(R.id.spm_stdprojecttitle);
            spm_stdtechnology=itemView.findViewById(R.id.spm_stdtechnology);
            spm_stdmeetingtime=itemView.findViewById(R.id.spm_stdmeetingtime);
            spm_stdmeetingdate=itemView.findViewById(R.id.spm_stdmeetingdate);
            btnsent=itemView.findViewById(R.id.btn_senttime);
//            itemView.setOnClickListener(v -> {
//                supervisor_static_data.std_regno=sm_schedules.get(position).getSpm_stdregno();
//                mContext.startActivity(new Intent(mContext, Supervisor_stdMeetingInformation.class));
//            });
            btnsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    supervisor_static_data.std_regno=sm_schedules.get(position).getSpm_stdregno();
                    mContext.startActivity(new Intent(mContext, Supervisor_stdMeetingInformation.class));
                }
            });
        }
    }
}
