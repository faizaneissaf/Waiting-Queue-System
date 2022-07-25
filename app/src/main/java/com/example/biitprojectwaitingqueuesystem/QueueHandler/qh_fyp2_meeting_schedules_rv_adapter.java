package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitprojectwaitingqueuesystem.R;

import java.util.List;

public class qh_fyp2_meeting_schedules_rv_adapter extends RecyclerView.Adapter<qh_fyp2_meeting_schedules_rv_adapter.View_Holder> {
    Context mContext;
    LayoutInflater inflater;
    List<qh_fyp2_meeting_schedules_list_tbl> fyp2_meetings;
    public qh_fyp2_meeting_schedules_rv_adapter(Context ctx, List<qh_fyp2_meeting_schedules_list_tbl> fyp2_meetings){
        this.inflater=LayoutInflater.from(ctx);
        this.fyp2_meetings=fyp2_meetings;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.qh_meetingschedules_fyp2_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.groupnamefyp2.setText(fyp2_meetings.get(position).getFyp2_projecttitle());
        holder.supnamefyp2.setText(fyp2_meetings.get(position).getFyp2_stdsupervisor());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return fyp2_meetings.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView groupnamefyp2,supnamefyp2;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            groupnamefyp2=itemView.findViewById(R.id.fyp2_meetinggroupname);
            supnamefyp2=itemView.findViewById(R.id.supnamefyp2);

            itemView.setOnClickListener(v -> {
//                Toast.makeText(v.getContext(), ""+fyp1_meetings.get(position).getFyp1_groupno(), Toast.LENGTH_SHORT).show();
                qh_static_data.qhfyp2_groupno=fyp2_meetings.get(position).getFyp2_groupno();
                mContext.startActivity(new Intent(mContext, qh_MeetingInformationfyp2.class));
            });
        }
    }
}
