package com.example.mylesson6;

import java.util.Calendar;

public class Notes {
    private String title;               //заголовок заметки
    private String content;             //содержимое заметки
    private Calendar createDate;        //дата и время создания заметки
    private boolean marked;             //флаг состояния заметки (отмечена или не отмечена)

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
