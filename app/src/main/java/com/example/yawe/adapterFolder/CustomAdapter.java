package com.example.yawe.adapterFolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.yawe.R;
import com.example.yawe.mode.EditModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
                public static ArrayList<EditModel> editModelArrayList;
                private LayoutInflater inflater;
                public CustomAdapter(Context ctx, ArrayList<EditModel> editModelArrayList){
                    inflater = LayoutInflater.from(ctx);
                    CustomAdapter.editModelArrayList = editModelArrayList;
                }
                @Override
                public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = inflater.inflate(R.layout.formdata, parent, false);
                MyViewHolder holder = new MyViewHolder(view);
            return holder;}
                @Override
                public void onBindViewHolder(final MyViewHolder holder, final int position) {
                    holder.viewnumbering.setText(editModelArrayList.get(position).getNumberingg());
                    holder.editIgicuba.setText(editModelArrayList.get(position).getEditgIgicuba());
                    holder.editAside.setText(editModelArrayList.get(position).getEditAside());
                    holder.editubushyuhe.setText(editModelArrayList.get(position).getEditUbushyuhe());
                    holder.editamazi.setText(editModelArrayList.get(position).getEditAmazi());
                    holder.clctid.setText(editModelArrayList.get(position).editid);
                    holder.yego.setText(editModelArrayList.get(position).editumwanzuro);
                }
                @Override
                public int getItemCount() {
                    return editModelArrayList.size();
                }
                class MyViewHolder extends RecyclerView.ViewHolder{

                protected EditText editIgicuba;
                protected EditText editAside;
                protected EditText editubushyuhe;
                protected EditText editamazi;
                protected TextView viewnumbering;
                protected TextView  clctid;
                protected RadioButton yego,Oya;
                protected RadioGroup radio0;

                    private  TextWatcher Onimputtextchenge =new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }
                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                editModelArrayList.get(getAdapterPosition()).setEditgIgicuba(editIgicuba.getText().toString());
                                editModelArrayList.get(getAdapterPosition()).setEditAside(editAside.getText().toString());
                                editModelArrayList.get(getAdapterPosition()).setEditUbushyuhe(editubushyuhe.getText().toString());
                                editModelArrayList.get(getAdapterPosition()).setEditAmazi(editamazi.getText().toString());
                                int radioUmwanzuro =radio0.getCheckedRadioButtonId();
                                RadioButton currentBtn = itemView.findViewById(radioUmwanzuro);
                                String solution;
                                String umwanzuro=(currentBtn.getText().toString());
                                if(umwanzuro.equals("Yego")){
                                    solution = "1";
                                    System.out.println(solution);
                                    editModelArrayList.get(getAdapterPosition()).setEditumwanzuro(currentBtn.getHint().toString());
                                }else {
                                    solution = "0";
                                    editModelArrayList.get(getAdapterPosition()).setEditumwanzuro(currentBtn.getHint().toString());

                                }
                            }
                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                    };

                    public MyViewHolder(final View itemView) {
                    super(itemView);
                            editIgicuba = itemView.findViewById(R.id.Igicuba);
                            editAside = itemView.findViewById(R.id.Aside);
                            editubushyuhe = itemView.findViewById(R.id.Ubushyuhe);
                            editamazi = itemView.findViewById(R.id.Amazi);
                            viewnumbering = itemView.findViewById(R.id.numbering);
                            radio0 = itemView.findViewById(R.id.radio0);
                            yego= itemView.findViewById(R.id.yego);
                            Oya= itemView.findViewById(R.id.Oya);
                            clctid = itemView.findViewById(R.id.id);
                            //text wacher
                            editIgicuba.addTextChangedListener(Onimputtextchenge);
                            editAside.addTextChangedListener(Onimputtextchenge);
                            editubushyuhe.addTextChangedListener(Onimputtextchenge);
                            editamazi.addTextChangedListener(Onimputtextchenge);



                    }
        }
    }