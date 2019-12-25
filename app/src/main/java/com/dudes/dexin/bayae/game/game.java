package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.study.ModelSelect;

public class game extends Activity {

    String word;
    String meaning;
    TextView text_meaning;
    EditText text_word;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        text_meaning = (TextView)findViewById(R.id.textView2);
        text_word = (EditText) findViewById(R.id.editword);
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game.this.finish();
            }
        });
    }
}
