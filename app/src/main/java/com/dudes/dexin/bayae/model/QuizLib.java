package com.dudes.dexin.bayae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//class题库
public class QuizLib {
    private int id;
    private String name;//库的名字
    private int num;//题目数量
    private int type; //题型：0-选择题，1-填空题
    private List<Quiz> quizzes; //题目

    //无参构造方法，javaBean规范
    public QuizLib(){
        this.num = 0;
        this.quizzes = new ArrayList<>();
    }

    // 初始化题库
    public QuizLib(int id, String name, int type){
        this.id = id;
        this.name = name;
        this.num = 0;
        this.type = type;
        this.quizzes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId() {
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

    public void setNum(int num){
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes){
        this.quizzes = quizzes;
    }


    //添加题目
    public void add(Quiz quiz){
        this.quizzes.add(quiz);
        this.num++;
    }

    //根据id, 删除题目
    public int delete(int id){
        if ((num > 0) && (id <= num)){
            Iterator<Quiz> it = this.quizzes.iterator();
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



}
