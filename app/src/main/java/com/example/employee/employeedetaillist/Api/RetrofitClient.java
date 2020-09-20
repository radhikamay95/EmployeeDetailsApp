package com.example.employee.employeedetaillist.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private RetrofitClient() {
    }

    private static final String BASE_URL = "https://run.mocky.io/v3/";
    private static Retrofit retrofit = null;

    /**
     * @return
     * Retrofit client initialization
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
