package com.example.employee.employeedetaillist.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employee.employeedetaillist.Api.ApiInterface;
import com.example.employee.employeedetaillist.Api.RetrofitClient;
import com.example.employee.employeedetaillist.Model.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepositary {
    private Context context;

    public EmployeeRepositary(Application application) {
        this.context = application;
    }

    public LiveData<Example> getLiveExample() {

        final MutableLiveData<Example> data = new MutableLiveData<>();

        //Api client interface initialisation
        ApiInterface apiService = RetrofitClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiService.getExample();


        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                data.postValue(response.body());
                Log.e("tag-----------", "Success");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("tag --------", t.toString());
            }
        });

        return data;
    }
}
