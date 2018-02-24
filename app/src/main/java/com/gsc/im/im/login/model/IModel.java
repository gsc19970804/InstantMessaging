package com.gsc.im.im.login.model;

import com.gsc.im.im.login.bean.LoginBean;
import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * author:Created by WangZhiQiang on 2018/2/21.
 */

public interface IModel {

    //登录
    Observable<LoginBean> onLogin(String phoneNumber, String password, String imagevaildate);

    //获取验证码
    Observable<YZMBean> onGetYZM(String phoneNumber, String type);

    //用户注册
    Observable<ReginsterBean> onRegister(Map<String, String> map);
}
