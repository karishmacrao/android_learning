package com.example.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String URL = "https://api.github.com/search/";

    @GET("repositories?q=rmkrishna")
    Call<ItemList> getItemObject();


}
