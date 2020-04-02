package com.example.foodvilage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    ViewFlipper imgBanner;
    private RecyclerView recyclerView;
    private PopularAdapter adapter;
    private DatabaseReference databaseReference;
    private List<Popular> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        imgBanner = findViewById(R.id.imgBanner);

        int sliders[]={
                R.drawable.banner1,R.drawable.banner2,R.drawable.banner3
        };

        for (int slide:sliders){
            bannerFliper(slide);
        }
        showPopularProduct();
    }

    public void bannerFliper(int images){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(images);
        imgBanner.addView(imageView);
        imgBanner.setFlipInterval(5000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this, android.R.anim.fade_in);
        imgBanner.setOutAnimation(this, android.R.anim.fade_out);
    }

    private void showPopularProduct() {
        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        productList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("productList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Popular popular = dataSnapshot1.getValue(Popular.class);
                    productList.add(popular);
                }
                adapter = new PopularAdapter(getApplicationContext(),productList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
