package com.example.employee.employeedetaillist.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.employee.employeedetaillist.Model.Datum;
import com.example.employee.employeedetaillist.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmplopeeViewHolder> implements Filterable {
    private List<Datum> empDetails;
    private List<Datum> empFilteredDetails;
    private final Context context;

    /**
     * @param empDetails
     * @param context
     */
    public EmployeeAdapter(List<Datum> empDetails, Context context) {
        this.empDetails = empDetails;
        this.context = context;
        this.empFilteredDetails = empDetails;
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmplopeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_items, parent, false);
        RecyclerView.ViewHolder viewHolder = new EmplopeeViewHolder(view);
        return (EmplopeeViewHolder) viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmplopeeViewHolder holder, int position) {
        holder.id.setText(empFilteredDetails.get(position).getId());
        holder.name.setText(empFilteredDetails.get(position).getEmployeeName());
        holder.age.setText(empFilteredDetails.get(position).getEmployeeAge());
        holder.salary.setText(empFilteredDetails.get(position).getEmployeeSalary());
        //glide lib to bind the image into view
        Glide.with(context).load(empFilteredDetails.get(position).getProfileImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_person_black_24dp))
                .into(holder.profileImage);
    }

    @NonNull
    @Override
    public int getItemCount() {
        return empFilteredDetails.size();
    }

    /**
     * @return
     * method to filter list
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    empFilteredDetails = empDetails;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : empDetails) {
                        // filter the list based on name match
                        if (row.getEmployeeName() != null) {
                            if (row.getEmployeeName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                    }
                    empFilteredDetails = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = empFilteredDetails;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }


    public class EmplopeeViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView name;
        private TextView age;
        private TextView salary;
        private ImageView profileImage;


        public EmplopeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            id = itemView.findViewById(R.id.employee_id);
            age = itemView.findViewById(R.id.employee_age);
            salary = itemView.findViewById(R.id.employee_salary);
            profileImage = itemView.findViewById(R.id.employee_image);

        }
    }
}
