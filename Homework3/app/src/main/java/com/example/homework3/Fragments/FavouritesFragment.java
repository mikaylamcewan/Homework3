package com.example.homework3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework3.CatAdapter;
import com.example.homework3.Cats;
import com.example.homework3.R;


import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private RecyclerView favouriteCatsrv;
    // code to put the cat into the favourites fragment
    public static List<Cats> favouriteCatList = new ArrayList<>();

    public FavouritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite_cats, container, false);
        final CatAdapter catAdapter = new CatAdapter();

        //adding favourites to recycler view
        favouriteCatsrv = view.findViewById(R.id.favcatsrv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        //use list to fill the recycler view
        favouriteCatsrv.setLayoutManager(layoutManager);
        catAdapter.setData(favouriteCatList);
        favouriteCatsrv.setAdapter(catAdapter);




        return view;


    }
}
