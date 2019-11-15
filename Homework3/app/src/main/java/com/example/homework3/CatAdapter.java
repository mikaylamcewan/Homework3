package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework3.Activities.BreedDetailActivity;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{

    private List<Cats> catsToAdapt;

    public void setData(List<Cats> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cats, parent, false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cats catsAtPosition = catsToAdapt.get(position);

        holder.catName.setText(catsAtPosition.getName());
        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Context con = view.getContext();


                Intent intent = new Intent(con, BreedDetailActivity.class);
//                intent.putExtra("id", catsAtPosition.getId());
                intent.putExtra("name", catsAtPosition.getName());

                //intent for cat attributes
                intent.putExtra("description", catsAtPosition.getDescription());
                intent.putExtra("weight_imperial", catsAtPosition.getWeight_imperial());
                intent.putExtra("temperament", catsAtPosition.getTemperament());
                intent.putExtra("origin", catsAtPosition.getOrigin());
                intent.putExtra("life_span", catsAtPosition.getLife_span());
                intent.putExtra("wikipedia_url", catsAtPosition.getWikipedia_url());

                //converting int into String for dog dog friendliness level
                int dogflv = catsAtPosition.getDog_friendly();
                String dogFriend = Integer.toString(dogflv);
                intent.putExtra("dogFriend",dogFriend);

                con.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catName;

        public CatViewHolder(View v) {
            super(v);
            view = v;
            catName=v.findViewById(R.id.cat_name);
        }

    }
}