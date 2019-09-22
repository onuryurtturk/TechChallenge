package com.onuryurtturk.marketim.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onuryurtturk.marketim.model.Orders;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Api {

    private static final String BASE_URL = "http://kariyertechchallenge.mockable.io";
    private static ApiInterface api;

    public static ApiInterface getApi() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Orders.class,
                            new OrderDeserializer())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET("/")
        Call<Orders> getOrders();

    }
}
