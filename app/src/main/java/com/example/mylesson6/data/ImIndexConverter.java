package com.example.mylesson6.data;

import com.example.mylesson6.R;

import java.util.Random;

public class ImIndexConverter {

    private static Random rnd = new Random();
    private static Object syncObj = new Object();

    private static int[] imIndex = {
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
            R.drawable.ic_launch1,
    };

    public static int randomImIndex(){
        synchronized (syncObj){
            return rnd.nextInt(imIndex.length);
        }
    }

    public static int getImByIndex(int index){
        if (index < 0 || index >= imIndex.length){
            index = 0;
        }
        return imIndex[index];
    }

    public static int getIndexByIm(int ym){
        for(int i = 0; i < imIndex.length; i++){
            if (imIndex[i] == ym){
                return i;
            }
        }
        return 0;
    }
}
