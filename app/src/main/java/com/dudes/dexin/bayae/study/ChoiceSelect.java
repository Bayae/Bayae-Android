package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.common.SharedPreferencesHelper;
import com.dudes.dexin.bayae.libManagement.LibManager;
import com.dudes.dexin.bayae.model.Quiz;
import com.dudes.dexin.bayae.model.QuizLib;

import java.util.List;

public class ChoiceSelect extends Activity{
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int numRight;
    private int numWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_abcd);

        sharedPreferencesHelper = new SharedPreferencesHelper(ChoiceSelect.this,"Lib");
        LibManager libManager = new LibManager(sharedPreferencesHelper,ChoiceSelect.this);
        QuizLib quizLib = libManager.getQuizLib(libManager.getChosenQuizLib());
        List<Quiz> quizList = quizLib.getMultChoiceQuestions();
        int currentQuizId = initLib(quizList);

        TextView title = (TextView)findViewById(R.id.tv_study_title);
        title.setText(quizList.get(currentQuizId-1).getDescription());

        String[] answer = quizList.get(currentQuizId-1).getAnswer().split("");

        final ListView list = (ListView)findViewById(R.id.abcd_choice);

        final ModelAdapter adapter = new ModelAdapter(this);
        int i = 1;
        for (String opt:quizList.get(currentQuizId-1).getOpt()){
            adapter.add(new ChoiceInList(opt,getDrawableId(i)));
            i++;
        }
        list.setAdapter(adapter);

        ImageButton btn = (ImageButton) findViewById(R.id.ib_study_title_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceSelect.this.finish();
            }
        });

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, 20);
        p.topMargin = 40;
        p.weight = numRight;
        if(numWrong == 0 && numRight == 0) {
            p.weight = 1;
        }
        Button correct_bar = (Button)findViewById(R.id.correct);
        correct_bar.setLayoutParams(p);

        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(0, 20);
        p2.topMargin = 40;
        p2.weight = numWrong;
        if(numWrong == 0 && numRight == 0) {
            p2.weight = 1;
        }
        Button wrong_bar = (Button)findViewById(R.id.wrong);
        wrong_bar.setLayoutParams(p2);

        TextView txRight = (TextView)findViewById(R.id.correct_num);
        TextView txWrong = (TextView)findViewById(R.id.wrong_num);
        txRight.setText("正确"+ numRight);
        txWrong.setText("错误"+numWrong);


        final int[] flag = new int[6];
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout layout = (LinearLayout)list.getChildAt(position);
                ImageView img = (ImageView)layout.findViewById(R.id.choice_img);
                switch (position){
                    case 0:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.a_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.a_unselect);
                        }
                        break;
                    case 1:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.b_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.b_unselect);
                        }
                        break;
                    case 2:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.c_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.c_unselect);
                        }
                        break;
                    case 3:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.d_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.d_unselect);
                        }
                        break;
                    case 4:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.e_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.e_unselect);
                        }
                        break;
                    case 5:
                        if (flag[position] == 0) {
                            img.setImageResource(R.drawable.f_selected);
                        }else if (flag[position] == 1) {
                            img.setImageResource(R.drawable.f_unselect);
                        }
                        break;
                    default:
                        break;
                }
                flag[position] = (flag[position] + 1) % 2;
            }
        });
    }

    private int initLib(List<Quiz> quizzes){
        for (Quiz quiz:quizzes){
            switch(quiz.getStatus()){
                case 0 :
                    return quiz.getId();
                case 1 :
                    numRight++;
                    break;
                case 2 :
                    numWrong++;
                    break;
            }
        }
        return 1;
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
