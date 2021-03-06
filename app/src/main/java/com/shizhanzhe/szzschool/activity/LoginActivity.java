package com.shizhanzhe.szzschool.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.shizhanzhe.szzschool.Bean.LoginBean;
import com.shizhanzhe.szzschool.MainActivity;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;
import com.shizhanzhe.szzschool.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


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
    private QMUITipDialog mdialog;
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mdialog.dismiss();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this,R.color.white); }
        mBtnLogin.setOnClickListener(this);
        findViewById(R.id.tv_quick_sign_up).setOnClickListener(this);
        findViewById(R.id.RetrievePassword).setOnClickListener(this);
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
//        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
//        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
//        dialog.setMessage("正在登录...Loading");
        SharedPreferences preferences = getSharedPreferences("userjson", Context.MODE_PRIVATE);

        editor = preferences.edit();
    }
    /**
     * 登录按钮
     */
    private String username;
    private String b;
    private void login() {

        username = mEditUid.getText().toString();
        String password = mEditPsw.getText().toString();
        if (username != null && password.length() >= 6) {
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
            mdialog = new QMUITipDialog.Builder(LoginActivity.this).setIconType(4).setTipWord("帐号或密码长度有误").create();
            mdialog.show();
            mhandler.sendEmptyMessageDelayed(1,1500);
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
        mdialog = new QMUITipDialog.Builder(LoginActivity.this).setIconType(1).setTipWord("正在登录").create();
        mdialog.show();
        OkHttpDownloadJsonUtil.downloadJson(this, path, new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
            @Override
            public void onsendJson(String json) {
                if (json.length() <= 5) {
                    mdialog.dismiss();
                    mdialog = new QMUITipDialog.Builder(LoginActivity.this).setIconType(3).setTipWord("帐号或密码错误！").create();
                    mdialog.show();
                    mhandler.sendEmptyMessageDelayed(1,1500);
                } else {
                    editor.putString("uname", username);
                    editor.putString("upawd", b);
                    Gson gson = new Gson();
                    LoginBean bean = gson.fromJson(json, LoginBean.class);
                    editor.putString("username", bean.getUsername());
                    editor.putString("uid", bean.getId());
                    editor.putString("mobile", bean.getMobile());
                    editor.putString("token", bean.getToken());
                    editor.putString("vip", bean.getVip());
                    editor.putString("money", bean.getMoney());
                    editor.putString("ktagent", bean.getKaiagent());
                    editor.putString("teacher", bean.getIs_teacher());
                    editor.putString("jy", bean.getJyan());
                    editor.putString("img", bean.getHeadimg());
                    editor.commit();
                    MyApplication.isLogin=true;
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mdialog!=null&&mdialog.isShowing()){
            mdialog.dismiss();
        }
    }
}

