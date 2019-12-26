package com.dudes.dexin.bayae.model;

public class Word{
    private int id; //词的ID
    private String word; //单词
    private String meaning; //单词意思
    private int done; //状态:0-未完成,1-已完成

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public Word(){
        this.done = 0;
    }

    public Word(int id, String word, String meaning){
        this.id = id; //单词的编号
        this.word = word;
        this.meaning = meaning;
        this.done = 0; //默认：未完成
    }

}