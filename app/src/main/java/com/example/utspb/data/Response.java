package com.example.utspb.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response {
    @SerializedName("items")
    private List<Items> items;
    public List<Items> getItems() {return items;}
}


