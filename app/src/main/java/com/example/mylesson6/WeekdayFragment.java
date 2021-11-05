package com.example.mylesson6;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylesson6.data.CardsSource;
import com.example.mylesson6.data.CardsSourceImpl;
import com.example.mylesson6.ui.SocialNetworkAdapter;

import java.util.Objects;


public class WeekdayFragment extends Fragment {
    private static final String WEEKDAY_NOTES = "WeekdayNotes";

    boolean isLandScape;
    private CardsSource data;
    private RecyclerView recyclerView;
    private SocialNetworkAdapter noteAdapter;
    private int currentPosition = 0;


    public static WeekdayFragment newInstance() {
        return new WeekdayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_weekday, container, false);
       // String[] data = getResources().getStringArray(R.array.wad);
        // Получим источник данных для списка
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        CardsSource data = new CardsSourceImpl(getResources()).init();
        initRecyclerView(recyclerView, data,view);
        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(RecyclerView recyclerView, CardsSource data, View view) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        SocialNetworkAdapter adapter = new SocialNetworkAdapter(data);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separatot, null));
        recyclerView.addItemDecoration(itemDecoration);



        // Установим слушателя
        adapter.SetOnItemClickListener(new SocialNetworkAdapter.OnItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemClick(View view, int  position) {
                Toast.makeText(getContext(), "Заметка " + position, Toast.LENGTH_SHORT).show();
            }
            });



        LinearLayout layoutView = (LinearLayout) view;
        String[] d = getResources().getStringArray(R.array.title);
        //При помощи этого объекта будем доставать элементы, спрятанные в item.xml
        LayoutInflater ltInflater = getLayoutInflater();


        for (int i = 0; i < d.length; i++){
            String note = d[i];
        /*TextView tv = new TextView(getContext());
        tv.setText(note);
        tv.setTextSize(30);
        layoutView.addView(tv);*/

            //Достаём элемент из item.xml
            View item = ltInflater.inflate(R.layout.item, layoutView, false);
            //Находим в этом элементе TextView
            TextView tv = item.findViewById(R.id.edittex);
            tv.setText(note);
            layoutView.addView(item);


            final int position = i;
            tv.setOnClickListener(v -> {
                currentPosition = position;
                WeekdayEngNoteFragment(position);
            });

        }


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
