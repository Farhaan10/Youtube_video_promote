package com.example.farhaan.youtube_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Farhaan on 11-08-2016.
 */
public class Information extends Fragment{

    TextView first_text;
    ImageView first_image, second_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_information, container, false);
        first_text = (TextView) view.findViewById(R.id.first_text_play);
        first_image = (ImageView) view.findViewById(R.id.first_image);
        second_image = (ImageView) view.findViewById(R.id.second_image);
        first_text.setText("Play & earn huge Amount");
        return view;
    }
}
