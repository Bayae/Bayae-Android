package com.dudes.dexin.bayae.common.model;

//  class类型-单词的数据结构
public class Word{
    private int id; //词的ID
    private String word; //单词
    private String meaning; //单词意思
    private int done; //done(1) or undone(0)

    public Word(int id, String word, String meaning){
        this.id = id; //单词的编号
        this.word = word;
        this.meaning = meaning;
        this.done = 0; //默认：未完成
    }
    /* 改变完成状态
    @done: 0 or 1
    */
    public void setDone(int done) {
        this.done = done;
    }

    //  获取单词
    public String getWord() {
        return word;
    }
    // 获取单词意思
    public String getMeaning() {
        return meaning;
    }
    //  获取完成状态
    public int getDone() {
        return done;
    }
}