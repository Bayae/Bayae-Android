package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    String word;
    String meaning;
    int hasdone;
    int chance;
    TextView text_meaning;
    EditText text_word;
    ProgressBar progressBar;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        text_meaning = (TextView)findViewById(R.id.textView2);
        text_word = (EditText) findViewById(R.id.editword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game.this.finish();
            }
        });
        init();
    }
    private void init(){
        this.chance = 0;
        this.hasdone = 0;
        //通过用户偏好配置存储默认选用的库的文件名
        sharedPreferencesHelper = new SharedPreferencesHelper(game.this, "chosenLib");
        sharedPreferencesHelper.put("WordLib",DEFAULT_WORDLIB);
        sharedPreferencesHelper.put("WordLibList","");
        //题库调用方式
        LibManager libManager = new LibManager(sharedPreferencesHelper,game.this);
        //System.out.println(libManager.getChosenWordLib());
        WordLib wordLib = libManager.getWordLib(libManager.getChosenWordLib());
        //System.out.println(wordLib.getWords().get(0).getMeaning());
        meaning = wordLib.getWords().get(0).getMeaning();
        word = wordLib.getWords().get(0).getWord();
        text_meaning.setText(meaning);
    }
}
