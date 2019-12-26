package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.study.ChoiceInList;
import com.dudes.dexin.bayae.R;

public class ChoiceSelect extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_abcd);

        TextView title = (TextView)findViewById(R.id.tv_study_title);
        title.setText("要考试了，很想知道：背也能不能按时完成？");

        ListView list = (ListView)findViewById(R.id.abcd_choice);

        ModelAdapter adapter = new ModelAdapter(this);
        adapter.add(new ChoiceInList("做不完", R.drawable.a_unselect));
        adapter.add(new ChoiceInList("肯定做不完", R.drawable.b_selected));
        adapter.add(new ChoiceInList("绝对做不完", R.drawable.c_correct));
        adapter.add(new ChoiceInList("做鬼得完", R.drawable.d_wrong));
        adapter.add(new ChoiceInList("做得完我跟李大爷姓", R.drawable.e_half));
        adapter.add(new ChoiceInList("哔——————————（爆粗消音9999字）" +
                "软工屁事真的多，实验又难、项目又难、还TM有考试，让不让人活了！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！让不让人活了让不让人活了让不让人活了让不让人活了让不让人活了", R.drawable.f_unselect));

        list.setAdapter(adapter);

        ImageButton btn = (ImageButton) findViewById(R.id.ib_study_title_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceSelect.this.finish();
            }
        });
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



































