package com.example.biitprojectwaitingqueuesystem.UploadFiles;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
public interface UploadService {
    @Multipart
    @POST("Queuehandler/FileUpload") //test is name of controller and upload is name of method in API
    Call<ResponseBody> Upload(
            @Part MultipartBody.Part photo
    );
}
