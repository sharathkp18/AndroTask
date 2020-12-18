package com.example.androtask.module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MultipleResource {

    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List<EmployeeData> data = new ArrayList<>();

    public class EmployeeData {

        @SerializedName("catid")
        public Integer catid;
        @SerializedName("sub_catid")
        public String sub_catid;
        @SerializedName("brand")
        public String brand;
        @SerializedName("usr_id")
        public String usr_id;
        @SerializedName("limit")
        public String limit;
        @SerializedName("ofset")
        public String ofset;
    }
}
