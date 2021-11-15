package com.example.mylesson6.data;

import com.example.mylesson6.R;

import java.util.Random;

public class ImIndexConverter {

    private static Random rnd = new Random();
    private static Object syncObj = new Object();

    private static int[] picIndex = {
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
            return rnd.nextInt(picIndex.length);
        }
    }

    public static int getImByIndex(int index){
        if (index < 0 || index >= picIndex.length){
            index = 0;
        }
        return picIndex[index];
    }

    public static int getIndexByIm(int picture){
        for(int i = 0; i < picIndex.length; i++){
            if (picIndex[i] == picture){
                return i;
            }
        }
        return 0;
    }
}
