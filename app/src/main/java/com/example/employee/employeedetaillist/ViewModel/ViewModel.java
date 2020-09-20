package com.example.employee.employeedetaillist.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employee.employeedetaillist.Model.Example;

public class ViewModel extends AndroidViewModel {
    private EmployeeRepositary employeeRepositary;

    public ViewModel(@NonNull Application application) {
        super(application);
        employeeRepositary = new EmployeeRepositary(getApplication());
    }

    /**
     * @return
     *  method to get employeerepositary Livedata
     */
     public LiveData<Example> getDetail() {
        return employeeRepositary.getLiveExample();

    }
}
