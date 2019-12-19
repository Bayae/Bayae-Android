package com.dudes.dexin.bayae.common.model;

// class类型-题目的数据结构
public class Quiz {
    private int id;
    private String description;// 问题描述
    public String[] opt; // 选项内容ABCD
    private String answer; //以字符串存答案(eg.0|01)，可根据字符串操作判断对错
    //private int done //完成状态

    public Quiz(int id){
        this.id = id;
        this.opt = new String[4]; //默认选项个数：4
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    /*
    设置选项数组，
    @opt: 临时字符串数组
    最好定义一个临时字符串数组，
    把各选项填入，传地址给this.opt
     */
    public void setOpt(String[] opt) {
        this.opt = opt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String[] getOpt() {
        return opt;
    }

    public String getAnswer() {
        return answer;
    }




}
