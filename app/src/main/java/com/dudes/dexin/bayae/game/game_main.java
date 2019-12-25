package com.dudes.dexin.bayae.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.game.game;
import com.dudes.dexin.bayae.study.ModelSelect;

public class game_main extends Activity{
    private int p=0;
    private ProgressBar game_progress;
    private Myhandle myhandle = new Myhandle();
    protected class Myhandle extends Handler{
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            int code = msg.what;
            switch (code){
                case 1:
                    p+=10;
                    game_progress.setProgress(p);
                    break;
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_one);
        ImageButton btn = (ImageButton) findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game_main.this.finish();
            }
        });
        game_progress = (ProgressBar)findViewById(R.id.progressBar);
        if (p==0){
            new mythread().start();
        }
    }

    public class mythread extends Thread{
        @Override
        public void run(){
            super.run();
            while (true){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (p==100){
                    p=0;
                    Intent intent = new Intent(game_main.this,game.class);
                    startActivity(intent);
                    break;
                }
                Message msg = new Message();
                msg.what=1;
                myhandle.sendMessage(msg);
            }
        }
    }
}
