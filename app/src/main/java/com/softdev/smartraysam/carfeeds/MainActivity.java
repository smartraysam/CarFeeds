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

import com.softdev.smartraysam.carfeeds.adapter.FeedRecycleViewAdapter;
import com.softdev.smartraysam.carfeeds.model.carModel;
import com.softdev.smartraysam.carfeeds.util.ItemDivider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String CAR_FEED_URL = "https://s3-us-west-2.amazonaws.com/wunderbucket/locations.json";
    public RecyclerView feedRecyclerView;
    String TAG = MainActivity.class.getSimpleName();
    ArrayList<carModel> feedList;
    public FeedRecycleViewAdapter feedRecycleViewAdapter;
    carModel cModel;
    public ProgressDialog progressDialog;
    public static String selectCar = "CAR_POSITION";
    public static String carModels = "CAR_MODEL";
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feedRecyclerView = findViewById(R.id.recycleList);
        feedRecyclerView.hasFixedSize();
        feedRecyclerView.addItemDecoration(new ItemDivider(this));
        progressDialog = new ProgressDialog(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new GetCarFeeds().execute(CAR_FEED_URL);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetCarFeeds().execute(CAR_FEED_URL);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public class GetCarFeeds extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String jsoncarFeed = "";
            try {
                URL url = new URL(arg0[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }
                jsoncarFeed = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("JSONDATA", "Response from url: " + jsoncarFeed);
            feedList = new ArrayList<carModel>();
            if (!jsoncarFeed.equals("")) {
                try {
                    JSONObject jsonObj = new JSONObject(jsoncarFeed);
                    // Getting JSON Array node
                    JSONArray placemarks = jsonObj.getJSONArray("placemarks");
                    // looping through All placemarks
                    for (int i = 0; i < placemarks.length(); i++) {
                        JSONObject placemarkObj = placemarks.getJSONObject(i);
                        String address = placemarkObj.getString("address");
                        String name = placemarkObj.getString("name");
                        String engineType = placemarkObj.getString("engineType");
                        String exterior = placemarkObj.getString("exterior");
                        String interior = placemarkObj.getString("interior");
                        String vin = placemarkObj.getString("vin");
                        int fuel = placemarkObj.getInt("fuel");
                        // coordinate node is JSON Object
                        JSONArray coordinate = placemarkObj.getJSONArray("coordinates");
                        Double coordinateY = coordinate.getDouble(0);
                        Double coordinateX = coordinate.getDouble(1);
                        cModel = new carModel(Long.valueOf(i),name,address,engineType,vin,exterior,interior,fuel,coordinateX,coordinateY);
                        feedList.add(cModel);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                progressDialog.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.cancel();
            feedRecycleViewAdapter = new FeedRecycleViewAdapter(getApplicationContext(), feedList, new FeedRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.d(TAG, "clicked position:" + position);
                    Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                    intent.putExtra(selectCar, feedList.get(position).getID());
                    intent.putParcelableArrayListExtra(carModels, feedList);
                    startActivity(intent);

                }
            });
            feedRecyclerView.setAdapter(feedRecycleViewAdapter);
            feedRecycleViewAdapter.notifyDataSetChanged();
        }
    }

}
