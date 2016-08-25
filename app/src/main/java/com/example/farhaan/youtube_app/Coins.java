package com.example.farhaan.youtube_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Farhaan on 11-08-2016.
 */
public class Coins extends Fragment{

    TextView text_coins, no_of_coins, gift_cards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_coins, container, false);
        text_coins = (TextView) view.findViewById(R.id.text_coins);
        no_of_coins = (TextView) view.findViewById(R.id.number_of_coins);
        gift_cards = (TextView) view.findViewById(R.id.gift_cards);
        text_coins.setText("My Coins");
        no_of_coins.setText("You have 0 coins in your account");
        gift_cards.setText("Gift Cards");
        return view;
    }
}