package com.example.yawe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yawe.ApiUrl.Apiulr;
import com.example.yawe.adapterFolder.CustomAdapter;
import com.example.yawe.common.Parameters;
import com.example.yawe.common.SharedPrefManager;
import com.example.yawe.mode.User;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NextActivity extends AppCompatActivity {
    Map<String, String> arrayList = new HashMap<>();
    public String ibicuba;
    public String amazi;
    public String aside;
    public String ubushyuhe;
    public String editumwanzuro;
    public String  userid;
    public String id;
    public  JSONArray container;
    public String receiveCollection="receiveCollection";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        toolbar = findViewById(R.id.anim_toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar);
        imageView=findViewById(R.id.imageclick);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(NextActivity.this,dashboardActivity.class);
                startActivity(intent);
            }
        });




        User user = SharedPrefManager.getInstance(this).getUser();
        //        data to be transferd to the database
        container= new JSONArray();
        userid = String.valueOf(user.getId());
        int siz=CustomAdapter.editModelArrayList.size();
        for (int i = 0; i < CustomAdapter.editModelArrayList.size(); i++) {
            ibicuba = CustomAdapter.editModelArrayList.get(i).getEditgIgicuba();
            amazi = CustomAdapter.editModelArrayList.get(i).getEditAmazi();
            aside = CustomAdapter.editModelArrayList.get(i).getEditAside();
            ubushyuhe = CustomAdapter.editModelArrayList.get(i).getEditUbushyuhe();
            editumwanzuro = CustomAdapter.editModelArrayList.get(i).getEditumwanzuro();
            id = CustomAdapter.editModelArrayList.get(i).getEditid();
            JSONObject objectList = new JSONObject();
            try {
                objectList.put("size",ibicuba );
                objectList.put("water",amazi );
                objectList.put("acid", aside);
                objectList.put("temperature", ubushyuhe);
                objectList.put("decision",editumwanzuro);
                container.put(objectList);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }




     loader loader =new loader();
        loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,receiveCollection,id,userid,String.valueOf(container));


    }



    class loader extends AsyncTask<String, String, String> {
    HttpURLConnection conn;
    URL url = null;

    @Override
    protected void onPreExecute() {
//nextstape nzakoraho
        super.onPreExecute();
    }

    @SuppressLint("WrongThread")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected String doInBackground(String... params) {

        String address =Apiulr.Approve;

        try {

            url = new URL(address);
        } catch (MalformedURLException e) {
//nextstape nzakoraho
            e.printStackTrace();
            return "exception";
        }
        try {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");

            // setDoInput and setDoOutput method depict handling of both send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);
            JSONArray values = new JSONArray();


            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("action", params[0])
                    .appendQueryParameter("collectionId", params[1])
                    .appendQueryParameter("receiverId",params[2])
                    .appendQueryParameter("containers",params[3]);
            String query = builder.build().getEncodedQuery();

            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e1) {
            //nextstape nzakoraho
            e1.printStackTrace();
            return "exception";
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if byarangiye connection made
            if (response_code == HttpURLConnection.HTTP_OK) {


                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return (result.toString());

            } else {

                return ("unsuccessful"+response_code);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            conn.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        ImageView imageView = findViewById(R.id.imageclick);
        imageView.setImageResource(R.drawable.iyin);

        try {
            //converting response to json object
            JSONObject obj = new JSONObject(result);

            //if no error in response
            if (obj.getBoolean("status")) {
                TextView textView=(TextView) findViewById(R.id.surfaceView);
                textView.setText(obj.getString("msg"));
                textView.setMovementMethod(new ScrollingMovementMethod());



            } else {

                TextView textView=(TextView) findViewById(R.id.surfaceView);
                textView.setText(obj.getString("msg"));
                textView.setMovementMethod(new ScrollingMovementMethod());
            }
        } catch (JSONException e) {
            e.printStackTrace();


        }

        }}}


