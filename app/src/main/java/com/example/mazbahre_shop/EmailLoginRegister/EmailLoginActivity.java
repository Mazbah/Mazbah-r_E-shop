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
import android.widget.ProgressBar;
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

public class EmailLoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnLogIn;
    public static ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        ////// Status Bar Hide Start ///////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //// Status Bar Hide end/////////////

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        init();
    }

    private void init() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogIn = (Button) findViewById(R.id.button2);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
    }

    private void LogIn() {
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_email)){
            email.setError("Email is required!!!");
        }else if(TextUtils.isEmpty(user_password)){
            password.setError("Password is required!!!");
        }else{
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Logging...");
            dialog.setMessage("Please wait until checking your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailLogin(user_email,user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body().getResponse().equals("ok")){
                        String user_id = response.body().getResponse();

                        sessionManager.createSession(user_id);

                        Intent intent = new Intent(EmailLoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Animatoo.animateSwipeLeft(EmailLoginActivity.this);

                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("no_account")){
                        Toast.makeText(EmailLoginActivity.this,"No Account Found!!!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(EmailLoginActivity.this,"Something went wrong,Please try again!!!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void gotToRegister(View view) {
        Intent intent = new Intent(EmailLoginActivity.this,EmailRegisterActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(EmailLoginActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
        finish();
    }
}