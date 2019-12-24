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

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.model.ModelInList;


public class ModelSelect extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_select);

        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("模式选择");

        ListView list = (ListView)findViewById(R.id.list_select);

        ModelAdapter adapter = new ModelAdapter(this);
        adapter.add(new ModelInList("背书模式", "直接看答案",R.drawable.model_recite));
        adapter.add(new ModelInList("选择模式", "选择题",R.drawable.model_multiple_choice));
        adapter.add(new ModelInList("填空模式", "填空题",R.drawable.model_fill_blank));
        adapter.add(new ModelInList("混合模式", "二项全能",R.drawable.model_mix));

        list.setAdapter(adapter);

        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ModelSelect.this.finish();
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
