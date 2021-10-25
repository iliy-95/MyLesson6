package com.example.mylesson6;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeekdayEngNoteFragment extends Fragment {

final static String APP_PP = "index";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_eng_weekday, container, false);
    }







    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            int index = arguments.getInt(APP_PP);
            AppCompatEditText EditText = view.findViewById(R.id.editTextView);
            AppCompatTextView TextView = view.findViewById(R.id.textView);
            TypedArray typedArray = getResources().obtainTypedArray(R.array.engw);
            //EditText.setText(typedArray.getResourceId(index, -1));
            TextView.setText(typedArray.getResourceId(index,-1));
            typedArray.recycle();
        }
    }
    public static WeekdayEngNoteFragment newInstance(int index) {
        // Создание фрагмента
        WeekdayEngNoteFragment fragment = new WeekdayEngNoteFragment();
        // Передача параметра через бандл
        Bundle args = new Bundle();
        args.putInt(APP_PP, index);
        fragment.setArguments(args);
        return fragment;
    }


}