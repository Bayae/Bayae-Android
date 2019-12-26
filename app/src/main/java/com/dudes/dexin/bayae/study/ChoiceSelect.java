package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
    private boolean enable_choose = true;
    private boolean enable_check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_abcd);

        sharedPreferencesHelper = new SharedPreferencesHelper(ChoiceSelect.this,"Lib");
        LibManager libManager = new LibManager(sharedPreferencesHelper,ChoiceSelect.this);
        QuizLib quizLib = libManager.getQuizLib(libManager.getChosenQuizLib());
        final List<Quiz> quizList = quizLib.getMultChoiceQuestions();
        final int currentQuizId = initLib(quizList);

        TextView title = (TextView)findViewById(R.id.tv_study_title);
        title.setText(quizList.get(currentQuizId-1).getDescription());

        String answer_String = quizList.get(currentQuizId-1).getAnswer();
        final String[] answer = answer_String.split("");

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
        txRight.setText("正确"+numRight);
        txWrong.setText("错误"+numWrong);

        final int[] flag = new int[quizList.get(currentQuizId-1).getOpt().size()];
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (enable_choose) {
                    LinearLayout layout = (LinearLayout) list.getChildAt(position);
                    ImageView img = (ImageView) layout.findViewById(R.id.choice_img);
                    switch (position) {
                        case 0:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.a_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.a_unselect);
                            }
                            break;
                        case 1:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.b_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.b_unselect);
                            }
                            break;
                        case 2:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.c_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.c_unselect);
                            }
                            break;
                        case 3:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.d_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.d_unselect);
                            }
                            break;
                        case 4:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.e_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.e_unselect);
                            }
                            break;
                        case 5:
                            if (flag[position] == 0) {
                                img.setImageResource(R.drawable.f_selected);
                            } else if (flag[position] == 1) {
                                img.setImageResource(R.drawable.f_unselect);
                            }
                            break;
                        default:
                            break;
                    }
                    flag[position] = (flag[position] + 1) % 2;
                }
            }
        });

        final Button submit = (Button)findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enable_check){
                    enable_check = false;
                    enable_choose = false;
                    submit.setTextColor(Color.parseColor("#BDBDBD"));
                    int[] ans = new int[flag.length];
                    for (int i = 0; i < answer.length - 1; i++) {
                        System.out.println(Integer.parseInt(answer[i + 1]));
                        int index = Integer.parseInt(answer[i + 1]) - 1;
                        ans[index] = 1;
                    }
                    boolean flag_allcorrect = true;
                    for (int i = 0; i < flag.length; i++) {
                        if (flag[i] != ans[i]) {
                            flag_allcorrect = false;
                            break;
                        }
                    }
                    if (flag_allcorrect) numRight++; else numWrong++;
                    for (int i = 0; i < flag.length; i++) {
                        LinearLayout layout = (LinearLayout)list.getChildAt(i);
                        ImageView img = (ImageView)layout.findViewById(R.id.choice_img);
                        switch (i) {
                            case 0:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.a_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.a_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.a_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.a_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.a_wrong);
                                }
                                break;
                            case 1:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.b_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.b_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.b_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.b_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.b_wrong);
                                }
                                break;
                            case 2:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.c_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.c_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.c_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.c_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.c_wrong);
                                }
                                break;
                            case 3:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.d_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.d_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.d_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.d_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.d_wrong);
                                }
                                break;
                            case 4:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.e_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.e_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.e_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.e_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.e_wrong);
                                }
                                break;
                            case 5:
                                if (ans[i] == 0 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.f_unselect);
                                }
                                if (ans[i] == 1 && flag[i] == 0) {
                                    img.setImageResource(R.drawable.f_correct);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && !flag_allcorrect) {
                                    img.setImageResource(R.drawable.f_half);
                                }
                                if (ans[i] == 1 && flag[i] == 1 && flag_allcorrect) {
                                    img.setImageResource(R.drawable.f_correct);
                                }
                                if (ans[i] == 0 && flag[i] == 1) {
                                    img.setImageResource(R.drawable.f_wrong);
                                }
                                break;
                            default:
                                break;
                        }
                    }
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
                    txRight.setText("正确"+numRight);
                    txWrong.setText("错误"+numWrong);
                }
            }
        });

        final Button btnNext = (Button)findViewById(R.id.button_next);
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(currentQuizId<quizLib.getNumofMCQ()){
                    currentQuizId++;
                    title.setText(quizList.get(currentQuizId-1).getDescription());
                    adapter = new ModelAdapter(ChoiceSelect.this);
                    int i = 1;
                    for (String opt:quizList.get(currentQuizId-1).getOpt()){
                        adapter.add(new ChoiceInList(opt,getDrawableId(i)));
                        i++;
                    }
                    list.setAdapter(adapter);
                }
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
