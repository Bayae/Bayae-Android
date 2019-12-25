package com.dudes.dexin.bayae;

import com.alibaba.fastjson.JSON;
import com.dudes.dexin.bayae.model.Quiz;
import com.dudes.dexin.bayae.model.QuizLib;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuizLib {
    @Test
    public void test(){
        QuizLib qlib = new QuizLib(1, "elc1");


        Quiz q1 = new Quiz(1);
        /*注意题目的各部分，存入是都是字符串*/
        //description
        q1.setDescription("Scale的意思:");
        //options
        List<String> opt = new ArrayList<>();
        opt.add("n.规模；等级；比例；刻度");
        opt.add("vt.攀登");
        opt.add("n.吃饭");
        opt.add("adv.更多地");
        q1.setOpt(opt);
        //answer
        q1.setAnswer("0"); //第一个选项


        Quiz q2 = new Quiz(2);
        q2.setDescription("你是个____和____的人");
        opt = new ArrayList<>();
        opt.add("温柔");
        opt.add("磁性");
        opt.add("逗比");
        opt.add("傻憨");
        q2.setOpt(opt);
        q2.setAnswer("43"); //傻憨逗比

        //初始化完毕，加入库
        qlib.addMultChoiceQuestion(q1);
        qlib.addFillBlankQuestion(q2);

        String qLibObjectToJson = JSON.toJSONString(qlib);
        System.out.println(qLibObjectToJson);


//
//        /*打印*/
//        //题库info
//        System.out.println(qLibJsonToObject.getId());
//        System.out.println(qLibJsonToObject.getName());
//        System.out.println(qLibJsonToObject.getNum());
//        System.out.println(qLibJsonToObject.getType()); //0
//        System.out.println(qLibJsonToObject.getQuizzes()); //qlib.getQuizzes().toString()
//        //题目info
//        System.out.println("Quiz#1: ");
//        System.out.println(qLibJsonToObject.getQuizzes().get(0));
//        System.out.println(qLibJsonToObject.getQuizzes().get(0).getId());
//        System.out.println(qLibJsonToObject.getQuizzes().get(0).getDescription());
//        System.out.println(qLibJsonToObject.getQuizzes().get(0).getOpt());
//        System.out.println(qLibJsonToObject.getQuizzes().get(0).getAnswer());
//        System.out.println("Quiz#2: ");
//        System.out.println(qLibJsonToObject.getQuizzes().get(1));
//        System.out.println(qLibJsonToObject.getQuizzes().get(1).getId());
//        System.out.println(qLibJsonToObject.getQuizzes().get(1).getDescription());
//        System.out.println(qLibJsonToObject.getQuizzes().get(1).getOpt());
//        System.out.println(qLibJsonToObject.getQuizzes().get(1).getAnswer());

//        //以下测试删除题目
//        System.out.println("before: " + qlibJsonToObject.getNum());
//        qlib.del(0);
//        System.out.println("after: " + qlibJsonToObject.getNum());
//        System.out.println(qlibJsonToObject.quizzes[0].getId()); //并没有真正删除：只是往前挪（覆盖），最后一个也没删。
        //System.out.println(qlib.quizzes[1].getId());
        /*
        测试结果：
        1
        英语单词选择
        2
        1
        [Lcom.dudes.dexin.bayae.common.model.Quiz;@e2144e4
        Quiz#1:
        com.dudes.dexin.bayae.model.Quiz@6477463f
        1
        Scale的意思:
        吃屎
        0
        Quiz#2:
        com.dudes.dexin.bayae.model.Quiz@3d71d552
        2
        你是个____和____的人
        傻憨
        23
        before: 2
        after: 1
        2
        2

        Process finished with exit code 0
         */

    }
}
