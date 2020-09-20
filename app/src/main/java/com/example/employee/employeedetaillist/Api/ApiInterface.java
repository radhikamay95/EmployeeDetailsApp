package com.example.employee.employeedetaillist.Api;

import com.example.employee.employeedetaillist.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("325512ea-9fd0-4aeb-9cf5-cfda5c96f7a0")
    Call<Example> getExample();
}
