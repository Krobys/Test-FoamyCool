package com.akrivonos.beerdictionaryapplication.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.akrivonos.beerdictionaryapplication.R;

public class DetailedInfoBeerFragment extends Fragment {


    public DetailedInfoBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_info_beer, container, false);
    }

}
