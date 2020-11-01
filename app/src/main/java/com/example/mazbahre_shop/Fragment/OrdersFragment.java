package com.example.mazbahre_shop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mazbahre_shop.Adapter.CatAdapter;
import com.example.mazbahre_shop.MainActivity;
import com.example.mazbahre_shop.Model.CategoryModel;
import com.example.mazbahre_shop.R;
import com.example.mazbahre_shop.Sessions.SessionManager;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class OrdersFragment<logout> extends Fragment implements View.OnClickListener{

    public OrdersFragment(){
        // Required empty public constructor
    }

    DrawerLayout drawerLayout;
    ImageView navigationBar;
    NavigationView navigationView;
    private View view;
    private RelativeLayout bookmarks,Mazbahreshop;
    private TextView your_orders,favorite_orders,address,helpline,send_feedback,report_safety,rateUs;
    SessionManager sessionManager;
    private TextView login,logout;

    //////// Category Slider Start///////////
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private List<CategoryModel> categoryModelList;
    //////// Category Slider End ////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders,container,false);
        sessionManager = new SessionManager(getContext());

        onSetNavigationDrawerEvents();
        init();
        return view;
    }

    private void init() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Grocery & Staples"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Vegetables & Fruits"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Personal Care"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Household Items"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Home & Kitchen"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Biscuits & Chocolates"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Bevarages"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Breakfast & Diary"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Best Value"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Noodles & Instant Items"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Baby Care"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Pet Care"));
        categoryModelList.add(new CategoryModel(R.drawable.cartIcon,"Fashion"));

        catAdapter = new CatAdapter(categoryModelList,getContext());
        recyclerView.setAdapter(catAdapter);
        catAdapter.notifyDataSetChanged();

    }

    private void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.navigationView);

        navigationBar = (ImageView) view.findViewById(R.id.navigationBar);
        login = (TextView) view.findViewById(R.id.login);
        logout = (TextView) view.findViewById(R.id.logout);
        bookmarks = (RelativeLayout) view.findViewById(R.id.relativeLayout3);
        Mazbahreshop = (RelativeLayout) view.findViewById(R.id.relativeLayout4);


        your_orders = (TextView) view.findViewById(R.id.your_orders);
        favorite_orders = (TextView) view.findViewById(R.id.favorite_orders);
        address = (TextView) view.findViewById(R.id.address);
        helpline = (TextView) view.findViewById(R.id.helpline);
        send_feedback = (TextView) view.findViewById(R.id.send_feedback);
        report_safety = (TextView) view.findViewById(R.id.report_safety);
        rateUs = (TextView) view.findViewById(R.id.rateUs);

        navigationBar.setOnClickListener(this);
        login.setOnClickListener(this);
        logout.setOnClickListener(this);
        bookmarks.setOnClickListener(this);
        Mazbahreshop.setOnClickListener(this);

        your_orders.setOnClickListener(this);
        favorite_orders.setOnClickListener(this);
        address.setOnClickListener(this);
        helpline.setOnClickListener(this);
        send_feedback.setOnClickListener(this);
        report_safety.setOnClickListener(this);
        rateUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.login:
                LogIn();
                break;
            case R.id.logout:
                LogOut();
                break;
            case R.id.relativeLayout3:
                Toast.makeText(getContext(), "Bookmarks", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayout4:
                Toast.makeText(getContext(), "Mazbah'r E-Shop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.your_orders:
                Toast.makeText(getContext(), "Your orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite_orders:
                Toast.makeText(getContext(), "Favorite orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.address:
                Toast.makeText(getContext(), "Address", Toast.LENGTH_SHORT).show();
                break;
            case R.id.helpline:
                Toast.makeText(getContext(), "Helpline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_feedback:
                Toast.makeText(getContext(), "Send feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rateUs:
                Toast.makeText(getContext(), "Rate Us", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void LogOut() {
        sessionManager.editor.clear();
        sessionManager.editor.commit();

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
        Animatoo.animateSwipeRight(getContext());
    }

    private void LogIn() {

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
        Animatoo.animateSwipeRight(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();

        if(sessionManager.isLogIn()){
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
    }
}