package com.example.androtask;

import com.example.androtask.module.MultipleResource;

import retrofit2.Call;
import retrofit2.http.GET;


interface APIInterface {

    @GET("/api/get_product.php")
    Call<MultipleResource> getUserList();

}
