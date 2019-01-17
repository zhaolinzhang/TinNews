package com.zhaolin_zhang.tinnews.retrofit;

import com.zhaolin_zhang.tinnews.retrofit.response.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRequestApi {
    @GET("top-headlines")
    Observable<BaseResponse> getNewsByCountry(@Query("country") String country);
}
