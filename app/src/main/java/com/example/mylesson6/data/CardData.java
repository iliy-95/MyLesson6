package com.example.mylesson6.data;

public class CardData {


    private final String content;
   private final String s;
    private final int im;
    private final boolean like;





    public CardData(String s, String content, int im, boolean like) {
        this.im=im;
        this.content=content;
        this.s=s;
        this.like=like;
    }



    public String getS() {
        return s;
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
