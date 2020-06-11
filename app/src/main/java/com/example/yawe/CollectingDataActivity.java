package com.example.yawe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yawe.adapterFolder.CustomAdapter;
import com.example.yawe.adapterFolder.OnboardingAdpter;
import com.example.yawe.adapterFolder.Onboardlist;
import com.example.yawe.common.SharedPrefManager;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CollectingDataActivity extends AppCompatActivity {
    TextView textView;
    OnboardingAdpter onboardingAdpter;
    private  LinearLayout linearLayoutonboardingindicator;

    private  MaterialButton buttonboardingaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecting_data);
        buttonboardingaction=findViewById(R.id.buttononboardingAction);

        linearLayoutonboardingindicator=findViewById(R.id.layoutonboarding);
        setuponboardingItem();//to set image to be sliding
        setuponboardinglayoutindicator();//dotes that sliding in apk
        setcurrentonboardingindicator(0); //sliding count the current dots


        final ViewPager2 onboardingviewpage = findViewById(R.id.onboardingviewpager2);
        onboardingviewpage.setAdapter(onboardingAdpter);
        onboardingviewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setcurrentonboardingindicator(position);
            }
        });

        buttonboardingaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer num=onboardingviewpage.getCurrentItem()+1;
                    if (num >= onboardingviewpage.getCurrentItem()){
                        onboardingviewpage.setCurrentItem(onboardingviewpage.getCurrentItem()+1);
                        int num2=onboardingviewpage.getCurrentItem();
                        if (num > num2){
                            Intent intent =new Intent(CollectingDataActivity.this,dashboardActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(CollectingDataActivity.this, num+"saphani"+onboardingviewpage.getCurrentItem(), Toast.LENGTH_SHORT).show();


                        }

                    }else{
                        onboardingviewpage.setCurrentItem(onboardingviewpage.getCurrentItem()+1);
                           }

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });




//        this is shared prefarence line to save current user
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
    private  void setuponboardingItem(){
        List<Onboardlist> onboardlists=new ArrayList<>();
        Onboardlist itemlist= new Onboardlist();
        itemlist.setTitle("Welcome To Yawe Milk collection");
        itemlist.setDiscription("Yawe Is an App that help to collect the milk and messure The requerments");
        itemlist.setImage(R.drawable.umucunda);

        Onboardlist requerss= new Onboardlist();
        requerss.setTitle("there We are private company");
        requerss.setDiscription("he help the farmer around the country");
        requerss.setImage(R.drawable.iyin);

        Onboardlist another= new Onboardlist();
        another.setTitle("Welcome To Yawe Milk collection");
        another.setDiscription("Yawe Is an App that help to collect the milk and messure The requerments");
        another.setImage(R.drawable.iyin);

        onboardlists.add(another);
        onboardlists.add(itemlist);
        onboardlists.add(requerss);
        onboardingAdpter = new OnboardingAdpter(onboardlists);

    }

//    sliding indicatores
    private  void  setuponboardinglayoutindicator(){
        ImageView[] indicate =new ImageView[onboardingAdpter.getItemCount()];
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0;i<indicate.length;i++){
            indicate[i]=new ImageView(getApplicationContext());
            indicate[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ovellerindicator));

            indicate[i].setLayoutParams(layoutParams);
            linearLayoutonboardingindicator.addView(indicate[i]);


        }

    }
    @SuppressLint("SetTextI18n")
    private  void  setcurrentonboardingindicator(int index){
        int childcount= linearLayoutonboardingindicator.getChildCount();
        for (int i=0;i<childcount;i++){
            if (i == index) {
                ImageView imageView = (ImageView) linearLayoutonboardingindicator.getChildAt(i);
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.online));
            }else {
                ImageView imageView = (ImageView) linearLayoutonboardingindicator.getChildAt(i);
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.notonline));
            }
        }
        if (index == onboardingAdpter.getItemCount()-1){
            buttonboardingaction.setText("Start");
        }else {
            buttonboardingaction.setText("Next");
        }
    }
}