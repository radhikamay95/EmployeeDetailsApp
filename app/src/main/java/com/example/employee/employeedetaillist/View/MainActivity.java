package com.example.employee.employeedetaillist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.employee.employeedetaillist.Model.Datum;
import com.example.employee.employeedetaillist.Model.Example;
import com.example.employee.employeedetaillist.R;
import com.example.employee.employeedetaillist.ViewModel.ViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ViewModel viewModel;
    private EmployeeAdapter employeeAdapter;
    private List<Datum> detailsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_emplist);

        //ViewModelProvider initialisation
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ViewModel.class);
        //observes livedata
        viewModel.getDetail().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example example) {
                //recyclerview
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                employeeAdapter = new EmployeeAdapter(example.getData(), MainActivity.this);
                recyclerView.setAdapter(employeeAdapter);
                detailsList = example.getData();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        //search the list based on name
        SearchManager searchManager = (SearchManager) getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (detailsList != null) {
                    employeeAdapter.getFilter().filter(query);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (detailsList != null) {
                    employeeAdapter.getFilter().filter(newText);

                }

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menu = item.getItemId();
        switch (menu) {

            case R.id.ascend:
                if (detailsList != null) {

                    Collections.sort(detailsList, new Comparator<Datum>() {
                        @Override
                        public int compare(Datum datum, Datum t1) {
                            // sort the list in order of A-Z..
                            return datum.getEmployeeName().compareToIgnoreCase(t1.getEmployeeName());

                        }
                    });
                    employeeAdapter.notifyDataSetChanged()
                    ;
                    Toast.makeText(MainActivity.this, "A-Z", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.descend:
                if (detailsList != null) {

                    Collections.sort(detailsList, new Comparator<Datum>() {
                        @Override
                        public int compare(Datum datum, Datum t1) {
                            // sort the list in order of Z-A..
                            return t1.getEmployeeName().compareToIgnoreCase(datum.getEmployeeName());
                        }
                    });
                    employeeAdapter.notifyDataSetChanged()
                    ;
                }
                Toast.makeText(MainActivity.this, "Z-A", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


}
