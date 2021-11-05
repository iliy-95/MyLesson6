package com.example.mylesson6.data;

public class CardData {


    private final String content;
    private final int im;
    private final boolean like;

    public CardData(int im, String description,  boolean like){
        this.im=im;
        this.content=description;
        this.like=like;
    }



    public int getIm() {
        return im;
    }

    public String getContent() {
        return content;
    }



    public boolean isLike() {
        return like;
    }


}
