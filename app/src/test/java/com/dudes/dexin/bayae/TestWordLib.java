//package com.dudes.dexin.bayae;
//
//import com.dudes.dexin.bayae.model.Word;
//import com.dudes.dexin.bayae.model.WordLib;
//
//import org.junit.Test;
//
//public class TestWordLib {
//    @Test
//    public void test(){
//        WordLib lib = new WordLib(1, "小学单词");
//
//        Word w1 = new Word(1, "hello", "你好");
//        Word w2 = new Word(2, "world", "世界");
//        Word w3 = new Word(3, "book", "书");
//
//        lib.add(w1);
//        lib.add(w2);
//        lib.add(w3);
//
//        System.out.println(lib.words[0].getDone());
//        System.out.println(lib.words[1].getMeaning());
//        System.out.println(lib.words[2]);
//        System.out.println("数目 "+lib.getNum());
//        System.out.println(lib.getId());
//        System.out.println(lib.getName());
//        /*
//        运行结果：
//        0
//        世界
//        com.dudes.dexin.bayae.model.Word@e2144e4
//        数目 3
//        1
//        小学单词
//
//        Process finished with exit code 0
//         */
//        System.out.println(lib.words[4]);
//        System.out.println("before: " + lib.words[1]);
//        lib.del(1);//并未真正删除单词，只是往前挪
//        System.out.println("after: " + lib.words[1]);
//        /*运行结果
//        null
//        before: com.dudes.dexin.bayae.model.Word@e2144e4
//        after: com.dudes.dexin.bayae.model.Word@6477463f
//         */
//
//        //检查bug:假如删除最后一个，看是否num减1
//        System.out.println("before: "+lib.getNum());
//        lib.del(0);
//        System.out.println("after: "+lib.getNum());
//        /*
//        before: 2
//        after: 1
//         */
//
//
//    }
//}
