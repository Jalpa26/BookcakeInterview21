package com.example.interview;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.interview.adapter.ImagesAdapter;
import com.example.interview.dto.Hit;
import com.example.interview.dto.ImageDTO;
import com.example.interview.network.ApiManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageListActivity extends AppCompatActivity {
    private RecyclerView rv_images;
    private ImagesAdapter imagesAdapter;
    private EditText edSearch;
    private ArrayList<Hit> hitArrayList=new ArrayList<>();
    private ArrayList<Hit> filteredHitList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        getViewByID();
        rv_images.setLayoutManager(new LinearLayoutManager(this));
        rv_images.setHasFixedSize(true);
        getData();
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             if(hitArrayList.size()>0){
                 filteredHitList.clear();
                for(int j=0;j<hitArrayList.size();j++){
                    if(hitArrayList.get(j).getUser().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredHitList.add(hitArrayList.get(j));
                    }
                }
                imagesAdapter.notifyDataSetChanged();
             }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    void getViewByID(){
        rv_images = findViewById(R.id.rv_image);
        edSearch = findViewById(R.id.ed_search);
    }
    private void getData() {
        ApiManager.getInstance().getApiService().getData("23020221-e6fac444311373308e7f1147d", "yellow+flowers", "photo", "true").enqueue(new Callback<ImageDTO>() {
            @Override
            public void onResponse(Call<ImageDTO> call, Response<ImageDTO> response) {
                Log.e("Success", "success");
                if (response.isSuccessful()) {
                    hitArrayList.addAll(response.body().getHits());
                    filteredHitList.addAll(response.body().getHits());
                    imagesAdapter = new ImagesAdapter(ImageListActivity.this, filteredHitList, new ImagesAdapter.OnClickLisner() {
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent(ImageListActivity.this, ImageDetailActivity.class);
                            intent.putExtra("imageList", filteredHitList);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                    });
                    rv_images.setAdapter(imagesAdapter);
                }
            }

            @Override
            public void onFailure(Call<ImageDTO> call, Throwable t) {
            }
        });
    }
}