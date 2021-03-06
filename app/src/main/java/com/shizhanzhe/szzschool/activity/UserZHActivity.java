package com.shizhanzhe.szzschool.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.shizhanzhe.szzschool.Bean.LoginBean;
import com.shizhanzhe.szzschool.Bean.PersonalDataBean;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.pay.Pay;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;
import com.shizhanzhe.szzschool.utils.StatusBarUtil;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import org.zackratos.ultimatebar.UltimateBar;


/**
 * Created by hasee on 2016/11/4.
 * 账户中心
 */
@ContentView(R.layout.activity_userzh)
public class UserZHActivity extends Activity implements View.OnClickListener {
    @ViewInject(R.id.mymoney)
    TextView money;
    @ViewInject(R.id.recharge)
    TextView cz;
    @ViewInject(R.id.xf)
    TextView xf;
    @ViewInject(R.id.back)
    ImageView back;
    @ViewInject(R.id.dsmoney)
    TextView dsmoney;
    @ViewInject(R.id.tgmoney)
    TextView tgmoney;
    @ViewInject(R.id.vip)
    TextView vip;
    @ViewInject(R.id.ktvip)
    TextView ktvip;
    @ViewInject(R.id.dstx)
    TextView dstx;
    @ViewInject(R.id.dszy)
    TextView dszy;
    @ViewInject(R.id.tgtx)
    TextView tgtx;
    @ViewInject(R.id.tgzy)
    TextView tgzy;
    @ViewInject(R.id.empty)
    QMUIEmptyView empty;
    @ViewInject(R.id.scroll)
    ScrollView scroll;
    private QMUITipDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this,R.color.white); }


        dialog = new QMUITipDialog.Builder(this).setIconType(1).setTipWord("正在加载").create();

        getUserData();
        cz.setOnClickListener(this);
        xf.setOnClickListener(this);
        ktvip.setOnClickListener(this);
        back.setOnClickListener(this);
        dstx.setOnClickListener(this);
        dszy.setOnClickListener(this);
        tgtx.setOnClickListener(this);
        tgzy.setOnClickListener(this);
    }

    private boolean flag = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge:
                Intent intent = new Intent(getApplicationContext(), MoneyActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.xf:
                startActivity(new Intent(UserZHActivity.this, XFActivity.class));
                break;
            case R.id.dstx:
                Intent intent1 = new Intent(UserZHActivity.this, TXZYActivity.class);
                intent1.putExtra("type", 1);
                startActivity(intent1);
                break;
            case R.id.dszy:
                Intent intent2 = new Intent(UserZHActivity.this, TXZYActivity.class);
                intent2.putExtra("type", 2);
                startActivity(intent2);
                break;
            case R.id.tgtx:
                Intent intent3 = new Intent(UserZHActivity.this, TXZYActivity.class);
                intent3.putExtra("type", 3);
                startActivity(intent3);
                break;
            case R.id.tgzy:
                Intent intent4 = new Intent(UserZHActivity.this, TXZYActivity.class);
                intent4.putExtra("type", 4);
                startActivity(intent4);
                break;
            case R.id.ktvip:
                Intent intent5 = new Intent(UserZHActivity.this, TXZYActivity.class);
                intent5.putExtra("type", 5);
                startActivity(intent5);
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
        flag = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                money.setText(frozen_money);
        }
    }

    private String frozen_money;

    void getUserData() {
        dialog.show();
        SharedPreferences preferences = getSharedPreferences("userjson", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final String mvip = preferences.getString("vip", "");

        OkHttpDownloadJsonUtil.downloadJson(this, new Path(this).PERSONALDATA(), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
            @Override
            public void onsendJson(String json) {
                try {
                    if (json.equals("0")) {
                        dialog.dismiss();
                        empty.show(false, "", "网络异常", "重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getUserData();
                            }
                        });
                        return;
                    } else if (json.equals("1")) {
                        dialog.dismiss();
                        empty.show(false, "", "网络超时", "重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getUserData();
                            }
                        });
                        return;
                    }
                    Gson gson = new Gson();
                    PersonalDataBean bean = gson.fromJson(json, PersonalDataBean.class);
                    frozen_money = bean.getFrozen_money();
                    money.setText(bean.getFrozen_money());
                    editor.putString("money", bean.getFrozen_money());
                    editor.commit();
                    dsmoney.setText(bean.getUser_money());
                    tgmoney.setText(bean.getTgfee());
                    if (mvip.contains("1")) {
                        vip.setText("已开通");
                    } else {
                        vip.setText("未开通");
                    }
                    dialog.dismiss();
                    scroll.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    dialog.dismiss();
                    empty.show(false, "", "数据异常", "重试", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getUserData();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            flag = false;
            getUserData();
        }
    }
}

