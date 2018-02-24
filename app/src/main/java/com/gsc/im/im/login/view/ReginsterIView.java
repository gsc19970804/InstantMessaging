package com.gsc.im.im.login.view;

import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;

/**
 * author:Created by WangZhiQiang on 2018/2/23.
 */

public interface ReginsterIView {

    //获取验证码
    void onGetYZM(YZMBean yzmBean);

    //用户注册
    void onRegister(ReginsterBean reginsterBean);

}
