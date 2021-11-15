package com.example.mylesson6.ui;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mylesson6.R;


public class WeekdayEngNoteFragment extends Fragment {

final static String APP_PP = "index";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note_eng_weekday, container, false);
    }






//menu_eng_weekday сделать смену темы и тд
    public void  onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater){
        inflater.inflate(R.menu.menu_eng_weekday, menu);
        MenuItem item = menu.findItem(R.id.add);
    if (item != null){
        item.setVisible(false);
    }

}








    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            int index = arguments.getInt(APP_PP);
            AppCompatEditText EditText = view.findViewById(R.id.editTextView);
            AppCompatTextView TextView = view.findViewById(R.id.textView);
            TypedArray typedArray = getResources().obtainTypedArray(R.array.content);
            //EditText.setText(typedArray.getResourceId(index, -1));
            TextView.setText(typedArray.getResourceId(index,-1));
            typedArray.recycle();
        }
        Button buttonBack = view.findViewById(R.id.back);
        buttonBack.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });


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