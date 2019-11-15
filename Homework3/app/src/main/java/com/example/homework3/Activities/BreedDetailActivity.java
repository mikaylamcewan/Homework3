package com.example.homework3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework3.CatAdapter;
import com.example.homework3.Cats;
import com.example.homework3.Fragments.FavouritesFragment;
import com.example.homework3.Fragments.SearchCatsFragment;
import com.example.homework3.R;

public class BreedDetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView weightTextView;
    private TextView temperamentTextView;
    private TextView originTextView;
    private TextView lifespanTextView;
    private TextView wikilinkTextView;
    private TextView dflTextView;

    //favourite button code
    private ImageButton favouriteButton;
    public boolean isFav=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail_layout);

        nameTextView = findViewById(R.id.cat_name);
        descriptionTextView = findViewById(R.id.description);
        weightTextView=findViewById(R.id.weight);
        temperamentTextView=findViewById(R.id.temperament);
        originTextView=findViewById(R.id.origin);
        lifespanTextView=findViewById(R.id.lifespan);
        wikilinkTextView=findViewById(R.id.wiki_link);
        dflTextView=findViewById(R.id.dfl);
        favouriteButton=findViewById(R.id.favButton);

        //intent code
        Intent intent = getIntent();

//        String id = intent.getStringExtra("id");
        final String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String weight = intent.getStringExtra("weight_imperial");
        String temperament = intent.getStringExtra("temperament");
        String origin = intent.getStringExtra("origin");
        String lifespan = intent.getStringExtra("life_span");
        String wikipedia = intent.getStringExtra("wikipedia_url");
        String dfl = intent.getStringExtra("dogFriend");

        // favourite button onClick for user to select cat as favourite
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               FavouritesFragment.favouriteCatList.add(Cats);

                Toast.makeText(getApplicationContext(), "Cat added to favourites!",
                        Toast.LENGTH_LONG).show();

                if(isFav){
                    favouriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else{
                    favouriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
                isFav = !isFav;
            }

        });

       // setting text
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        weightTextView.setText(weight);
        temperamentTextView.setText(temperament);
        originTextView.setText(origin);
        lifespanTextView.setText(lifespan);
        wikilinkTextView.setText(wikipedia);
        dflTextView.setText(dfl);

    }
}
