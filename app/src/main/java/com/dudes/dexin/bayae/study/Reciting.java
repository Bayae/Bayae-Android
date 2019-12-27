package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.libManagement.LibManager;
import com.dudes.dexin.bayae.model.Quiz;
import com.dudes.dexin.bayae.model.QuizLib;

import java.util.Arrays;
import java.util.List;

public class Reciting extends Activity {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int currentQuizId = 1;//当前题号
    private ModelAdapter adapter;
    private TextView title;//题目问题
    List<String> answer;//答案
    QuizLib quizLib;//题库
    List<Quiz> quizList;//题目列表
    ListView list;//选项
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_reciting);

        sharedPreferencesHelper = new SharedPreferencesHelper(Reciting.this,"Lib");
        LibManager libManager = new LibManager(sharedPreferencesHelper,Reciting.this);
        quizLib = libManager.getQuizLib(libManager.getChosenQuizLib());
        quizList = quizLib.getMultChoiceQuestions();
        for(Quiz quiz:quizLib.getFillBlankQuestions()){
            quizList.add(quiz);
        }

        reflash();


    }

    private void reflash(){
        //题目
        title = (TextView)findViewById(R.id.tv_study_title);
        title.setText(quizList.get(currentQuizId-1).getDescription());


        //答案
        String answer_String = quizList.get(currentQuizId-1).getAnswer();
        answer = Arrays.asList(answer_String.split(""));

        //选项
        list = (ListView)findViewById(R.id.abcd_choice);

        //list填充
        adapter = new ModelAdapter(this);
        int i = 1;
        for (String opt:quizList.get(currentQuizId-1).getOpt()){
            if (answer.contains(i+"")) {
                adapter.add(new ChoiceInList(opt, getDrawableIdForRightAnswer(i)));
            }else {
                adapter.add(new ChoiceInList(opt, getDrawableId(i)));
            }
            i++;
        }
        list.setAdapter(adapter);

        //返回按钮
        btn = (ImageButton) findViewById(R.id.ib_study_title_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reciting.this.finish();
            }
        });

        final Button btnNext = (Button)findViewById(R.id.button_next);
        final Button btnLast = (Button)findViewById(R.id.button_previous);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuizId < quizLib.getNumofMCQ() + quizLib.getNumofFBQ()){
                    currentQuizId++;
                    reflash();
                    btnLast.setTextColor(Color.parseColor("#000000"));
                }else {
                    btnNext.setTextColor(Color.parseColor("#BDBDBD"));
                }
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuizId > 1){
                    currentQuizId--;
                    reflash();
                    btnNext.setTextColor(Color.parseColor("#000000"));
                }else {
                    btnLast.setTextColor(Color.parseColor("#BDBDBD"));
                }
            }
        });

    }

    private int getDrawableId(int order){
        switch (order){
            case 1:
                return R.drawable.a_unselect;
            case 2:
                return R.drawable.b_unselect;
            case 3:
                return R.drawable.c_unselect;
            case 4:
                return R.drawable.d_unselect;
            case 5:
                return R.drawable.e_unselect;
            case 6:
                return R.drawable.f_unselect;
        }
        return 0;
    }

    private int getDrawableIdForRightAnswer(int order){
        switch (order){
            case 1:
                return R.drawable.a_correct;
            case 2:
                return R.drawable.b_correct;
            case 3:
                return R.drawable.c_correct;
            case 4:
                return R.drawable.d_correct;
            case 5:
                return R.drawable.e_correct;
            case 6:
                return R.drawable.f_correct;
        }
        return 0;
    }

    class ModelAdapter extends ArrayAdapter<ChoiceInList> {
        LayoutInflater inflater;

        public ModelAdapter(Context context) {
            super(context, 0);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(
                        R.layout.choice_item, parent, false
                );
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.choice_img);
            TextView item = convertView.findViewById(R.id.choice_list_item);

            ChoiceInList s = this.getItem(position);

            img.setImageDrawable(getResources().getDrawable(s.image));
            item.setText(s.description);
            return convertView;
        }
    }
}
