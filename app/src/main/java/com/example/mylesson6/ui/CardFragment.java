package com.example.mylesson6.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylesson6.MainActivity;
import com.example.mylesson6.R;
import com.example.mylesson6.data.CardData;
import com.example.mylesson6.data.ImIndexConverter;
import com.example.mylesson6.observe.Publisher;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CardFragment extends Fragment {
    private static final String ARG_CARD_DATA = "CARD_DATA";
    private CardData cardData;
    private Publisher publisher;

    private TextInputEditText s;
    private TextInputEditText content;
    private DatePicker datePicker;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        publisher = ((MainActivity) context).getPublisher();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        publisher = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        initView(view);
        if (cardData != null) {
            populateView();
        }
        return view;
    }



    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);
    }

    // Получение даты из DatePicker
    private Date getDateFromDatePicker() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.datePicker.getYear());
        cal.set(Calendar.MONTH, this.datePicker.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return cal.getTime();
    }


    @SuppressLint("CutPasteId")
    private void initView(View view) {
       s = view.findViewById(R.id.inputss);
        content = view.findViewById(R.id.inputcon);
        datePicker = view.findViewById(R.id.inputDate);
    }
    private void populateView() {
        s.setText(cardData.getS());
        content.setText(cardData.getContent());
        initDatePicker(cardData.getDate());
    }

    // Для редактирования данных
    public static CardFragment newInstance(CardData cardData) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA,  cardData);
        fragment.setArguments(args);
        return fragment;
    }

    // Для добавления новых данных
    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardData = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    private CardData collectCardData() {
        String s = Objects.requireNonNull(this.s.getText()).toString();
        String content = Objects.requireNonNull(this.content.getText()).toString();
        Date date = getDateFromDatePicker();

        if (cardData != null){
           cardData.setS(s);
           cardData.setContent(content);
           cardData.setDate(date);
           return cardData;
        } else {
            int im = ImIndexConverter.getImByIndex(ImIndexConverter.randomImIndex());
            return new CardData(s, content, im, false, date);
        }

    }



    // Здесь соберём данные из views
    @Override
    public void onStop() {
        super.onStop();
        cardData = collectCardData();
    }

    // Здесь передадим данные в паблишер
    @Override
    public void onDestroy() {
        Log.d("mylogs","onDestroy()");
        super.onDestroy();
        publisher.notifyTask(cardData);
    }


}


