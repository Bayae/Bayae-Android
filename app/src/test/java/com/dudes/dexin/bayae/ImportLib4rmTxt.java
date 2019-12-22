package com.dudes.dexin.bayae;

import com.dudes.dexin.bayae.common.Libs4rmTxt;
import com.dudes.dexin.bayae.common.model.QuizLib;

import org.junit.Test;

public class ImportLib4rmTxt {
    @Test
    public void test(){
        /*声明*/
        //QuizLib qlib = new QuizLib(1, )


        /*读取txt*/
        String encoding = "UTF-8";
        //String fpath = "/Users/Mac/Documents/Bayae/Bayae-Android/app/src/main/libs/题库.txt";
        String dPath = "/Users/Mac/Documents/Bayae/Bayae-Android/app/src/main/libs/";

        Libs4rmTxt libs4rmTxt = new Libs4rmTxt(dPath, encoding);
        //libs4rmTxt.importLibs4rmTxt(fpath, encoding);

    }

}
