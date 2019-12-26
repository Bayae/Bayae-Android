package com.dudes.dexin.bayae.model;

import android.widget.Button;

public class LibInList {
    public String name;
    public Boolean chosen; //选中

    public LibInList(String name){ //初始化
        this.name = name;
        this.chosen = false; //默认未选中
    }
}
