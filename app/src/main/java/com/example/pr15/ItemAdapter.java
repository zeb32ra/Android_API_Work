package com.example.pr15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Manga> list;

    public ItemAdapter(Context context, ArrayList<Manga> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manga manga = list.get(position);
        holder.tv.setText(manga.getName());
        //extras
        holder.img = manga.getImg();
        holder.name = manga.getName();
        holder.author_name = manga.getFio();
        holder.mark = manga.getScore();
        holder.details = manga.getDetail();
        holder.id = manga.getId();
        //extras
        Picasso.with(context).load(manga.getImg()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;
        String img;
        String name;
        String author_name;
        int mark;
        int id;
        String details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.item_text_view);
            iv = itemView.findViewById(R.id.item_img_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), CardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ID", id);
                    intent.putExtra("ImagePath", img);
                    intent.putExtra("Name", "Название: " + name);
                    intent.putExtra("AuthorName", "Автор: " + author_name);
                    intent.putExtra("Mark", "Оценка: " + mark);
                    intent.putExtra("Details", details);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
