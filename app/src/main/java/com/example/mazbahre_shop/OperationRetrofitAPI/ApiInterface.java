package com.example.mazbahre_shop.OperationRetrofitAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /////////////Email Registration ////////////////////
    @GET("email_registration.php")
    Call<Users> performEmailRegistration(
            @Query("user_name") String user_name,
            @Query("user_email") String user_email,
            @Query("user_password") String user_password

    );

    /////////////// Email LogIn ///////////////////////
    @GET("email_login.php")
    Call<Users> performEmailLogin(
            @Query("user_email") String user_email,
            @Query("user_password") String user_password

    );

    /////////////Phone Registration ////////////////////
    @GET("phone_registration.php")
    Call<Users> performPhoneRegistration(
            @Query("user_phone") String user_phone
    );

    /////////////// Phone LogIn ///////////////////////
    @GET("phone_login.php")
    Call<Users> performPhoneLogin(
            @Query("user_phone") String user_phone
    );
}
