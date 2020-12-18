package com.example.androtask;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androtask.module.MultipleResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewData extends AppCompatActivity {
    LinearLayout usersList;
     boolean didGetEmployee;
    APIInterface apiInterface;
    List<MultipleResource.EmployeeData> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        try {
            apiInterface = APIClient.getClient().create(APIInterface.class);
            usersList = findViewById(R.id.listLayout);
            Cursor response = null;
            if (response.moveToFirst()) {
                while (!response.isAfterLast()){
                    onAddField(getEmployees());
                response.moveToNext();}
            }else{
                Toast.makeText(ViewData.this, "No Data", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
}
    public void onAddField(List<MultipleResource.EmployeeData> dataList) {
        try {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.user_tag, null);
            TextView userName = rowView.findViewById(R.id.userNameTv);
            TextView userInfo = rowView.findViewById(R.id.userInfoTv);
            ImageButton goArrow = rowView.findViewById(R.id.goArrow);
                 userName.setText(this.dataList.toString());
                userInfo.setText(this.dataList.hashCode());
                usersList.addView(rowView, usersList.getChildCount());
                goArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ViewData.this, "Function not implemented", Toast.LENGTH_SHORT).show();
                     }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List getEmployees(){
        try{
            Call<MultipleResource> call = apiInterface.getUserList();
            call.enqueue(new Callback<MultipleResource>() {
                @Override
                public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                    MultipleResource resource = response.body();
                    List<MultipleResource.EmployeeData> dataList = resource.data;
                    didGetEmployee=true;
                    onAddField(dataList);
                    Toast.makeText(ViewData.this, "got it", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<MultipleResource> call, Throwable t) {
                    call.cancel();
                    didGetEmployee=false;
                    Toast.makeText(ViewData.this, "not get", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
}