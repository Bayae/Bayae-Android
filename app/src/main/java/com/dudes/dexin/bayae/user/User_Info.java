package com.dudes.dexin.bayae.user;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.dudes.dexin.bayae.MainActivity;
import com.dudes.dexin.bayae.R;
import com.dudes.dexin.bayae.study.ModelSelect;

public class User_Info extends AppCompatActivity {

    private ListView mListView;
    private String [] data = {"学习设置","修改资料","打卡日历","检查更新", "退出登录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("个人信息");
        ImageButton btn = findViewById(R.id.ib_title_back);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent();
                it.setClass(User_Info.this, MainActivity.class);
                User_Info.this.startActivity(it);
            }
        });

        mListView = (ListView) findViewById(R.id.list_select1);
        ArrayAdapter<String> array = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,data);

        //将数据和布局 显示到列表
        mListView.setAdapter(array);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(User_Info.this,User_Info.class);
                switch (position){
                    case 0:
                        i = new Intent(User_Info.this, Login.class);
                        break;
                    case 1:
                        break;
                    default:
                        i = new Intent(User_Info.this,User_Info.class);
                        break;
                    case 4:
                        i = new Intent(User_Info.this, Login.class);
                        break;
                }
                startActivity(i);
            }
        });

    }
}
