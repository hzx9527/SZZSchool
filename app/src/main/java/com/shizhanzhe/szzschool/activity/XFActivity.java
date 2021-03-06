package com.shizhanzhe.szzschool.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.shizhanzhe.szzschool.Bean.XFBean;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.adapter.XFAdapter;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;
import com.shizhanzhe.szzschool.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hasee on 2016/12/19.
 * 消费
 */
@ContentView(R.layout.activity_xf)
public class XFActivity extends Activity {
    @ViewInject(R.id.list_xf)
    ListView xf;
    @ViewInject(R.id.back)
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this,R.color.white); }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        OkHttpDownloadJsonUtil.downloadJson(XFActivity.this, new Path(this).XF(), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
            @Override
            public void onsendJson(String json) {
                Gson gson = new Gson();
                //Json的解析类对象
                final List<XFBean> list = gson.fromJson(json, new TypeToken<List<XFBean>>(){}.getType());
                if (list.size()>0) {
                    final XFAdapter xfAdapter = new XFAdapter(getApplicationContext(), list);
                    xf.setAdapter(xfAdapter);
//                    xf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                            new AlertDialog.Builder(XFActivity.this).setTitle("删除").setMessage("确定要删除本条记录吗？")
//                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            String xfid = list.get(position).getId();
//                                            OkHttpDownloadJsonUtil.downloadJson(getApplicationContext(), new Path(XFActivity.this).DelXF(xfid), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
//                                                @Override
//                                                public void onsendJson(String json) {
//                                                    Log.i("===", "删除成功" + json);
//                                                }
//                                            });
//                                            list.remove(position);
//                                            xfAdapter.notifyDataSetChanged();
//                                        }
//                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            }).show();
//
//                        }
//                    });
                }else {
                    xf.setVisibility(View.GONE);
                }
            }
        });
    }
}
