package com.softdev.smartraysam.carfeeds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.softdev.smartraysam.carfeeds.Core.GetDataContract;
import com.softdev.smartraysam.carfeeds.Core.Presenter;
import com.softdev.smartraysam.carfeeds.adapter.FeedRecycleViewAdapter;
import com.softdev.smartraysam.carfeeds.model.carModel;
import com.softdev.smartraysam.carfeeds.util.ItemDivider;
import com.softdev.smartraysam.carfeeds.util.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {
    private Presenter mPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public FeedRecycleViewAdapter feedRecycleViewAdapter;
    public ProgressDialog progressDialog;
    public static String selectCar = "CAR_POSITION";
    public static String carModels = "CAR_MODEL";
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), "","Loading");
        recyclerView = (RecyclerView)findViewById(R.id.recycleList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ItemDivider(this));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDataFromURL(getApplicationContext(), "","Loading");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onGetDataSuccess(String message, final List<carModel> allCars) {
        progressDialog.cancel();
        feedRecycleViewAdapter = new FeedRecycleViewAdapter(getApplicationContext(), allCars, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                intent.putExtra(selectCar, allCars.get(position).getName());
                intent.putParcelableArrayListExtra(carModels,(ArrayList<carModel>) allCars);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(feedRecycleViewAdapter);

    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status",message);
        Toast.makeText(this, "Error occur while loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(String message) {
        Log.d("Status",message);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
