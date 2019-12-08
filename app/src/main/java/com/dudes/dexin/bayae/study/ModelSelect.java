package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;


public class ModelSelect extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_select);

        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("模式选择");

        ListView list = (ListView)findViewById(R.id.list_select);

        ModelAdapter adapter = new ModelAdapter(this);
        adapter.add(new Model("背书模式", "直接看答案",R.drawable.model_recite));
        adapter.add(new Model("选择模式", "选择题",R.drawable.model_multiple_choice));
        adapter.add(new Model("填空模式", "填空题",R.drawable.model_fill_blank));
        adapter.add(new Model("混合模式", "二项全能",R.drawable.model_mix));

        list.setAdapter(adapter);
    }

    class ModelAdapter extends ArrayAdapter<com.dudes.dexin.bayae.study.ModelSelect.Model> {
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


            com.dudes.dexin.bayae.study.ModelSelect.Model s = this.getItem(position);

            img.setImageDrawable(getResources().getDrawable(s.image));
            item.setText(s.name);
            subItem.setText(s.description);
            return convertView;
        }
    }

    class Model {
        String name;
        String description;
        int image;

        public Model(String name, String description, int image) {
            this.name = name;
            this.description = description;
            this.image = image;
        }
    }
}
