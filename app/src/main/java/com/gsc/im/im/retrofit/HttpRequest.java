package com.gsc.im.im.retrofit;

import com.gsc.im.im.login.bean.LoginBean;
import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author:Created by WangZhiQiang on 2018/2/21.
 */

public interface HttpRequest {

    //用户登录
    @GET("instantMessaging/user/login")
    Observable<LoginBean> onLogin(@Query("phoneNumber") String phoneNumber, @Query("password") String password, @Query("imagevaildate") String imagevaildate);

    //获取验证码
    @GET("instantMessaging/user/smsVerification")
    Observable<YZMBean> onGetYZM(@Query("phoneNumber") String phoneNumber, @Query("type") String type);

    //用户注册
    @FormUrlEncoded
    @POST("instantMessaging/user/register")
    Observable<ReginsterBean> onRegister(@FieldMap Map<String, String> map);
}
