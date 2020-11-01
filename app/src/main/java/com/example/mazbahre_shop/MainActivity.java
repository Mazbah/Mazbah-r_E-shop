package com.example.mazbahre_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mazbahre_shop.Adapter.PlateAdapter;
import com.example.mazbahre_shop.EmailLoginRegister.EmailLoginActivity;
import com.example.mazbahre_shop.EmailLoginRegister.EmailRegisterActivity;
import com.example.mazbahre_shop.Model.PlateModel;
import com.example.mazbahre_shop.PhoneLogInRegister.PhoneLoginActivity;
import com.example.mazbahre_shop.Sessions.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PlateModel> plateModelList;
    private PlateAdapter plateAdapter;
    private LinearLayout emailContinue;
    private LinearLayout emailContinueBtn,phoneContinue;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        //////////App Update Checker Start //////////////
     /*   try{
            AppUpdateChecker appUpdateChecker = new AppUpdateChecker(this);
            appUpdateChecker.checkForUpdate(false);
        }catch(Exception e){
            e.printStackTrace();
        }   */
        //////////App Update Checker End /////////////////

        ////// Status Bar Hide Start ///////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //// Status Bar Hide end/////////////

        emailContinue = (LinearLayout) findViewById(R.id.linear2);
        phoneContinue = (LinearLayout) findViewById(R.id.linear1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setKeepScreenOn(true);
        recyclerView.setHasFixedSize(true);

        plateModelList = new ArrayList<>();
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));
        plateModelList.add(new PlateModel(R.drawable.one));

        plateAdapter = new PlateAdapter(plateModelList,this);
        recyclerView.setAdapter(plateAdapter);
        plateAdapter.notifyDataSetChanged();

        /////////// Call AutoScroll Start ///////////////
        autoScroll();
        /////////// Call AutoScroll End ////////////////

        ////////// Continue with Mail /////////////////
        emailContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmailLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });

        ////////// Continue with Phone /////////////////
        phoneContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });
    }

    public void autoScroll(){
        final int scrollSpeed = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count== plateAdapter.getItemCount()){
                    count = 0;
                }
                if(count< plateAdapter.getItemCount()) {
                    recyclerView.smoothScrollToPosition(++count);
                    handler.postDelayed(this,scrollSpeed);
                }
            }
        };
        handler.postDelayed(runnable,scrollSpeed);
    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
        Animatoo.animateSwipeLeft(MainActivity.this);
    }

    protected void onStart(){
        super.onStart();

        if(sessionManager.isLogIn()){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            Animatoo.animateSlideLeft(this);
        }else{

        }
    }
}