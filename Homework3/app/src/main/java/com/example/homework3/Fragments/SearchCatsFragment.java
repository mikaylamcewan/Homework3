package com.example.homework3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homework3.CatAdapter;
import com.example.homework3.Cats;
import com.example.homework3.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCatsFragment extends Fragment {


    public SearchCatsFragment() {
    }
    private RecyclerView catRecyclerView;
    private EditText searchCatsText;
    private TextView setCatText;
    private ImageButton searchButton;
    private String userSearchInput;
    private List<Cats>catList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search_cats, container, false);

        //new edit code
        catRecyclerView = view.findViewById(R.id.cat_recycler);
        catRecyclerView.setVisibility(View.INVISIBLE);
        searchButton=view.findViewById(R.id.searchButton);


        searchCatsText = view.findViewById(R.id.catSearchText);
        setCatText = view.findViewById(R.id.setSearch);
        searchCatsText.setVisibility(View.VISIBLE);
        setCatText.setVisibility(View.INVISIBLE);

        //end new button code

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        catRecyclerView.setLayoutManager(layoutManager);

        //cat API
        final CatAdapter catAdapter = new CatAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds?api_key=6de74a93-dfc2-4a24-8e98-5585d796b790";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] catsArray=gson.fromJson(response, Cats[].class);
                final ArrayList<Cats> catsArrayList = new ArrayList<>(Arrays.asList(catsArray));
                catAdapter.setData(catsArrayList);
                catRecyclerView.setAdapter(catAdapter);

                //allowing the user to search for a cat, only visible once the user has clicked on the search button
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userSearchInput = searchCatsText.getText().toString();
                        setCatText.setText(userSearchInput);
                        String input = setCatText.getText().toString();
                        String userInput = input.toLowerCase();
                        List<Cats>newCatList=new ArrayList<>();
                        // for loop to get cats
                        for(Cats name: catsArrayList) {
                            if(name.getName().toLowerCase().contains(userInput)) {
                                newCatList.add(name);
                            }
                        }
                        catAdapter.setData(newCatList);
                        catRecyclerView.setAdapter(catAdapter);
                        catRecyclerView.setVisibility(view.VISIBLE);

                    }
                });

                requestQueue.stop();

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("The request has failed!");

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
        return view;
    }
}
