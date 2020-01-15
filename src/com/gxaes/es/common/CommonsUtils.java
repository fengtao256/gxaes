package com.gxaes.es.common;

import java.util.Random;

public class CommonsUtils {
    public static String getCard(){
        Random rand=new Random();//生成随机数
        String cardNnumer="";
        for(int a=0;a<9;a++){
            cardNnumer+=rand.nextInt(10);//生成9位随机数字
        }
        return cardNnumer;
    }
}
