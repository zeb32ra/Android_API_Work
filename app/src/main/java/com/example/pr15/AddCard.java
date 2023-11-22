package com.example.pr15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCard extends AppCompatActivity {
    Button add;
    ApiInterface apiInterface;
    Context context = this;
    EditText name, url, description, fio, score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        add = findViewById(R.id.add_btn);
        apiInterface = ServiceBuilder.request_builder().create(ApiInterface.class);

        name = findViewById(R.id.name);
        url = findViewById(R.id.url);
        description = findViewById(R.id.descr);
        fio = findViewById(R.id.FIO);
        score = findViewById(R.id.score);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manga manga = new Manga(name.getText().toString(), description.getText().toString(), url.getText().toString(), fio.getText().toString(), Integer.parseInt(score.getText().toString()));
                Call<Manga> add_manga = apiInterface.postMangaList(manga);
                add_manga.enqueue(new Callback<Manga>() {
                    @Override
                    public void onResponse(Call<Manga> call, Response<Manga> response) {
                        Toast.makeText(context, "Manga was added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Manga> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}