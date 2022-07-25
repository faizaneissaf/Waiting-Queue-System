package com.example.biitprojectwaitingqueuesystem.QueueHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitprojectwaitingqueuesystem.R;

import java.util.List;

public class qh_fyp1_meetinginfo_rv_adapter extends RecyclerView.Adapter<qh_fyp1_meetinginfo_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<qh_fyp1_meetinginfo_tbl_list> mettinginfofyp1;
    public qh_fyp1_meetinginfo_rv_adapter(Context ctx, List<qh_fyp1_meetinginfo_tbl_list> mettinginfofyp1){
        this.inflater=LayoutInflater.from(ctx);
        this.mettinginfofyp1=mettinginfofyp1;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.qh_groupinformation_data_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.mii_mname.setText(mettinginfofyp1.get(position).getMi_fyp1_stdname());
        holder.mii_pt.setText(mettinginfofyp1.get(position).getMi_fyp1_stdprojecttitle());
        holder.mii_rgno.setText(mettinginfofyp1.get(position).getMi_fyp1_regno());
        holder.mii_cls.setText(mettinginfofyp1.get(position).getMi_fyp1_stdcls());
        holder.mii_sname.setText(mettinginfofyp1.get(position).getMi_fyp1_stdsupervisor());
        holder.mii_time.setText(mettinginfofyp1.get(position).getMi_fyp1_meetingtime());
        holder.mii_tech.setText(mettinginfofyp1.get(position).getMi_fyp1_technology());
    }

    @Override
    public int getItemCount() {
        return mettinginfofyp1.size();
    }

    public class View_Holder  extends RecyclerView.ViewHolder{
        TextView mii_pt,mii_mname,mii_sname,mii_rgno,mii_time,mii_cls,mii_tech;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            mii_mname=itemView.findViewById(R.id.mi_stdname_fyp1);
            mii_pt=itemView.findViewById(R.id.mi_prjtitle);
            mii_rgno=itemView.findViewById(R.id.mi_regno);
            mii_cls=itemView.findViewById(R.id.mi_stdclsfyp1);
            mii_sname=itemView.findViewById(R.id.mi_stdsupervisorfyp1);
            mii_time=itemView.findViewById(R.id.mi_mtngtime);
            mii_tech=itemView.findViewById(R.id.mi_stdtechfyp1);
        }
    }
}
