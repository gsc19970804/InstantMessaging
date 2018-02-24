package com.gsc.im.im.login.presenter;

import com.gsc.im.im.login.bean.LoginBean;
import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;
import com.gsc.im.im.login.model.Model;
import com.gsc.im.im.login.view.LoginIView;
import com.gsc.im.im.login.view.ReginsterIView;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author:Created by WangZhiQiang on 2018/2/21.
 */

public class Passer<T> {
    private T view;
    private Model model;

    public Passer(T view) {
        this.view = view;
        model = new Model();
    }

    //登录
    public void onLogin(String phoneNumber, String password, String imagevaildate) {
        Observable<LoginBean> loginBeanObservable = model.onLogin(phoneNumber, password, imagevaildate);
        loginBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        LoginIView loginIView = (LoginIView) Passer.this.view;
                        if (loginBean != null) {
                            loginIView.onLogin(loginBean);
                        }
                    }
                });
    }


    //获取验证码
    public void onGetYZM(String phoneNumber, String type) {
        Observable<YZMBean> yzmBeanObservable = model.onGetYZM(phoneNumber, type);
        yzmBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YZMBean>() {
                    @Override
                    public void accept(YZMBean yzmBean) throws Exception {
                        ReginsterIView reginsterIView = (ReginsterIView) Passer.this.view;
                        if (reginsterIView != null) {
                            reginsterIView.onGetYZM(yzmBean);
                        }
                    }
                });
    }

    //用户注册
    public void onRegister(Map<String, String> map) {
        Observable<ReginsterBean> reginsterBeanObservable = model.onRegister(map);
        reginsterBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReginsterBean>() {
                    @Override
                    public void accept(ReginsterBean reginsterBean) throws Exception {
                        ReginsterIView reginsterIView = (ReginsterIView) Passer.this.view;
                        if (reginsterIView != null) {
                            reginsterIView.onRegister(reginsterBean);
                        }
                    }
                });
    }


    //解绑视图
    public void onDestroy() {
        if (view != null) {
            view = null;
        }
    }

}
