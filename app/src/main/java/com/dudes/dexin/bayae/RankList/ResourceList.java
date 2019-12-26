package com.dudes.dexin.bayae.RankList;

import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

import com.dudes.dexin.bayae.R;


public class ResourceList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton6);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            { ((ImageButton) v).setBackgroundResource(R.drawable.fullheart); }
        });
    }






















}

