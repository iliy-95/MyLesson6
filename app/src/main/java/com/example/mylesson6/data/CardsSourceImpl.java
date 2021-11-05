package com.example.mylesson6.data;

import android.content.res.Resources;

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
    for (int i = 0; i < content.length; i++) {
        dataSource.add(new CardData(content [i], content[i] , false));
    }
    return this;

}
@Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
