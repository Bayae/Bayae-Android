package com.dudes.dexin.bayae.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String description;// 问题描述
    private List<String> opt; // 选项描述
    private String answer; //以字符串存答案(eg.0|01)，可根据字符串操作判断对错
    private int status ;//状态:0-未做,1-做对,2-做错

    public Quiz(int id){
        this.id = id;
        this.status = 0;
        this.opt = new ArrayList<>();
    }

    public Quiz(){
        this.opt =  new ArrayList<>();
        this.status = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOpt() {
        return opt;
    }

    public void setOpt(List<String> opt) {
        this.opt = opt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
