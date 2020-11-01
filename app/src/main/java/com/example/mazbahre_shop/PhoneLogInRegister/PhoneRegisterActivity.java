package com.example.mazbahre_shop.PhoneLogInRegister;

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
import com.example.mazbahre_shop.EmailLoginRegister.EmailLoginActivity;
import com.example.mazbahre_shop.EmailLoginRegister.EmailRegisterActivity;
import com.example.mazbahre_shop.HomeActivity;
import com.example.mazbahre_shop.MainActivity;
import com.example.mazbahre_shop.OperationRetrofitAPI.ApiClient;
import com.example.mazbahre_shop.OperationRetrofitAPI.ApiInterface;
import com.example.mazbahre_shop.OperationRetrofitAPI.Users;
import com.example.mazbahre_shop.R;
import com.example.mazbahre_shop.Sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneRegisterActivity extends AppCompatActivity {

    private EditText phone;
    private Button btnReg;
    public static ApiInterface apiInterface;
    SessionManager sessionManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        ////// Status Bar Hide Start ///////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //// Status Bar Hide end/////////////

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);
        
        init();
    }

    private void init() {
        phone = (EditText) findViewById(R.id.phone);
        btnReg = (Button) findViewById(R.id.button2);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        String user_phone = phone.getText().toString().trim();
        if(TextUtils.isEmpty(user_phone)){
            phone.setError("Phone is required!!!");
        }else{
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registering...");
            dialog.setMessage("Please wait while adding your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performPhoneRegistration(user_phone);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body().getResponse().equals("ok")){
                        String user_id = response.body().getResponse();

                        sessionManager.createSession(user_id);

                        Intent intent = new Intent(PhoneRegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Animatoo.animateSwipeLeft(PhoneRegisterActivity.this);

                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("no_account")){
                        Toast.makeText(PhoneRegisterActivity.this,"No Account Found!!!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("already")){
                        Toast.makeText(PhoneRegisterActivity.this,"This phone number is already exists, Please try another...",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(PhoneRegisterActivity.this,"Something went wrong,Please try again!!!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(PhoneRegisterActivity.this, PhoneLoginActivity.class);
        startActivity(intent);
        Animatoo.animateSlideDown(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(PhoneRegisterActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
        finish();
    }
}