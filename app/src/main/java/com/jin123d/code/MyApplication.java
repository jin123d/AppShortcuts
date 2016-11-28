package com.jin123d.code;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by hekr_jds on 11/11 0011.
 **/

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);
        OkGo.getInstance().debug("OkGo")
                .setConnectTimeout(10 * 1000)  //全局的连接超时时间
                .setReadTimeOut(10 * 1000)     //全局的读取超时时间
                .setWriteTimeOut(10 * 1000);    //全局的写入超时时间
    }
}
