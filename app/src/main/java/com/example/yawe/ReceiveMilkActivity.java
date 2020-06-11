package com.example.yawe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yawe.adapterFolder.CustomAdapter;
import com.example.yawe.mode.EditModel;

import java.util.ArrayList;

public class ReceiveMilkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    public ArrayList<EditModel> editModelArrayList;
    Button savebutton;
    protected EditText Igicuba,Aside,Ubushyuhe,Amazi;
    public  ArrayList<String> addarray = new ArrayList<String >();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_milk);
        recyclerView = (RecyclerView) findViewById(R.id.formdata);
        savebutton = (Button)findViewById(R.id.savecollected);

        Igicuba = (EditText) findViewById(R.id.Igicuba);
        Aside = (EditText) findViewById(R.id.Aside);
        Ubushyuhe = (EditText) findViewById(R.id.Ubushyuhe);
        Amazi = (EditText) findViewById(R.id.Amazi);




        editModelArrayList = populateList();
        customAdapter = new CustomAdapter(this,editModelArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

         savebutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(ReceiveMilkActivity.this,NextActivity.class);
             startActivity(intent);

         }
     });

    }





    private ArrayList<EditModel> populateList(){
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null){
            String v =(String) b.get("id");
            String j =(String) b.get("name");
            String ibicuba =(String) b.get("ibicuba");
            int intValue = Integer.parseInt(ibicuba);
            ArrayList<EditModel> list = new ArrayList<>();
            String ibi="";
            for(int i = 1; i <= intValue; i++){
                EditModel editModel = new EditModel();
                editModel.setNumberingg(String.valueOf(i));
                editModel.setEditAside(String.valueOf(i));
                editModel.setEditid(v);
//                editModel.setEditAside(String.valueOf(ibi));
//                editModel.setEditAside(String.valueOf(ibi));
                list.add(editModel);
                }
                return list;
        }
        return null;

    }
}
