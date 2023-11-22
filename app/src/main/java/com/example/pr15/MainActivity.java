package com.example.pr15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    Context context = this;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        apiInterface = ServiceBuilder.request_builder().create(ApiInterface.class);
        add = findViewById(R.id.add);

        Call<ArrayList<Manga>> get_list = apiInterface.get_manga_list();
        get_list.enqueue(new Callback<ArrayList<Manga>>() {
            @Override
            public void onResponse(Call<ArrayList<Manga>> call, Response<ArrayList<Manga>> response) {
                if(response.isSuccessful()){
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    ArrayList<Manga> list = response.body();
                    ItemAdapter adapter = new ItemAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Manga>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCard.class);
                context.startActivity(intent);
            }
        });

    }
}