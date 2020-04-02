package com.example.foodvilage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {

    private Context context;
    private List<Popular> productList;

    public PopularAdapter(Context context, List<Popular> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Popular popular = productList.get(position);
        holder.productName.setText(popular.getProduct_title());
        holder.productPrice.setText(popular.getProduct_price());
        Picasso.get().load(popular.getProduct_images())
                .placeholder(R.drawable.banner1).fit()
                .centerCrop().into((Target) holder.productImg);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productName, productPrice, productImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_nameId);
            productPrice = itemView.findViewById(R.id.product_priceId);
            productImg = itemView.findViewById(R.id.product_imgId);
        }
    }
}

