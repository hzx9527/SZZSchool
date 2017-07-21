package com.shizhanzhe.szzschool.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.Gson;
import com.shizhanzhe.szzschool.Bean.LoginBean;
import com.shizhanzhe.szzschool.MainActivity;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.internal.framed.Variant;

/**
 * Created by hasee on 2016/10/31.
 * 登陆
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends Activity implements View.OnClickListener {
    @ViewInject(R.id.btn_login)
    Button mBtnLogin;
    @ViewInject(R.id.edit_uid)
    EditText mEditUid;
    @ViewInject(R.id.txtMobileNum)
    EditText mEditPsw;
    private SharedPreferences.Editor editor;
    SVProgressHUD mSVProgressHUD;
    SVProgressHUD mSVProgressHUD1;
    SVProgressHUD mSVProgressHUD2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        mBtnLogin.setOnClickListener(this);
        findViewById(R.id.tv_quick_sign_up).setOnClickListener(this);
        findViewById(R.id.RetrievePassword).setOnClickListener(this);
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD1 = new SVProgressHUD(this);
        mSVProgressHUD2 = new SVProgressHUD(this);
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    /**
     * 登录按钮
     */
    String username;
    String b;
    private void login() {

        username = mEditUid.getText().toString();
        String password = mEditPsw.getText().toString();
        if (username != null && password.length() >= 6) {
            mSVProgressHUD.showWithStatus("正在登陆...");
            StringBuffer sb = new StringBuffer(password);
            String s = sb.reverse().toString();
            s = s.replace("", "-"); //每个字符加个-
            String a[] = s.split("-");//截取字符串为数组
            String t = a[3] + a[4];
            String y = a[2] + a[5];
            a[2] = "";
            a[3] = "";
            a[4] = "";
            a[5] = "";
            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < a.length; i++) {
                sb2.append(a[i]);
            }
            String b2 = sb2.toString();
            b = b2 + t + y;
            String path = Path.UZER(username, b);
            getLogin(path);
        } else {
            new SVProgressHUD(this).showInfoWithStatus("帐号或密码长度有误");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:    //登录
                login();
                break;
            case R.id.RetrievePassword:    //忘记密码
                startActivity(new Intent(this, FindPWActivity.class));
                break;
            case R.id.tv_quick_sign_up:    //注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            default:
                break;
        }
    }




    public void getLogin(String path){

        OkHttpDownloadJsonUtil.downloadJson(this, path, new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
            @Override
            public void onsendJson(String json) {

                if (json.length() <= 5) {
                    new SVProgressHUD(LoginActivity.this).showErrorWithStatus("帐号或密码错误！");
                } else {
                    new SVProgressHUD(LoginActivity.this).showSuccessWithStatus("登陆成功");
                    editor.putString("uname", username);
                    editor.putString("upawd", b);
                    editor.commit();
                    SharedPreferences preferences = getSharedPreferences("userjson", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = preferences.edit();
                    Gson gson = new Gson();
                    LoginBean bean = gson.fromJson(json, LoginBean.class);
                    editor2.putString("username", bean.getUsername());
                    editor2.putString("uid", bean.getId());
                    editor2.putString("token", bean.getToken());
                    editor2.putString("vip", bean.getVip());
                    editor2.putString("ktagent", bean.getKaiagent());
                    editor2.putString("teacher", bean.getIs_teacher());
                    editor2.putString("jy", bean.getJyan());
                    editor2.putString("img", bean.getHeadimg());
                    editor2.commit();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    intent.putExtra("data", json);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mSVProgressHUD.dismiss();
    }

}

