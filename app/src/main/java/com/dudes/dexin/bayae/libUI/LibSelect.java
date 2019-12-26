package com.dudes.dexin.bayae.libUI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.dudes.dexin.bayae.model.LibInList;
import com.dudes.dexin.bayae.R;


public class LibSelect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_main);

        TextView title = (TextView)findViewById(R.id.lib_title);
        title.setText("设置题库");

        ListView list = (ListView)findViewById(R.id.lib_item);

        LibSelect.LibAdapter adapter = new LibSelect.LibAdapter(this);
        adapter.add(new LibInList("cet4"));
        adapter.add(new LibInList("elc1"));
        adapter.add(new LibInList("elc2"));

        list.setAdapter(adapter); //加入List

        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back); //返回键
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LibSelect.this.finish();
            }
        });


    }

    class LibAdapter extends ArrayAdapter<LibInList> {
        LayoutInflater inflator;

        public LibAdapter(Context context) {
            super(context, 0);  //存疑
            inflator = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflator.inflate(
                        R.layout.lib_item, parent, false);
            }
            TextView item = convertView
                    .findViewById(R.id.lib_name);
            Switch button = convertView
                    .findViewById(R.id.lib_switch);


            LibInList lib = this.getItem(position);

            item.setText(lib.name);
            return convertView;
        }
    }



 }

