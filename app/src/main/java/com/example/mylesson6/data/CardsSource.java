package com.example.mylesson6.data;

import android.content.Context;

public interface CardsSource {


        CardData getCardData(int position);
        int size();
        void deleteCardData(int position);
        void updateCardData(int position, CardData cardData);
        void addCardData(CardData cardData);
        void clearCardData();


}
