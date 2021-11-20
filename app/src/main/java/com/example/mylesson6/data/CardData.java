package com.example.mylesson6.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData implements Parcelable {



    private String id;
    private String content;
    private String s;
    private int im;
    private boolean like;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public CardData(String s, String content, int im, boolean like,Date date) {
        this.im=im;
        this.content=content;
        this.s=s;
        this.like=like;
        this.date = date;
    }


    protected CardData(Parcel in) {
        content = in.readString();
        s = in.readString();
        im = in.readInt();
        like = in.readByte() != 0;
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public void setDate(Date date) {
        this.date = date;
    }
    public void setS(String s) {
        this.s = s;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setLike(Boolean like) {
        this.like = like;
    }
    public void setIm(int im) {
        this.im = im;
    }

    public Date getDate() {
        return date;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(s);
        dest.writeInt(im);
        dest.writeByte((byte) ( like ? 1 : 0 ));
    }
}
