package com.example.mylesson6.ui;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylesson6.R;
import com.example.mylesson6.data.CardData;
import com.example.mylesson6.data.CardsSource;


public class SocialNetworkAdapter
        extends RecyclerView.Adapter<SocialNetworkAdapter.ViewHolder>{
    private final static String TAG = "SocialNetworkAdapter";
    private final CardsSource dataSource;
    private final Fragment fragment;
    private static OnItemClickListener itemClickListener;
    private int menuPosition;


    public int getMenuPosition() {
        return menuPosition;
    }


    // Передаём в конструктор источник данных
    // В нашем случае это массив, но может быть и запрос к БД
    public SocialNetworkAdapter(CardsSource dataSource, Fragment fragment) {

        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public SocialNetworkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        Log.d(TAG,"onCreateViewHolder");
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull ViewHolder  viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        //viewHolder.getTextView().setText(dataSource[i]);
        viewHolder.setData(dataSource.getCardData(i));
        Log.d(TAG, "onBindViewHolder");
        viewHolder.content.setText(dataSource.getCardData(i).getContent());


    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }




    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView im;
private final TextView s;
         final TextView content;
        final CheckBox like;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.imageView);
            s  = itemView.findViewById(R.id.zag);
            content = itemView.findViewById(R.id.edittex);
            like=itemView.findViewById(R.id.like);

            registerForContextMenu(im);


            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

            im.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    im.showContextMenu(0, 0);
                    return true;
                }
            });



        }


        private  void registerForContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
            fragment.registerForContextMenu(itemView);
        }
        }


        public void setData(@NonNull CardData cardData) {
            s.setText(cardData.getS());
            content.setText(cardData.getContent());
            im.setImageResource(cardData.getIm());
            like.setChecked(cardData.isLike());

        }
    }

}














