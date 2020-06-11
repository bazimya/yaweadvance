package com.example.yawe.adapterFolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yawe.R;

import java.util.List;

public class OnboardingAdpter extends RecyclerView.Adapter<OnboardingAdpter.OnboardingViewholder>{
    private  List<Onboardlist> onboardlists;

    public OnboardingAdpter(List<Onboardlist> onboardlists) {
        this.onboardlists = onboardlists;
    }

    @NonNull
    @Override
    public OnboardingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OnboardingViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcontainer,parent,false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewholder holder, int position) {
        holder.setonboardingData(onboardlists.get(position));

    }

    @Override
    public int getItemCount() {
        return onboardlists.size();
    }

    class OnboardingViewholder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private  TextView textDiscription;
        private  ImageView imageonboarding;


       OnboardingViewholder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.textTitle);
            textDiscription=itemView.findViewById(R.id.textdiscription);
            imageonboarding=itemView.findViewById(R.id.imageonbording);

        }
        void  setonboardingData(Onboardlist onboardlist){

           textTitle.setText(onboardlist.getTitle());
           textDiscription.setText(onboardlist.getDiscription());
           imageonboarding.setImageResource(onboardlist.getImage());
        }
    }
}
