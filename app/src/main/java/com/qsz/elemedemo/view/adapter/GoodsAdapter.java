package com.qsz.elemedemo.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsz.elemedemo.R;
import com.qsz.elemedemo.mode.Goods;
import com.qsz.elemedemo.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QSZ on 2018/6/20 10:09
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsHolder> {

    Context mContext;
    List<Goods.SubModel> data = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public GoodsAdapter(Context context,List<Goods.SubModel>list){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        data.addAll(list);
    }

    @NonNull
    @Override
    public GoodsAdapter.GoodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_goods,parent,false);
        return new GoodsAdapter.GoodsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsHolder holder, int position) {
        holder.imageView.setImageResource(ImageUtil.getGoodsImageResId(position));
        holder.textView.setText(data.get(position).getName());
        if (position < 3){
            holder.imageTipView.setVisibility(View.VISIBLE);
        }else {
            holder.imageTipView.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GoodsHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageView imageTipView;
        TextView textView;

        public GoodsHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_goods_img);
            imageTipView = itemView.findViewById(R.id.iv_goods_img_tip);
            textView = itemView.findViewById(R.id.iv_goods_name);
        }
    }
}
