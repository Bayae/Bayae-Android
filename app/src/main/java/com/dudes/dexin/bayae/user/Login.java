package com.dudes.dexin.bayae.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText accountText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountText = (EditText) findViewById(R.id.account_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        Button loginBtn = findViewById(R.id.login_btn);
        Button registerpageBtn= findViewById(R.id.register_page);
        loginBtn.setOnClickListener(this);
        registerpageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登陆按钮
            case R.id.login_btn:
                String loginAccount = accountText.getText().toString();
                String loginPassword = passwordText.getText().toString();
                System.out.println(loginAccount);
                System.out.println(loginPassword);
                loginWithOkHttp(loginAccount,loginPassword);
                break;
            //注册按钮
            case R.id.register_page:
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    //实现登陆
    private void loginWithOkHttp(String account, String password) {
        OkHttpUtil.loginWithOkHttp(account, password, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Login","登陆请求失败");
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
                        if (responseData.equals("200")) {
                            Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            intent.putExtra("login","登陆成功");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this,"登陆失败",Toast.LENGTH_SHORT).show();
                        }
                        System.out.println(responseData);
                    }
                });
            }
        });
    }


}
