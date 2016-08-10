package app.com.example.android.sampleapp.data;

import app.com.example.android.sampleapp.models.Walmart;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ShowMe on 8/8/16.
 */
public interface WalmartAPI {
    String BASE_URL = "http://api.walmartlabs.com/v1/";

    @GET("search")
    Call<Walmart> getWalmartItem(@Query("query") String query, @Query("apiKey") String api);

    class Factory {

        private static WalmartAPI service;


        public static WalmartAPI getInstance() {

            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(WalmartAPI.class);
                return service;

            } else {
                return service;
            }

        }

    }



}
