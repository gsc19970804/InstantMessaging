package com.gsc.im.im.login.model;

import com.gsc.im.im.login.bean.LoginBean;
import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;
import com.gsc.im.im.retrofit.HttpRequest;
import com.gsc.im.im.retrofit.RetrofitMannger;

import java.util.Map;

import io.reactivex.Observable;

/**
 * author:Created by WangZhiQiang on 2018/2/21.
 */

public class Model implements IModel {
    //登入
    @Override
    public Observable<LoginBean> onLogin(String phoneNumber, String password, String imagevaildate) {
        return RetrofitMannger.newInstance().mcreat(HttpRequest.class).onLogin(phoneNumber, password, imagevaildate);
    }

    //获取验证码
    @Override
    public Observable<YZMBean> onGetYZM(String phoneNumber, String type) {
        return RetrofitMannger.newInstance().mcreat(HttpRequest.class).onGetYZM(phoneNumber, type);
    }


    //用户注册
    @Override
    public Observable<ReginsterBean> onRegister(Map<String, String> map) {
        return RetrofitMannger.newInstance().mcreat(HttpRequest.class).onRegister(map);

    }
}
