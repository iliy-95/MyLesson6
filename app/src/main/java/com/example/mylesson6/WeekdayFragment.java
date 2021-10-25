package com.example.mylesson6;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WeekdayFragment extends Fragment {
    private static final String WEEKDAY_NOTES = "WeekdayNotes";

    private int currentPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weekday, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(WEEKDAY_NOTES, 0);
        }

        if (isLandscape()) {
            showLandWerkday(currentPosition);
        }


        initList(view);
    }

private void initList(View view){
    LinearLayout layoutView = (LinearLayout) view;
    String[] d = getResources().getStringArray(R.array.wad);

    for (int i = 0; i < d.length; i++){
        String note = d[i];
        TextView tv = new TextView(getContext());
        tv.setText(note);
        tv.setTextSize(30);
        layoutView.addView(tv);
        final int position = i;
        tv.setOnClickListener(v -> {
            currentPosition = position;
            WeekdayEngNoteFragment(position);
        });

    }

    }

    private void WeekdayEngNoteFragment(int index) {


        if (isLandscape()) {
            showLandWerkday(index);
        } else {
            showPortDay(index);
        }

    }
private void showPortDay(int index){
    WeekdayEngNoteFragment weekdayEngNoteFragment = WeekdayEngNoteFragment.newInstance(index);
    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, weekdayEngNoteFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
}




    private void showLandWerkday(int index) {

        WeekdayEngNoteFragment detail = WeekdayEngNoteFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.eng_container, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(WEEKDAY_NOTES, currentPosition);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }






}
