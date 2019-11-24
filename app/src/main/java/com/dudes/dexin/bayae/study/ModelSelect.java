package com.dudes.dexin.bayae.study;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.dudes.dexin.bayae.R;


public class ModelSelect extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        ListView list = (ListView)findViewById(R.id.list);
//        if(list==null)
//        {
//            android.util.Log.d("debug","list null");
//
//        }
//
//        ModelAdapter adapter = new ModelAdapter(this);
//        adapter.add(new Model("背书模式", "直接看答案"));
//        adapter.add(new Model("选择模式", "选择题"));
//        adapter.add(new Model("填空模式", "填空题"));
//        adapter.add(new Model("混合模式", "二项全能"));
//
//        list.setAdapter(adapter);
    }

    class ModelAdapter extends ArrayAdapter<com.dudes.dexin.bayae.study.ModelSelect.Model> {
        LayoutInflater inflator;

        public ModelAdapter(Context context) {
            super(context, 0);
            inflator = LayoutInflater.from(context);
        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = inflator.inflate(
//                        android.R.layout.simple_list_item_2, parent, false);
//            }
//
//            TextView text1 = (TextView) convertView
//                    .findViewById(android.R.id.text1);
//            TextView text2 = (TextView) convertView
//                    .findViewById(android.R.id.text2);
//
//            com.dudes.dexin.bayae.MainActivity.Model s = this.getItem(position);
//
//            text1.setText(s.name);
//            text2.setText("" + s.description);
//
//            return convertView;
//        }
    }

    class Model {
        String name;
        String description;

        public Model(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
