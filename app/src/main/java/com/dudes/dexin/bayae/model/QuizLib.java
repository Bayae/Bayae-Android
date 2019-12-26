package com.dudes.dexin.bayae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//class题库
public class QuizLib {
    private int id;
    private String name;//库的名字
    private int numofFBQ;//填空题数量
    private int numofMCQ;//选择题数量
    private List<Quiz> fillBlankQuestions; //填空题
    private List<Quiz> multChoiceQuestions;//选择题

    //无参构造方法，javaBean规范
    public QuizLib(){
        this.numofFBQ = 0;
        this.numofMCQ = 0;
        this.fillBlankQuestions = new ArrayList<>();
        this.multChoiceQuestions = new ArrayList<>();
    }

    // 初始化题库
    public QuizLib(int id, String name){
        this.id = id;
        this.name = name;
        this.numofFBQ = 0;
        this.numofMCQ = 0;
        this.fillBlankQuestions = new ArrayList<>();
        this.multChoiceQuestions = new ArrayList<>();
    }
    //添加填空题目
    public void addFillBlankQuestion(Quiz quiz){
        this.fillBlankQuestions.add(quiz);
        this.numofFBQ++;
    }

    //根据id, 删除题目
    public int deleteFillBlankQuestion(int id){
        if ((numofFBQ > 0) && (id <= numofFBQ)){
            Iterator<Quiz> it = this.fillBlankQuestions.iterator();
            while(it.hasNext()){
                int i = it.next().getId();
                if(i == id){
                    it.remove();
                }
            }
            numofFBQ--; //自减
            return 1;
        }
        return 0;
    }

    //添加选择题目
    public void addMultChoiceQuestion(Quiz quiz){
        this.multChoiceQuestions.add(quiz);
        this.numofMCQ++;
    }

    //根据id, 删除题目
    public int deleteMultChoiceQuestion(int id){
        if ((numofMCQ > 0) && (id <= numofMCQ)){
            Iterator<Quiz> it = this.multChoiceQuestions.iterator();
            while(it.hasNext()){
                int i = it.next().getId();
                if(i == id){
                    it.remove();
                }
            }
            numofMCQ--; //自减
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

    public int getNumofFBQ() {
        return numofFBQ;
    }

    public void setNumofFBQ(int numofFBQ) {
        this.numofFBQ = numofFBQ;
    }

    public int getNumofMCQ() {
        return numofMCQ;
    }

    public void setNumofMCQ(int numofMCQ) {
        this.numofMCQ = numofMCQ;
    }

    public List<Quiz> getFillBlankQuestions() {
        return fillBlankQuestions;
    }

    public void setFillBlankQuestions(List<Quiz> fillBlankQuestions) {
        this.fillBlankQuestions = fillBlankQuestions;
    }

    public List<Quiz> getMultChoiceQuestions() {
        return multChoiceQuestions;
    }

    public void setMultChoiceQuestions(List<Quiz> multChoiceQuestions) {
        this.multChoiceQuestions = multChoiceQuestions;
    }
}
