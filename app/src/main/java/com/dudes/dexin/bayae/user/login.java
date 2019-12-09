package com.dudes.dexin.bayae.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1=findViewById(R.id.login_btn);
        btn1.setOnClickListener(listener);
    }
    Button.OnClickListener listener=new Button.OnClickListener(){
        public void onClick(Viewv){
            Intent intent =new Intent(login.this,MainActivity.class);
            startActivity(intent);
            login.this.finish();
        }
    }
}
