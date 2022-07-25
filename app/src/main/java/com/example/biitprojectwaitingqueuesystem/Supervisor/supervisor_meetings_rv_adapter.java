package com.example.biitprojectwaitingqueuesystem.Supervisor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_fyp2_meeting_schedules_list_tbl;
import com.example.biitprojectwaitingqueuesystem.QueueHandler.qh_fyp2_meeting_schedules_rv_adapter;
import com.example.biitprojectwaitingqueuesystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class supervisor_meetings_rv_adapter extends RecyclerView.Adapter<supervisor_meetings_rv_adapter.View_Holder> {
    Context mContext;
    LayoutInflater inflater;
    List<supervisor_meetings_list_tbl> sp_meetings;
    public supervisor_meetings_rv_adapter(Context ctx, List<supervisor_meetings_list_tbl> sp_meetings){
        this.inflater=LayoutInflater.from(ctx);
        this.sp_meetings=sp_meetings;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.supervisor_meetings_schedules_list_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.ptitle.setText(sp_meetings.get(position).getSp_stdprojecttitle());
        holder.stdname.setText(sp_meetings.get(position).getSp_stdname());
        holder.stdarid.setText(sp_meetings.get(position).getSp_stdregno());
        holder.stdcls.setText(sp_meetings.get(position).getSp_stdcls());
        holder.stdtech.setText(sp_meetings.get(position).getSp_stdtechnology());
        holder.stdmtime.setText(sp_meetings.get(position).getSp_stdtime());
        holder.stdmdate.setText(sp_meetings.get(position).getSp_stddate());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return sp_meetings.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView ptitle,stdname,stdarid,stdcls,stdtech,stdmtime,stdmdate;
//        Spinner remarks;
        EditText remarks;
        Button btnmark;
        String x;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            ptitle=itemView.findViewById(R.id.ptitle_sp_end);
            stdname=itemView.findViewById(R.id.std_name_sp_end);
            stdarid=itemView.findViewById(R.id.stdregno_sp_end);
            stdcls=itemView.findViewById(R.id.stdcls_sp_end);
            stdtech=itemView.findViewById(R.id.tech_sp_end);
            stdmtime=itemView.findViewById(R.id.mtngtime_sp_end);
            stdmdate=itemView.findViewById(R.id.mtngdate_sp_end);
            remarks=itemView.findViewById(R.id.sp_remarks);
            btnmark=itemView.findViewById(R.id.btn_mark);

            //        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();

//            remarks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    x=parent.getSelectedItem().toString();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
            btnmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x=remarks.getText().toString();
//                    Toast.makeText(v.getContext(), x+"", Toast.LENGTH_SHORT).show();
                    String regno=sp_meetings.get(position).getSp_stdregno();
//                    Toast.makeText(v.getContext(), regno+"", Toast.LENGTH_SHORT).show();
                    JSONObject obj=new JSONObject();
                    try {
                        obj.put("remarks",x.toString());
                        String url= supervisor_static_data.ipaddress+"/BIITWaitingQueueSystem/api/Supervisor/remarkStd?stdarid="+regno+"&remarks="+x;
                        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(v.getContext(), "Remarked", Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(jsonArrayRequest);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
