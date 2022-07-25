package com.example.biitprojectwaitingqueuesystem.ProjectCommittee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.Supervisor.supervisor_meetings_list_tbl;
import com.example.biitprojectwaitingqueuesystem.Supervisor.supervisor_meetings_rv_adapter;

import java.util.List;

public class pq_allgroups_rv_adapter extends RecyclerView.Adapter<pq_allgroups_rv_adapter.View_Holder> {
    LayoutInflater inflater;
    List<pq_allgroups_list_tbl> pq_allmeetings;
    public pq_allgroups_rv_adapter(Context ctx, List<pq_allgroups_list_tbl> pq_allmeetings){
        this.inflater=LayoutInflater.from(ctx);
        this.pq_allmeetings=pq_allmeetings;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.pq_allgroups_rv_list_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
//        holder.stdgno.setText(pq_allmeetings.get(position).getPq_groupno());
        holder.stdregno.setText(pq_allmeetings.get(position).getPq_stdregno());
        holder.stdname.setText(pq_allmeetings.get(position).getPq_stdname());
        holder.stdcls.setText(pq_allmeetings.get(position).getPq_stdcls());
        holder.stdsup.setText(pq_allmeetings.get(position).getPq_stdsupervisor());
        holder.stdptitle.setText(pq_allmeetings.get(position).getPq_stdprojecttitle());
        holder.stdtech.setText(pq_allmeetings.get(position).getPq_stdtechnology());
        holder.stdremarks.setText("Remarks : "+pq_allmeetings.get(position).getPq_remarks());
    }

    @Override
    public int getItemCount() {
        return pq_allmeetings.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView stdgno,stdregno,stdname,stdcls,stdsup,stdptitle,stdtech,stdremarks;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            stdgno=itemView.findViewById(R.id.pq_prjgroupno);
            stdregno=itemView.findViewById(R.id.pq_stdregno);
            stdname=itemView.findViewById(R.id.pq_stdname);
            stdcls=itemView.findViewById(R.id.pq_stdcls);
            stdsup=itemView.findViewById(R.id.pq_stdsupervisor);
            stdptitle=itemView.findViewById(R.id.pq_projecttitle);
            stdtech=itemView.findViewById(R.id.pq_projectTech);
            stdremarks=itemView.findViewById(R.id.pq_stdmarks);
        }
    }
}
