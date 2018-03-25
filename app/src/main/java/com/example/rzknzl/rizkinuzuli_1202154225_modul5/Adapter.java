package com.example.rzknzl.rizkinuzuli_1202154225_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RZKNZL on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {

    //deklarasi komponen yang akan digunakan
    private Context mContext;
    int mColor;
    private List<AddData> mList;
    public TextView tvToDo, tvDescription, tvPriority;
    public CardView Cardvw;

    //Constructor
    public Adapter(Context cntx, ArrayList<AddData> list, int color){
        this.mContext=cntx;
        this.mList=list;
        this.mColor=color;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_list, parent, false);
        holder Hold = new holder(view);
        return Hold;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = mList.get(position);
        tvToDo.setText(data.getTodo());
        tvDescription.setText(data.getDesc());
        tvPriority.setText(data.getPrior());
        Cardvw.setCardBackgroundColor(mContext.getResources().getColor(this.mColor));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return mList.size();
    }

    //mendapatkan list data
    public AddData getData(int position){
        return mList.get(position);
    }

    //untuk menghapus list
    public void deleteData(int del){
        //remove item yang terpilih
        mList.remove(del);
        //memberi notifikasi item yang di remove
        notifyItemRemoved(del);
        notifyItemRangeChanged(del, mList.size());
    }

    class holder extends RecyclerView.ViewHolder{
        public holder(View itemView){
            super(itemView);
            //melakukan referrencing pada layout
            tvToDo = itemView.findViewById(R.id.headline);
            tvDescription = itemView.findViewById(R.id.explanation);
            tvPriority = itemView.findViewById(R.id.number);
            Cardvw = itemView.findViewById(R.id.cardlist);
        }
    }
}
