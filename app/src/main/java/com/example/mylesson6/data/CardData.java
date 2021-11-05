package com.example.mylesson6.data;

public class CardData {


    private final String title;
    private final String content;
    private final boolean like;

    public CardData(String title, String description,  boolean like){
        this.title = title;
        this.content=description;
        this.like=like;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }



    public boolean isLike() {
        return like;
    }


}
