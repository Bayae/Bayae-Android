package com.dudes.dexin.bayae;

import com.dudes.dexin.bayae.common.model.Quiz;
import com.dudes.dexin.bayae.common.model.QuizLib;

import org.junit.Test;

public class TestQuizLib {
    @Test
    public void test(){
        QuizLib qlib = new QuizLib(1, "英语单词选择", 0);//选择题

        Quiz q1 = new Quiz(1);
        /*注意题目的各部分，存入是都是字符串*/
        //description
        q1.setDescription("Scale的意思:");
        //options
        String[] opt = {"n.规模；等级；比例；刻度", "vt.攀登", "吃屎", "滚"}; //隐式初始化
        q1.setOpt(opt);
        //answer
        q1.setAnswer("0"); //第一个选项

        System.out.println(qlib.getType()); //0

        qlib.setType(1); //重新设置类型，填空题
        Quiz q2 = new Quiz(2);
        q2.setDescription("你是个____和____的人");
        opt = new String[]{"温柔", "磁性", "傻憨", "逗比"}; //静态初始化
        q2.setOpt(opt);
        q2.setAnswer("23"); //傻憨逗比

        //初始化完毕，加入库
        qlib.add(q1);
        qlib.add(q2);


        /*打印*/
        //题库info
        System.out.println(qlib.getId());
        System.out.println(qlib.getName());
        System.out.println(qlib.getNum());
        System.out.println(qlib.getType()); //0
        System.out.println(qlib.getQuizzes()); //qlib.getQuizzes().toString()
        //题目info
        System.out.println("Quiz#1: ");
        System.out.println(qlib.quizzes[0]);
        System.out.println(qlib.quizzes[0].getId());
        System.out.println(qlib.quizzes[0].getDescription());
        System.out.println(qlib.quizzes[0].opt[2]); //public opt[]
        System.out.println(qlib.quizzes[0].getAnswer());
        System.out.println("Quiz#2: ");
        System.out.println(qlib.quizzes[1]);
        System.out.println(qlib.quizzes[1].getId());
        System.out.println(qlib.quizzes[1].getDescription());
        System.out.println(qlib.quizzes[1].opt[2]);
        System.out.println(qlib.quizzes[1].getAnswer());

        //以下测试删除题目
        System.out.println("before: " + qlib.getNum());
        qlib.del(0);
        System.out.println("after: " + qlib.getNum());
        System.out.println(qlib.quizzes[0].getId()); //并没有真正删除：只是往前挪（覆盖），最后一个也没删。
        //System.out.println(qlib.quizzes[1].getId());
        /*
        测试结果：
        1
        英语单词选择
        2
        1
        [Lcom.dudes.dexin.bayae.common.model.Quiz;@e2144e4
        Quiz#1:
        com.dudes.dexin.bayae.common.model.Quiz@6477463f
        1
        Scale的意思:
        吃屎
        0
        Quiz#2:
        com.dudes.dexin.bayae.common.model.Quiz@3d71d552
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
