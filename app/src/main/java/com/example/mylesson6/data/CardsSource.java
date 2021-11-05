package com.example.mylesson6.data;

import android.content.Context;

public interface CardsSource {


        CardData getCardData(int position);
        int size();


}
