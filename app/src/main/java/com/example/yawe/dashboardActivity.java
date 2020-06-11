package com.example.yawe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yawe.adapterFolder.adapter;
import com.example.yawe.common.Parameters;
import com.example.yawe.ApiUrl.Apiulr;
import com.example.yawe.common.SharedPrefManager;
import com.google.android.material.appbar.AppBarLayout;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class dashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    RecyclerView recyclerView;
    private Menu collapseMenu;
    RequestQueue requestQueue;
    ArrayList<Parameters> modelArrayList = new ArrayList<>();





    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.anim_toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar);


        recyclerView = findViewById(R.id.recycleviewlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }



        extractmethode();
        adapter mAdapter = new adapter(modelArrayList);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("YAWE");


        Context context;


    }

    private void extractmethode() {


        requestQueue =Volley.newRequestQueue(this);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,Apiulr.Url , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response.optBoolean("status")) {
                    JSONArray userPermissions = response.optJSONArray("data");
                    for (int i = 0; i < userPermissions.length(); i++) {
                        Parameters permission = new Parameters();
                        JSONObject itemPermission = userPermissions.optJSONObject(i);
                        permission. id= itemPermission.optString("id");
                        permission. collectorName= itemPermission.optString("collectorName");
                        permission.amount = itemPermission.optString("amount");
                        permission.containers = itemPermission.optString("containers");
                        permission.receiption = itemPermission.optString("receiption");
                        permission.quantity = itemPermission.optString("quantity");
                        permission.collectionsCount = itemPermission.optString("collectionsCount");
                        modelArrayList.add(permission);

                    }
                    adapter mAdapter = new adapter(modelArrayList);
                    recyclerView.setAdapter(mAdapter);


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getLocalizedMessage());


            }
        }

        );
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.help:
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
                startActivity(new Intent(dashboardActivity.this,LoginActivity.class));
                break;
            case R.id.collected:
                Toast.makeText(this, "comming Soon", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
//        return super.onOptionsItemSelected(item);
    }
}
