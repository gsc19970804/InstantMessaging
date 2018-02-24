package com.gsc.im.im.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gsc.im.im.R;
import com.gsc.im.im.login.bean.ReginsterBean;
import com.gsc.im.im.login.bean.YZMBean;
import com.gsc.im.im.login.presenter.Passer;
import com.gsc.im.im.login.view.ReginsterIView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements ReginsterIView {

    @Bind(R.id.registerphone)
    EditText registerphone;
    @Bind(R.id.registeryzm)
    EditText registeryzm;
    @Bind(R.id.getyzm)
    Button getyzm;
    @Bind(R.id.registerpassword)
    EditText registerpassword;
    @Bind(R.id.registerpassword2)
    EditText registerpassword2;
    @Bind(R.id.registername)
    EditText registername;
    @Bind(R.id.registeruser)
    Button registeruser;
    private Passer<ReginsterIView> reginsterIViewPasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.getyzm, R.id.registeruser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.getyzm:
                if (TextUtils.isEmpty(registerphone.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取验证码
                reginsterIViewPasser = new Passer<ReginsterIView>(this);
                reginsterIViewPasser.onGetYZM(registerphone.getText().toString(), "1");
                break;
            //用户注册
            case R.id.registeruser:
                singup();
                break;
        }
    }


    /*
    * 用户注册
    * **/

    private void singup() {
        if (TextUtils.isEmpty(registerphone.getText().toString()) || TextUtils.isEmpty(registerpassword.getText().toString()) || TextUtils.isEmpty(registerpassword2.getText().toString()) || TextUtils.isEmpty(registeryzm.getText().toString()) || TextUtils.isEmpty(registername.getText().toString())) {
            Toast.makeText(this, "输入项不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!registerpassword.getText().toString().equals(registerpassword2.getText().toString())) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //进行注册
        if (reginsterIViewPasser == null) {
            Toast.makeText(this, "请获取验证码，并输入正确的验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", registerphone.getText().toString());
        map.put("password", registerpassword.getText().toString());
        map.put("password1", registerpassword2.getText().toString());
        map.put("realName", registername.getText().toString());
        map.put("verify", registeryzm.getText().toString());
        reginsterIViewPasser.onRegister(map);

    }

    //获取验证码
    @Override
    public void onGetYZM(YZMBean yzmBean) {
        Toast.makeText(this, yzmBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //用户注册
    @Override
    public void onRegister(ReginsterBean reginsterBean) {
        if (reginsterBean.getStatus().equals("0000")) {
            Toast.makeText(this, reginsterBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, reginsterBean.getMessage() + ",错误码:" + reginsterBean.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }


    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reginsterIViewPasser != null) {
            reginsterIViewPasser.onDestroy();
            reginsterIViewPasser = null;
        }
    }
}
