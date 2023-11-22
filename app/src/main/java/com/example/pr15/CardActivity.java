package com.example.pr15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class CardActivity extends AppCompatActivity {
    Context context = this;
    int id;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ImageView MangaImage = findViewById(R.id.imageManga);
        TextView MangaName = findViewById(R.id.name);
        TextView MangaStudent = findViewById(R.id.author);
        TextView MangaScore = findViewById(R.id.mark);
        TextView MangaDetail = findViewById(R.id.description);
        Button delete = findViewById(R.id.delete_btn);
        Button back = findViewById(R.id.back_btn);
        apiInterface = ServiceBuilder.request_builder().create(ApiInterface.class);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id = extras.getInt("ID");
            Picasso.with(this).load(extras.getString("ImagePath")).error(R.drawable.error).into(MangaImage);
            MangaName.setText(extras.getString("Name"));
            MangaStudent.setText(extras.getString("AuthorName"));
            MangaScore.setText(extras.getString("Mark"));
            MangaDetail.setText(extras.getString("Details"));
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> delete_manga = apiInterface.deleteMangaList(id);

                delete_manga.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }


}