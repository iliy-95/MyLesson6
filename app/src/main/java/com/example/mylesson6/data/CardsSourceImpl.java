package com.example.mylesson6.data;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.mylesson6.R;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceImpl  implements CardsSource {
    private List<CardData> dataSource;
    private final Resources resources;    // ресурсы приложения

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

public CardsSourceImpl init() {
    dataSource = new ArrayList<>();
//String[] title = resources.getStringArray(R.array.title);
    String[] content = resources.getStringArray(R.array.title);
    int[] im = getImageArray();
    for (int i = 0; i < content.length; i++) {
        dataSource.add(new CardData(im[i], content [i], false));
    }
    return this;

}

    private int[] getImageArray() {
        TypedArray im = resources.obtainTypedArray(R.array.im);
        int length = im.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = im.getResourceId(i, 0);
        }
        return answer;

    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }

}
