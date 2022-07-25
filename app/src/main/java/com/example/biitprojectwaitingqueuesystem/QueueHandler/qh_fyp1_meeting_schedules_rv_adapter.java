package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitprojectwaitingqueuesystem.R;

import org.w3c.dom.Text;

import java.util.List;

public class qh_fyp1_meeting_schedules_rv_adapter extends RecyclerView.Adapter<qh_fyp1_meeting_schedules_rv_adapter.View_Holder>{
    Context mContext;
    LayoutInflater inflater;
    List<qh_fyp1_meeting_schedules_list_tbl> fyp1_meetings;
    public qh_fyp1_meeting_schedules_rv_adapter(Context ctx, List<qh_fyp1_meeting_schedules_list_tbl> fyp1_meetings){
        this.inflater=LayoutInflater.from(ctx);
        this.fyp1_meetings=fyp1_meetings;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.qh_meetingschedules_fyp1_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.groupname.setText(fyp1_meetings.get(position).getFyp1_projecttitle());
        holder.supnamefyp1.setText(fyp1_meetings.get(position).getFyp1_stdsupervisor());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return fyp1_meetings.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView groupname,supnamefyp1;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            groupname=itemView.findViewById(R.id.fyp1_meetinggroupname);
            supnamefyp1=itemView.findViewById(R.id.supnamefyp1);
            itemView.setOnClickListener(v -> {
//                Toast.makeText(v.getContext(), ""+fyp1_meetings.get(position).getFyp1_groupno(), Toast.LENGTH_SHORT).show();
                qh_static_data.qhfyp1_groupno=fyp1_meetings.get(position).getFyp1_groupno();
                mContext.startActivity(new Intent(mContext, Qh_MeetingInformation.class));
            });
        }
    }
}
