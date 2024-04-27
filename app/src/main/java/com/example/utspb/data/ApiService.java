package com.example.utspb.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String TOKEN = "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";
    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<Response> callUser(@Query("q") String username);
    @GET("users/{username}")
    Call<Items> callDetailUser(@Path("username") String username);

}
