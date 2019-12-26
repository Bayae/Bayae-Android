package com.dudes.dexin.bayae.libManagement;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.model.QuizLib;
import com.dudes.dexin.bayae.model.WordLib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class LibManager {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Activity context;

    public LibManager(SharedPreferencesHelper sharedPreferencesHelper,Activity context){
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.context = context;
    }

    //修改选取词库
    public void changeChosenQuizLib(String quizLib){
        sharedPreferencesHelper.update("ChosenQuizLib",quizLib);
    }

    //修改选取题库
    public void changeChosenWordLib(String wordLib){
        sharedPreferencesHelper.update("ChosenWordLib",wordLib);
    }

    //获得当前选择的题库的名字
    public String getChosenQuizLib(){
        return sharedPreferencesHelper.getSharedPreference("ChosenQuizLib","").toString();
    }

    //通过用户偏好配置获得当前选取的词库的名字
    public String getChosenWordLib(){
        return sharedPreferencesHelper.getSharedPreference("ChosenWordLib","").toString();
    }

    //获得词库列表
    public List<String> getWordLibNameList(){
        String listString = sharedPreferencesHelper.getSharedPreference("WordLibList","").toString();
        return Arrays.asList(listString.split(","));
    }

    //获得词库列表
    public List<String> getQuizLibNameList(){
        String listString = sharedPreferencesHelper.getSharedPreference("QuizLibList","").toString();
        return Arrays.asList(listString.split(","));
    }

    //获得题库
    public QuizLib getQuizLib(String libName){
        String qLibObjectToJson = readFileFromRaw(context, getResource("raw", libName));
        QuizLib qLibJsonToObject = JSON.parseObject(qLibObjectToJson,new TypeReference<QuizLib>(){});
        return qLibJsonToObject;
    }

    //获得词库
    public WordLib getWordLib(String libName){
        String qLibObjectToJson = readFileFromRaw(context, getResource("raw", libName));
        WordLib qLibJsonToObject = JSON.parseObject(qLibObjectToJson,new TypeReference<WordLib>(){});
        return qLibJsonToObject;
    }

    public static String readFileFromRaw(Activity context, int rawName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().openRawResource(rawName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //获得资源的资源号
    public int getResource(String defType,String sourceName){
        Context ctx = context.getBaseContext();
        int resId = context.getResources().getIdentifier(sourceName, defType, ctx.getPackageName());
        //如果没有在"mipmap"下找到imageName,将会返回0
        return resId;
    }
}
