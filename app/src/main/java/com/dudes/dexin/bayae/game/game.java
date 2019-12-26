package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.libManagement.LibManager;
import com.dudes.dexin.bayae.model.QuizLib;
import com.dudes.dexin.bayae.model.Word;
import com.dudes.dexin.bayae.model.WordLib;
import com.dudes.dexin.bayae.study.ModelSelect;

public class game extends Activity {
    private SharedPreferencesHelper sharedPreferencesHelper;
    public String DEFAULT_WORDLIB = "wordlib";
    public String word;
    //输入框中的内容
    public String getword;
    public String meaning;
    public int hasdone;
    public int chance;
    public int wordright;
    public int wordall;
    protected TextView text_meaning;
    protected EditText text_word;
    protected ProgressBar progressBar;
    protected TextView rest_chance;
    protected TextView all;
    protected TextView right;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        text_meaning = (TextView)findViewById(R.id.textView2);
        text_word = (EditText) findViewById(R.id.editword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        rest_chance = (TextView)findViewById(R.id.textView5);
        right = (TextView)findViewById(R.id.finish_num);
        all = (TextView)findViewById(R.id.textView4);
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        init();
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game.this.finish();
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
                }else{
                    //失去焦点时的处理内容
                    getword = text_word.getText().toString();
                    System.out.println(getword);
                    //与获得的单词比较
                    if(getword.compareTo(word)==0){
                        text_word.setTextColor(Color.GREEN);
                    }
                    else{
                        text_word.setTextColor(Color.RED);
                    }
                }
            }
        });
    }
    private void init(){
        this.chance = 5;
        this.hasdone = 0;
        this.wordright = 0;
        this.wordall = 10;
        //通过用户偏好配置存储默认选用的库的文件名
        sharedPreferencesHelper = new SharedPreferencesHelper(game.this, "chosenLib");
        sharedPreferencesHelper.put("WordLib",DEFAULT_WORDLIB);
        sharedPreferencesHelper.put("WordLibList","");
        //题库调用方式
        LibManager libManager = new LibManager(sharedPreferencesHelper,game.this);
        //System.out.println(libManager.getChosenWordLib());
        WordLib wordLib = libManager.getWordLib(libManager.getChosenWordLib());
        //System.out.println(wordLib.getWords().get(0).getMeaning());
        this.meaning = wordLib.getWords().get(0).getMeaning();
        this.word = wordLib.getWords().get(0).getWord();
        text_meaning.setText(meaning);
        //界面控件初始化
        rest_chance.setText(Integer.toString(chance));
        right.setText(Integer.toString(wordright));
        all.setText(Integer.toString(wordall));
    }

}
