package com.dudes.dexin.bayae.common.model;

//class题库
public class QuizLib {
    private int id;
    private String name;
    private int num;
    private int type; //规定：选择题（0）| 填空题(1)
    public Quiz[] quizzes; //题目所在处
    /*
    @type: 0|1
    根据输入type值，判断题库的类型：选择（0）？填空（1)
    初始化题库
     */
    public QuizLib(int id, String name, int type){
        this.id = id;
        this.name = name;
        this.num = 0;
        this.quizzes = new Quiz[50]; //默认初容量：50
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    //获取题库的类型(0或1)，相对应（选择|填空）
    public int getType() {
        return type;
    }

    //获取题库对象
    public Quiz[] getQuizzes() {
        return quizzes;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public void setId(int id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }
    */

    //添加题目
    public void add(Quiz quiz){
        if (num >= quizzes.length){
            Quiz[] newQuizzes = new Quiz[quizzes.length+5]; //新建临时数组，每次默认增加5
            System.arraycopy(quizzes, 0, newQuizzes, 0, num);
            quizzes = newQuizzes; //传地址
        }
        quizzes[num++] = quiz;
    }

    /*
    根据index, 删除题目
    @n: 数组index, n>0 && n<num
     */
    public void del(int n){
        if ((num > 0) && ( n < num)){ //限制index范围
            int numMoved = num - n - 1;
            System.arraycopy(quizzes, n+1, quizzes, n, numMoved);
            num--; //自减
        }
    }



}
