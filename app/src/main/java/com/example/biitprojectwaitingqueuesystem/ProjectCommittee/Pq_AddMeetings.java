package com.example.biitprojectwaitingqueuesystem.ProjectCommittee;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.biitprojectwaitingqueuesystem.R;
import com.example.biitprojectwaitingqueuesystem.UploadFiles.APIClient;
import com.example.biitprojectwaitingqueuesystem.UploadFiles.UploadService;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pq_AddMeetings extends AppCompatActivity {
    Button imexcel;
    private Uri filepath;
    File dir,imageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_add_meetings);
        imexcel=findViewById(R.id.btn_importexcel);

        imexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadExcel(1);
            }
        });
    }
    //-----------
    //upload pdf
    private void uploadExcel(int requestCode){
        // String url= "https://docs.google.com/gview?embedded=true&url=https://url.pdf";
        Intent uploadPdf = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        uploadPdf.addCategory(Intent.CATEGORY_OPENABLE);

        //uploadPdf.setDataAndType(Uri.parse(url), "application/pdf");
        // File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        //Uri path = Uri.fromFile(file);
        uploadPdf.setType("application/vnd.ms-excel");
        //uploadPdf.setAction(uploadPdf.ACTION_GET_CONTENT);
        //uploadPdf = new Intent(Intent.ACTION_VIEW);
        //uploadPdf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //uploadPdf.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        //ploadPdf.setDataAndType(path, "application/pdf");
        startActivityForResult(Intent.createChooser(uploadPdf,"PDF FILE SELECT"),requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null && data.getData() != null){
            String path = data.getData().getPath();
            Uri uri = data.getData();
            filepath = uri;
//            String filepth=filepath.getPath().toString();

            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            imageFile = new File(dir, filepath.getPath().toString());

            try {
                if(!dir.isDirectory()) {
                    dir.mkdirs();
                }
                imageFile.createNewFile();

            } catch(Exception e) {
                e.printStackTrace();
            }


            Toast.makeText(getApplicationContext(), imageFile+"", Toast.LENGTH_SHORT).show();
            saveFile();
        }
    }
    public  void saveFile(){
        File file = new File(filepath.getPath());
        RequestBody photoContent = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", imageFile.getName(), photoContent);

//                RequestBody PId= RequestBody.create(MediaType.parse("text/plain"),json.toString());
//                RequestBody UId= RequestBody.create(MediaType.parse("text/plain"),loginId.toString());

        UploadService uploadService = APIClient.getClient().create(UploadService.class);
//        Toast.makeText(getApplicationContext(), file+"", Toast.LENGTH_SHORT).show();
        uploadService.Upload(photo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    Toast.makeText(Pq_AddMeetings.this, "Added Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(Pq_AddMeetings.this, " onFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}