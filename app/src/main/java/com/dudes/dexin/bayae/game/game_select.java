package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.study.ModelSelect;

public class game_select extends Activity{
    RelativeLayout game_select;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_select);
        game_select = (RelativeLayout)findViewById(R.id.game_one);
        game_select.setOnClickListener(new clicklistener());
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game_select.this.finish();
            }
        });
    }
    class clicklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(game_select.this, game_main.class);
            startActivity(intent);
        }
    }
}
