package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.libManagement.LibManager;
import com.dudes.dexin.bayae.model.WordLib;


public class game extends Activity {
    private SharedPreferencesHelper sharedPreferencesHelper;

    public String word;
    public String meaning;
    //输入框中的内容
    public String getword;
    public int hasdone;
    public int chance;
    public int wordright;
    public int wordall;
    public int progress;
    public WordLib wordLib;
    //题目id
    public int word_id;
    protected TextView text_meaning;
    protected EditText text_word;
    protected ProgressBar progressBar;
    protected TextView rest_chance;
    protected TextView all;
    protected TextView right;
    protected Button next_word;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        text_meaning = (TextView)findViewById(R.id.textView2);
        text_word = (EditText) findViewById(R.id.editword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        rest_chance = (TextView)findViewById(R.id.textView5);
        right = (TextView)findViewById(R.id.finish_num);
        all = (TextView)findViewById(R.id.textView4);
        next_word = (Button) findViewById(R.id.game_next);
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        init();
        //返回键
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(game.this,game_select.class);
                startActivity(intent);
            }
        });
        //下一题
        next_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wordLib.getWords().get(word_id).getDone()==1&&word_id < wordLib.getNum()){
                    word_id += 1;
                    meaning = wordLib.getWords().get(word_id).getMeaning();
                    word = wordLib.getWords().get(word_id).getWord();
                    text_meaning.setText(meaning);
                    text_word.setText("");
                }
            }
        });
        //设置edit键盘监听器
        text_word.setOnKeyListener(new android.view.View.OnKeyListener(){

            @Override

            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                //回车清除焦点
                if (keycode == keyEvent.KEYCODE_ENTER) {
                    text_word.clearFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(text_word.getWindowToken(),0);
                    return true;
                } else {
                    return false;
                }
            }
        });
        //设置焦点监听器
        text_word.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    //得到焦点时的处理内容
                    System.out.println("get Focus");
                    text_word.setTextColor(Color.rgb(0,0,0));
                }else{
                    //失去焦点时的处理内容
                    getword = text_word.getText().toString();
                    System.out.println(getword);
                    //与获得的单词比较
                    if(getword.compareTo(word)==0){
                        if(wordLib.getWords().get(word_id).getDone()!=1){
                            //放置做过标志
                            wordLib.getWords().get(word_id).setDone(1);
                            text_word.setTextColor(Color.GREEN);
                            wordright += 1;
                            progress += 1;
                            right.setText(Integer.toString(wordright));
                            progressBar.setProgress(progress);
                            onResume();
                        }
                    }
                    else{
                        if(wordLib.getWords().get(word_id).getDone()!=1) {
                            text_word.setTextColor(Color.RED);
                            chance -= 1;
                            rest_chance.setText(Integer.toString(chance));
                            //弹窗结束游戏
                            onResume();
                        }
                    }
                }
            }
        });

    }
    public void onResume(){
        super.onResume();
        if(progress == progressBar.getMax()){
            //全部完成结束游戏
            // 获取view
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(game.this);
            //设置弹窗内容
            builder.setIcon(R.drawable.fighter);
            builder.setTitle("胜利");
            builder.setView(R.layout.game_successful);
            //设置按钮内容
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(game.this, game_select.class);
                    startActivity(intent);
                }
            });
            builder.setCancelable(true);
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(chance==0){
            //全部完成结束游戏
            // 获取view
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(game.this);
            //设置弹窗内容
            builder.setIcon(R.drawable.fighter);
            builder.setTitle("失败");
            builder.setView(R.layout.game_lose);
            //设置按钮内容
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(game.this,game_select.class);
                    startActivity(intent);
                }
            });
            builder.setCancelable(true);
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    private void init(){
        this.chance = 5;
        this.hasdone = 0;
        this.wordright = 0;
        this.word_id = 0;
        this.progress = 0;
        //通过用户偏好配置存储默认选用的库的文件名
        sharedPreferencesHelper = new SharedPreferencesHelper(game.this, "Lib");
        //题库调用方式
        LibManager libManager = new LibManager(sharedPreferencesHelper,game.this);
        //System.out.println(libManager.getChosenWordLib());
        this.wordLib = libManager.getWordLib(libManager.getChosenWordLib());
        //System.out.println(wordLib.getWords().get(0).getMeaning());
        this.meaning = wordLib.getWords().get(word_id).getMeaning();
        this.word = wordLib.getWords().get(word_id).getWord();
        text_meaning.setText(meaning);
        this.wordall = wordLib.getNum();
        progressBar.setMax(wordall);
        //界面控件初始化
        rest_chance.setText(Integer.toString(chance));
        right.setText(Integer.toString(wordright));
        all.setText(Integer.toString(wordall));
    }

}
