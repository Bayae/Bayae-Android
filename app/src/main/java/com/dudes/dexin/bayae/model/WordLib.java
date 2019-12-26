package com.dudes.dexin.bayae.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordLib {
    private int id; //词库的ID
    private String name; //词库的名字
    private int num; //词库的词数
    public List<Word> words; //单词存放处

    public WordLib(){
        this.words = new ArrayList<>();
        this.num =  0;
    }
    //WordLib类的构造方法
    public WordLib(int id, String name){
        this.id = id; //存疑：ID？
        this.name = name;
        this.num = 0; //词数初始化0
        this.words = new ArrayList<>();
    }

    //添加单词
    public void add(Word word){
        words.add(word);
        num++;
    }

    public int delete(int id){
        if ((num > 0) && (id <= num)){
            Iterator<Word> it = this.words.iterator();
            while(it.hasNext()){
                int i = it.next().getId();
                if(i == id){
                    it.remove();
                }
            }
            num--; //自减
            return 1;
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}


