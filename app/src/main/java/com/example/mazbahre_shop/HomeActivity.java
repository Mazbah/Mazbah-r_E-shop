package com.example.mazbahre_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mazbahre_shop.Adapter.CatAdapter;
import com.example.mazbahre_shop.Fragment.GoOutFragment;
import com.example.mazbahre_shop.Fragment.GoldFragment;
import com.example.mazbahre_shop.Fragment.OrdersFragment;
import com.example.mazbahre_shop.Fragment.VideosFragment;
import com.example.mazbahre_shop.Model.CategoryModel;
import com.example.mazbahre_shop.Sessions.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    //////// Category Slider Start///////////
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private List<CategoryModel> categoryModelList;
    //////// Category Slider End ////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ///////// Changing the color of status text color ////////////////////
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        /////////////////////////////////////////////////////////////////

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId())
                    {
                        case R.id.orders:
                            selectedFragment = new OrdersFragment();
                            break;
                        case R.id.goout:
                            selectedFragment = new GoOutFragment();
                            break;
                        case R.id.gold:
                            selectedFragment = new GoldFragment();
                            break;
                        case R.id.videos:
                            selectedFragment = new VideosFragment();
                            break;
                    }
                    //////////////////// Replacing by default fragment on home activity //////////////
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();

                    return false;
                }
            };
}