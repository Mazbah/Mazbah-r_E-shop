package com.example.mazbahre_shop.OperationRetrofitAPI;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("response")
    private String response;

    @SerializedName("user_id")
    private String User_id;

    public String getResponse() {
        return response;
    }

    public String getUser_id() {
        return User_id;
    }
}
