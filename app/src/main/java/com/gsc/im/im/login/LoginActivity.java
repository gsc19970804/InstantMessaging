package com.gsc.im.im.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gsc.im.im.R;
import com.gsc.im.im.login.bean.LoginBean;
import com.gsc.im.im.login.presenter.Passer;
import com.gsc.im.im.login.view.LoginIView;
import com.gsc.im.im.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:Created by WangZhiQiang on 2018/2/22.
 */

public class LoginActivity extends AppCompatActivity implements LoginIView {

    @Bind(R.id.loginphone)
    EditText loginphone;
    @Bind(R.id.loginpassword)
    EditText loginpassword;
    @Bind(R.id.forgetpassword)
    TextView forgetpassword;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.userlogin)
    Button userlogin;
    @Bind(R.id.yzmimg)
    ImageView yzmimg;
    @Bind(R.id.loginyzm)
    EditText loginyzm;
    private String updateTime;
    private Passer<LoginIView> loginIViewPasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        updateTime = String.valueOf(System.currentTimeMillis());
        //获取验证码
        Glide.with(this)
                .load("http://www.bwstudent.com/instantMessaging/user/verification")
                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                .skipMemoryCache(true)//跳过内存缓存
                .into(yzmimg);
        //更换验证码
        yzmimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(LoginActivity.this)
                        .load("http://www.bwstudent.com/instantMessaging/user/verification")
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                        .skipMemoryCache(true)//跳过内存缓存
                        .into(yzmimg);
                Log.e("121", "21");
            }
        });
    }


    @OnClick({R.id.forgetpassword, R.id.register, R.id.userlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //忘记密码
            case R.id.forgetpassword:

                break;
            //注册
            case R.id.register:
                singup();
                break;
            //用户登入
            case R.id.userlogin:
                singin();
                break;
        }
    }


    /*
    * 注册方法
    * */
    private void singup() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    /*
    * 登入方法
    * */
    private void singin() {
        if (TextUtils.isEmpty(loginphone.getText().toString()) || TextUtils.isEmpty(loginpassword.getText().toString()) || TextUtils.isEmpty(loginyzm.getText().toString())) {
            Toast.makeText(this, "输入项不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //进行登录
        loginIViewPasser = new Passer<LoginIView>(this);
        loginIViewPasser.onLogin(loginphone.getText().toString(), loginpassword.getText().toString(), loginyzm.getText().toString());

    }

    //登入结果
    @Override
    public void onLogin(LoginBean loginBean) {
        if (loginBean.getStatus().equals("0000")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            loginphone.setText("");
            loginpassword.setText("");
            loginyzm.setText("");
        } else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginIViewPasser != null) {
            loginIViewPasser.onDestroy();
            loginIViewPasser = null;
        }
    }
}
