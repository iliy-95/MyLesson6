package com.example.mylesson6;

import static com.example.mylesson6.R.drawable.ic_launch1;
import static com.example.mylesson6.R.drawable.separatot;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylesson6.data.CardData;
import com.example.mylesson6.data.CardsSource;
import com.example.mylesson6.data.CardsSourceImpl;
import com.example.mylesson6.ui.SocialNetworkAdapter;


public class WeekdayFragment extends Fragment {
    private static final String WEEKDAY_NOTES = "WeekdayNotes";

    boolean isLandScape;
    private CardsSource data;
    private RecyclerView recyclerView;
    private int currentPosition = 0;
    private SocialNetworkAdapter adapter;


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
         data = new CardsSourceImpl(getResources()).init();
        //initRecyclerView(recyclerView, data,view);
        initView(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_eng_weekday, menu);
    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                data.addCardData(new CardData("Заголовок " + data.size(),
                        "Заметка " + data.size(),
                        ic_launch1,
                        false));
                adapter.notifyItemInserted(data.size() - 1);
                recyclerView.scrollToPosition(data.size() - 1);
                return true;
            case R.id.action_iz:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        // Получим источник данных для списка
        data = new CardsSourceImpl(getResources()).init();
        initRecyclerView();
    }






    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
         adapter = new SocialNetworkAdapter(data, this);
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
    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v,  ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.con_menu,menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch(item.getItemId()) {
            case R.id.action_edit:

                data.updateCardData(position,
                        new CardData("Ваша заметка " ,
                                data.getCardData(position).getContent(),
                                data.getCardData(position).getIm(),
                                false));
                adapter.notifyItemChanged(position);

                return true;
            case R.id.action_delete:
                data.deleteCardData(position);
                adapter.notifyItemRemoved(position);
                return true;


        }
        return super.onContextItemSelected(item);
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
