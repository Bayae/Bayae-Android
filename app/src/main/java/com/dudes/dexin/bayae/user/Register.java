package com.dudes.dexin.bayae.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.common.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    private EditText rg_email;
    private EditText rg_password;
    private EditText rg_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rg_email = (EditText) findViewById(R.id.rg_email_text);
        rg_password = (EditText) findViewById(R.id.rg_password_text);
        rg_username =(EditText)findViewById(R.id.rg_username_text);
        Button Register_btn = (Button) findViewById(R.id.rg_button);
        Register_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String registerAccount = rg_email.getText().toString();
                String registerPassword = rg_password.getText().toString();
                String registernickname = rg_username.getText().toString();

                registerWithOkHttp(registerAccount,registerPassword,registernickname);
            }
        });



    }
    private void registerWithOkHttp(String account,String password,String username) {
        OkHttpUtil.registerWithOkHttp(account, password, username, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Register","注册请求失败");
            }

            //请求成功，获取服务器返回数据
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取返回内容
                final String responseData = response.body().string();
                //在主线程更新ui和响应用户操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseData.contains("\"code\":200,\"msg\":\"ok\"")) {
                            Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,MainActivity.class);
                            intent.putExtra("login","注册成功");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this,"注册失败",Toast.LENGTH_SHORT).show();
                            System.out.println(responseData);
                        }
                    }
                });
            }
        });
    }

}
