package com.example.biitprojectwaitingqueuesystem.Services;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadService {
    @Multipart
    @POST("ProjectCommittiee/uploadFile")
    Call<ResponseBody> Upload(
            @Part MultipartBody.Part file,
            @Part("description")RequestBody description
            );
}
