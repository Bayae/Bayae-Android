//package com.dudes.dexin.bayae.libManagement;
//
//import com.dudes.dexin.bayae.model.Quiz;
//import com.dudes.dexin.bayae.model.QuizLib;
//import com.dudes.dexin.bayae.model.WordLib;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
public class Libs4rmTxt {
//    /*
//    获得该文件夹里面的所有文件名，
//    从txt文件夹，导入生成Libs；
//
//    libs包括了：
//        dirPath, 文件夹地址
//
//    fileNames[]，文件名
//     */
//
//    public String dirPath;
//    public String encoding;
//    public ArrayList<String> fileNames = new ArrayList<String>();
//    public int fileNum; //库的总数量
//
//    public Libs4rmTxt(String dirPath, String encoding) {
//        //fileNames = new String[100]; //默认最大能存100个库
//        this.dirPath = dirPath;
//        this.encoding = encoding;
//        init();
//    }
//
//    public void init() {
//        File dir = new File(dirPath);
//        File[] files = dir.listFiles(); //获取该目录所有的文件，放入数组中
//        if (files != null) { //防止该文件夹为空
//            for (int i = 1; i < files.length; i++) { //从1开始，打印 files[0]存入无效的'.DS_Store'文件
//                String fileName = files[i].getName(); //带有后缀的文件名
//                String fname = fileName.substring(0, fileName.lastIndexOf(".")); //去后缀后的文件名
//                //String fPath = dirPath + fileName; //文件地址
//
//                //初始化所有的库名
//                fileNames.add(fname);
//                System.out.println(fname); //打印文件名
//                //导入文件流
//                //importLibs4rmTxt(fileNum, fname, fPath, encoding); //默认encoding:UTF-8
//                this.fileNum++; //自增1
//            }
//        }
//    }
///*
//    public void getFileName(String dirPath){
//        File dir = new File(dirPath);
//        File[] files = dir.listFiles(); //获取该目录所有的文件，放入数组中
//        if (files != null){ //防止该文件夹为空
//            for (int i=1; i<files.length; i++){ //从1开始，打印 files[0]存入无效的'.DS_Store'文件
//                String fileName = files[i].getName();
//                String filePath = dirPath+"/"+fileName;
//                String fname = fileName.substring(0, fileName.lastIndexOf("."));
//                //分别存进全局变量fileNames,filePaths
//                //this.fileNames.
//                //System.out.println("打印文件名" + i + " "+ fname);
//                //System.out.println(this.filePaths);
//            }
//        }
//    }
//
// */
//
//    public String getValue(String fline, String str) {
//        String value = "";
//        value = fline.substring(0, fline.lastIndexOf(str));
//        return value;
//    }
//
//    public Object importLibs4rmTxt(int id, String fname, String fpath, String encoding) {
//        try {
//            FileInputStream fin = new FileInputStream(fpath);
//            InputStreamReader reader = new InputStreamReader(fin, encoding);
//            BufferedReader buffReader = new BufferedReader(reader);
//
//            //初始化一个库(either 题库 or 词库)
//            String fline = ""; //每行输入流
//            fline = buffReader.readLine(); //第一行
//            String libType = getValue(fline, "库类型: ");
//            int num; //题目/单词的数量
//            switch (libType) {
//                case "0": // 题库
//                    int quizType = Integer.parseInt(getValue(buffReader.readLine(), "题型: "));
//                    QuizLib qlib = new QuizLib(id, fname, quizType);
//
//                    while ((fline = buffReader.readLine()) != null) { //起始行3
//                        int uid = Integer.parseInt(getValue(buffReader.readLine(), "ID: "));
//                        Quiz q = new Quiz(uid);
//                        //description
//                        String descri = getValue(buffReader.readLine(), "描述: ");
//                        q.setDescription(descri);
//                        //opt
//                        String[] opt = new String[4];
//                        opt[0] = getValue(buffReader.readLine(), "A) ");
//                        opt[1] = getValue(buffReader.readLine(), "B) ");
//                        opt[2] = getValue(buffReader.readLine(), "C) ");
//                        opt[3] = getValue(buffReader.readLine(), "D) ");
//                        q.setOpt(opt);
//                        //answer
//                        String ans = getValue(buffReader.readLine(), "答案: ");
//                        q.setAnswer(ans); //未转化成数字！！
//                        buffReader.readLine(); //跳过空白行
//
//                        qlib.add(q); //加入库,题目数-自增1
//                    }
//                    return qlib;
//
//                case "1": //词库
//                    WordLib wlib = new WordLib(id, fname);
//                    /*...*/
//                    return wlib;
//                default:
//                    System.out.println("不存在此库类型!!");
//            }
//
//            //流关闭:先打开的后关，后打开的先关
//            buffReader.close();
//            reader.close();
//            fin.close();
//
//
//        } catch (Exception e) {
//            System.out.println("读取txt文件出错！！");
//            e.printStackTrace();
//        }
//        return 0;
//
//    }
//
//
//
//
}
