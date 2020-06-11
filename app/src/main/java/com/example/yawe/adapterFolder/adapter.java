package com.example.yawe.adapterFolder;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yawe.CollectingDataActivity;
import com.example.yawe.R;
import com.example.yawe.ReceiveMilkActivity;
import com.example.yawe.common.Parameters;

import java.util.ArrayList;

public class adapter  extends RecyclerView.Adapter<adapter.viewholder> {
    private ArrayList<Parameters> modelArrayList = new ArrayList<>();
    private  Button receivebutton;
Context context;

    public adapter(ArrayList<Parameters> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.datalayout, parent, false);




        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {

        final String test = modelArrayList.get(position).collectorName;
        String s=test.substring(0,1);
        String liters=modelArrayList.get(position). quantity+"l";
        final String idn =modelArrayList.get(position). id;
        String conta =modelArrayList.get(position).containers;
        final String ibicuba =modelArrayList.get(position).containers;


        holder.capitaname.setText(s);
        holder.collectorName.setText(modelArrayList.get(position).collectorName);
        holder.quantity.setText(liters);
        holder.amount.setText(modelArrayList.get(position). amount);
        holder.collectionsCount.setText(modelArrayList.get(position). collectionsCount);
        holder.containers.setText(conta);
//        holder.receiption.setText(modelArrayList.get(position).receiption);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //goes to new activity passing the item name
                Intent intent = new Intent(holder.itemView.getContext(), ReceiveMilkActivity.class);
                intent.putExtra("name", test);
                intent.putExtra("id", idn);
                intent.putExtra("ibicuba", ibicuba);
                Bundle b = new Bundle();
                //get text for current item
                String textGet = idn;
                //put text into a bundle and add to intent
                intent.putExtra("text", textGet);

                //get position to carry integer
                intent.putExtra("position", String.valueOf(holder));

                intent.putExtras(b);

                //begin activity
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder{
        TextView collectorName,amount,containers,receiption , quantity,collectionsCount,capitaname;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            collectorName=itemView.findViewById(R.id.collectorName);
            amount=itemView.findViewById(R.id.amount);
            containers=itemView.findViewById(R.id.containers);
//            receiption=itemView.findViewById(R.id.receiption);
            quantity=itemView.findViewById(R.id.quantity);
            collectionsCount=itemView.findViewById(R.id.collectionsCount);
            capitaname=itemView.findViewById(R.id.capitalimage);
        }



    }
}
