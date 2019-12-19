package com.dudes.dexin.bayae.common.model;


public class WordLib {
    private int id; //词库的ID
    private String name; //词库的名字
    private int num; //词库的词数
    public Word[] words; //单词存放处

    //WordLib类的构造方法
    public WordLib(int id, String name){
        this.id = id; //存疑：ID？
        this.name = name;
        //this.num = 0; //词数初始化0
        this.words = new Word[100]; //初始化容量100
    }
    //添加单词
    public void add(Word word){
        //如果数组满了
        if (num >= words.length){
            Word[] newWords = new Word[words.length+10]; //新建数组，容量每次默认增加10
            System.arraycopy(words, 0, newWords, 0, num); //拷贝原来的单词库
            words = newWords;
        }
        words[num++] = word;
        //num++;
    }
    /*
    通过index，获取单词
        @n: index
     */
    public Word getWord(int n){
        return words[n];
    }
    /*
    根据index, 删除指定单词
        @n: index(注意：是数组的index，非第几个单词)
        限制条件n: n>0 && n<num(单词数)
     */
    public void del(int n){
        //检查数组是否为空
        if ((num != 0)&&(n < num)){ //不为空时，执行del操作
            int numMoved = num - n -1;//需要向前移动单词的数量，因index故需要自减1
            System.arraycopy(words, n+1, words, n, numMoved);
            //words[num--] = null; //移动完后，置空
            num--; //单词数减1
        }
    }
    /*
    根据index, 修改单词##暂时不要##
    public void edit()
     */

    public int getId() { // 获取单词库编号
        return id;
    }

    public String getName() { // 获取单词库名
        return name;
    }

    public int getNum() { //获取单词数
        return num;
    }
}


