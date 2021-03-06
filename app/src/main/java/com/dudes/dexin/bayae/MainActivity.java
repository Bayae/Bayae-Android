package com.dudes.dexin.bayae;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.RankList.ResourceList;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.game.game_select;
import com.dudes.dexin.bayae.libUI.LibSelect;
import com.dudes.dexin.bayae.model.ModelInList;
import com.dudes.dexin.bayae.study.ModelSelect;
import com.dudes.dexin.bayae.user.User_Info;

public class MainActivity extends Activity {
    private SharedPreferencesHelper sharedPreferencesHelper;
    public String DEFAULT_WORDLIB = "cet4";
    public String DEFAULT_QUIZLIB = "elc1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //通过用户偏好配置存储默认选用的库的文件名
        sharedPreferencesHelper = new SharedPreferencesHelper(MainActivity.this, "Lib");
        sharedPreferencesHelper.put("ChosenWordLib",DEFAULT_WORDLIB);
        sharedPreferencesHelper.put("ChosenQuizLib",DEFAULT_QUIZLIB);
        sharedPreferencesHelper.put("WordLibList","cet4");
        sharedPreferencesHelper.put("QuizLibList","elc1,elc2");

        //个人信息模块
        ImageButton btn = findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent();
                it.setClass(MainActivity.this, User_Info.class);
                MainActivity.this.startActivity(it);
            }
        });

        //库模块
        ImageButton btn2 = findViewById(R.id.setting_bank);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent();
                it.setClass(MainActivity.this, LibSelect.class);
                MainActivity.this.startActivity(it);
            }
        });

//        //题库调用方式
//        LibManager libManager = new LibManager(sharedPreferencesHelper,MainActivity.this);
//        System.out.println(libManager.getChosenQuizLib());
//        QuizLib quizLib = libManager.getQuizLib(libManager.getChosenQuizLib());
//        System.out.println(quizLib.getFillBlankQuestions().get(0).getAnswer());
//
//        //词库调用方式
//        WordLib wordLib = libManager.getWordLib(libManager.getChosenWordLib());
//        System.out.println(wordLib.getWords().get(0).getMeaning());

        //listview，各大模块入口
        ListView list = (ListView)findViewById(R.id.list_select);

        ModelAdapter adapter = new ModelAdapter(this);
        adapter.add(new ModelInList("开始学习", "背单词、做题",R.drawable.main_study));
        adapter.add(new ModelInList("游戏一刻", "填字",R.drawable.main_game));
        adapter.add(new ModelInList("排行榜", "高手对决",R.drawable.main_rank));
        adapter.add(new ModelInList("记忆曲线", "？？？",R.drawable.main_curve));

        list.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                switch (position){
                    case 0:
                        i = new Intent(MainActivity.this, ModelSelect.class);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, game_select.class);
                        break;
                    case 2:
                        i = new Intent(MainActivity.this, ResourceList.class);
                        break;
                    default:
                        i = new Intent(MainActivity.this,MainActivity.class);
                        break;
                }
                startActivity(i);
            }
        });
    }

    class ModelAdapter extends ArrayAdapter<ModelInList> {
        LayoutInflater inflator;

        public ModelAdapter(Context context) {
            super(context, 0);
            inflator = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflator.inflate(
                        R.layout.model_item, parent, false);
            }
            ImageView img = (ImageView) convertView
                    .findViewById(R.id.img);
            TextView item = convertView
                    .findViewById(R.id.list_item);
            TextView subItem = convertView
                    .findViewById(R.id.list_subItem);


            ModelInList s = this.getItem(position);

            img.setImageDrawable(getResources().getDrawable(s.image));
            item.setText(s.name);
            subItem.setText(s.description);
            return convertView;
        }
    }

}
