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

public class qh_fy2_meetinginfo_rv_adapter extends RecyclerView.Adapter<qh_fy2_meetinginfo_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<qh_fy2_meetinginfo_tbl_list> mettinginfofyp2;
    public qh_fy2_meetinginfo_rv_adapter(Context ctx, List<qh_fy2_meetinginfo_tbl_list> mettinginfofyp2){
        this.inflater=LayoutInflater.from(ctx);
        this.mettinginfofyp2=mettinginfofyp2;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.qh_groupinformation_data_row_fyp2,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.mii_mname2.setText(mettinginfofyp2.get(position).getMi_fyp2_stdname());
        holder.mii_pt2.setText(mettinginfofyp2.get(position).getMi_fyp2_stdprojecttitle());
        holder.mii_rgno2.setText(mettinginfofyp2.get(position).getMi_fyp2_regno());
        holder.mii_cls2.setText(mettinginfofyp2.get(position).getMi_fyp2_stdcls());
        holder.mii_sname2.setText(mettinginfofyp2.get(position).getMi_fyp2_stdsupervisor());
        holder.mii_time2.setText(mettinginfofyp2.get(position).getMi_fyp2_meetingtime());
        holder.mii_tech2.setText(mettinginfofyp2.get(position).getMi_fyp2_technology());
    }

    @Override
    public int getItemCount() {
        return mettinginfofyp2.size();
    }

    public class View_Holder  extends RecyclerView.ViewHolder{
        TextView mii_pt2,mii_mname2,mii_sname2,mii_rgno2,mii_time2,mii_cls2,mii_tech2;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            mii_mname2=itemView.findViewById(R.id.mi_stdname_fyp12);
            mii_pt2=itemView.findViewById(R.id.mi_prjtitle2);
            mii_rgno2=itemView.findViewById(R.id.mi_regno2);
            mii_cls2=itemView.findViewById(R.id.mi_stdclsfyp12);
            mii_sname2=itemView.findViewById(R.id.mi_stdsupervisorfyp12);
            mii_time2=itemView.findViewById(R.id.mi_mtngtime2);
            mii_tech2=itemView.findViewById(R.id.mi_stdtechfyp12);
        }
    }
}
