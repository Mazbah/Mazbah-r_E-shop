package com.example.mazbahre_shop.EmailLoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mazbahre_shop.HomeActivity;
import com.example.mazbahre_shop.MainActivity;
import com.example.mazbahre_shop.OperationRetrofitAPI.ApiClient;
import com.example.mazbahre_shop.OperationRetrofitAPI.ApiInterface;
import com.example.mazbahre_shop.OperationRetrofitAPI.Users;
import com.example.mazbahre_shop.PhoneLogInRegister.PhoneRegisterActivity;
import com.example.mazbahre_shop.R;
import com.example.mazbahre_shop.Sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailRegisterActivity extends AppCompatActivity {

    private EditText name,email,password;
    private Button regBtn;
    public static ApiInterface apiInterface;
    SessionManager sessionManager;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);

        ////// Status Bar Hide Start ///////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //// Status Bar Hide end/////////////

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        init();
    }

    private void init() {
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        regBtn = (Button) findViewById(R.id.button2);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });
    }

    private void Registration() {
        String user_name = name.getText().toString().trim();
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_name)){
            name.setError("Name is required!!!");
        }else if(TextUtils.isEmpty(user_email)){
            email.setError("Email is required!!!");
        }else if(TextUtils.isEmpty(user_password)){
            password.setError("Password is required!!!");
        }else{
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registering...");
            dialog.setMessage("Please wait while adding your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailRegistration(user_name,user_email,user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body().getResponse().equals("ok")){
                        user_id = response.body().getUser_id();

                        sessionManager.createSession(user_id);

                        Intent intent = new Intent(EmailRegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Animatoo.animateSwipeLeft(EmailRegisterActivity.this);

                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("failed")){
                        Toast.makeText(EmailRegisterActivity.this,"Something went wrong..Please try again!!!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("already")){
                        Toast.makeText(EmailRegisterActivity.this,"This email id is already exists, Please try another...",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(EmailRegisterActivity.this,EmailLoginActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(EmailRegisterActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
        finish();
    }
}