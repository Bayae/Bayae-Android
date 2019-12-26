package com.dudes.dexin.bayae.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    private EditText rg_email;
    private EditText rg_password;
    private EditText rg_username;
    private EditText rg_Cf_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rg_email = (EditText) findViewById(R.id.rg_email_text);
        rg_password = (EditText) findViewById(R.id.rg_password_text);
        rg_username =(EditText)findViewById(R.id.rg_username_text);
        rg_Cf_password=(EditText)findViewById(R.id.rg_Cf_password_text);
        Button Register_btn = (Button) findViewById(R.id.rg_button);
        Register_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String registerAccount = rg_email.getText().toString();
                String registerPassword = rg_password.getText().toString();
                String registernickname = rg_username.getText().toString();
                String confirmPassword = rg_Cf_password.getText().toString();

                if (TextUtils.isEmpty(registerPassword)||TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Register.this,"密码和确认密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    if (registerPassword.equals(confirmPassword)){
                        registerWithOkHttp(registerAccount,registerPassword,registernickname);
                    }else {
                        Toast.makeText(Register.this,"两次密码不同请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        ToggleButton togglePwd = (ToggleButton) findViewById(R.id.togglePwd);
        togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    rg_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    rg_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        ToggleButton toggleConfirmPwd = (ToggleButton) findViewById(R.id.toggleConfirmPwd);
        toggleConfirmPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    rg_Cf_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    rg_Cf_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
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
