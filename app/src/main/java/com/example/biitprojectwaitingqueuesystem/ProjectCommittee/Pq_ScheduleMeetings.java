package com.example.biitprojectwaitingqueuesystem.ProjectCommittee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.Services.APIClient;
import com.example.biitprojectwaitingqueuesystem.Services.UploadService;
import com.example.biitprojectwaitingqueuesystem.Supervisor.supervisor_static_data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pq_ScheduleMeetings extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Button btnupload,btnbrowse,btndeletealldata,importdata,btnupdated;
//    EditText description;
    TextView filename;
    TextView date1,date2;
    Calendar calendar;
    private String filePath;

    /////----T  data
    TextView gdate,supdate;
    EditText group1et,group2et;
    Spinner spsup1,spsup2;
    Button updatespdates,updategroupdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_schedule_meetings);
        verifyStoragePermissions(Pq_ScheduleMeetings.this);

        gdate=findViewById(R.id.selectgdate);
        supdate=findViewById(R.id.selectspdate);
        group1et=findViewById(R.id.group1_et);
        group2et=findViewById(R.id.group2_et);
        spsup1=findViewById(R.id.sp1_sp);
        spsup2=findViewById(R.id.sp2_sp);
        updategroupdates=findViewById(R.id.updateg);
        updatespdates=findViewById(R.id.updatesp);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //------------------
        DatePickerDialog.OnDateSetListener datesup = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private  void updateCalender(){
                String format="dd/MM/yyyy";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                supdate.setText(sdf.format(calendar.getTime()));
            }
        };
        supdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Pq_ScheduleMeetings.this,datesup,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        DatePickerDialog.OnDateSetListener dategroup = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private  void updateCalender(){
                String format="dd/MM/yyyy";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                gdate.setText(sdf.format(calendar.getTime()));
            }
        };
        gdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Pq_ScheduleMeetings.this,dategroup,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ////===========


        btnupdated=findViewById(R.id.btn_updateDates);
        date1=findViewById(R.id.date1_txt);
        date2=findViewById(R.id.date2_txt);
        btnbrowse=findViewById(R.id.buttonbrowse);
        btndeletealldata=findViewById(R.id.deletealldata_btn);
        btnupload=findViewById(R.id.buttonupload);
        importdata=findViewById(R.id.importnewdata_btn);

//        description=findViewById(R.id.description);
        filename=findViewById(R.id.et_filename);
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnbrowse_onClick(v);
            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnupload_onClick(v);
            }
        });
        //-----------------------Date Picks
        //--------------Date Picker
        calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private  void updateCalender(){
                String format="dd/MM/yyyy";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                date1.setText(sdf.format(calendar.getTime()));
            }
        };
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Pq_ScheduleMeetings.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        DatePickerDialog.OnDateSetListener datex = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private  void updateCalender(){
                String format="dd/MM/yyyy";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                date2.setText(sdf.format(calendar.getTime()));
            }
        };
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Pq_ScheduleMeetings.this,datex,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------btn Update dates
        btnupdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/updateDate?fdate="+date1.getText().toString()+"&sdate="+date2.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Dates Updated", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });

        /////////////////---------------------------------------------
        //----------btn Update dates
        updategroupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/fromto?group1="+group1et.getText().toString()+"&group2="+group2et.getText().toString()+"&date="+gdate.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Dates Updated", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        //----------btn Update dates
        updatespdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/fromtowithS?sup1="+spsup1.getSelectedItem().toString()+"&sup2="+spsup2.getSelectedItem().toString()+"&date="+supdate.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Dates Updated", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        /////---------------------------------------------------------






        ///------------------------Delete btn
        btndeletealldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/deleteAll";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        ///---------------------
        importdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=getString(R.string.ip)+"/BIITWaitingQueueSystem/api/ProjectCommittiee/readExcelSheet";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Successfully Imported", Toast.LENGTH_SHORT).show();
//                                finish();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
        ///---------------------
    }
    private void btnbrowse_onClick(View v) {
        Intent intent=new Intent();
//        intent.setType("application/vnd.ms-excel");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
//        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        Intent result=Intent.createChooser(intent,getText(R.string.choosefile));
        startActivityForResult(result,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10){
            if (resultCode==RESULT_OK){
                Uri uri=data.getData();
                filePath=getRealPathFromURI(uri);
                filename.setText(filePath.toString());
            }
        }
    }

    public void btnupload_onClick(View v) {
        try {
            File file=new File(filePath);
            RequestBody fileContent=RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part file1=MultipartBody.Part.createFormData("file",file.getName(),fileContent);
            RequestBody desciption=RequestBody.create(MediaType.parse("text/plain"),"ExcelSheet");
            UploadService uploadService= APIClient.getClient().create(UploadService.class);
            uploadService.Upload(file1,desciption).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(getApplicationContext(), "Successfully Upload", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }

    private  String getRealPathFromURI(Uri contentUri){
        String[] proj={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_index);
        cursor.close();
        return  result;
    }
    private static void verifyStoragePermissions(Activity activity){
        int permission= ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}