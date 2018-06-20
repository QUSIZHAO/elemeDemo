package com.qsz.elemedemo.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qsz.elemedemo.R;
import com.qsz.elemedemo.mode.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QSZ on 2018/6/20 10:24
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.GoodsTagHolder> {

    private List<Goods> data = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public LeftAdapter(Context context, List<Goods> list) {
        mLayoutInflater = LayoutInflater.from(context);
        data.addAll(list);
    }

    @NonNull
    @Override
    public GoodsTagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_goods_tag, parent, false);
        return new GoodsTagHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsTagHolder holder, int position) {
        holder.textView.setText(data.get(position).getcName());
        holder.itemView.setSelected(position == 0);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GoodsTagHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public GoodsTagHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
        }
    }
}
