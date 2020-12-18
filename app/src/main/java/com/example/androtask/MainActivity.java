package com.example.androtask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {
Button googleMapBtn,getApiBtn;
    String latitude,longitude,locationString;
    private static final int PLACE_PICKER_REQUEST_CODE = 1;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleMapBtn=findViewById(R.id.gmapBtn);
        getApiBtn=findViewById(R.id.getApi);
        googleMapButtonClick();
        getApiButtonClick();
    }
    public void googleMapButtonClick(){
        googleMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MainActivity.this),PLACE_PICKER_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PLACE_PICKER_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                latitude=String.valueOf(place.getLatLng().latitude);
                longitude=String.valueOf(place.getLatLng().longitude);
                locationString=latitude+" "+longitude;
                Toast.makeText(this, "Selected Location: "+locationString, Toast.LENGTH_SHORT).show();
             }
        }
    }
    public void getApiButtonClick(){
        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewData.class));
            }
        });
    }
}