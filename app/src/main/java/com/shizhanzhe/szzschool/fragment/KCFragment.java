package com.shizhanzhe.szzschool.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shizhanzhe.szzschool.Bean.BKBean;
import com.shizhanzhe.szzschool.Bean.ProBean;
import com.shizhanzhe.szzschool.Bean.ProListBean;
import com.shizhanzhe.szzschool.Bean.TGBean;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.activity.DetailActivity;
import com.shizhanzhe.szzschool.activity.MyApplication;
import com.shizhanzhe.szzschool.adapter.GVAdapter;
import com.shizhanzhe.szzschool.adapter.ListItem2Adapter;
import com.shizhanzhe.szzschool.adapter.TGAdapter;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;
import com.shizhanzhe.szzschool.utils.RefreshLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by zz9527 on 2017/6/12.
 */
@ContentView(R.layout.fragment_kc)
public class KCFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {
    @ViewInject(R.id.lv)
    ListView lv;
    RefreshLayout swipeLayout;
    int page=1;
    int type;
    List<TGBean>  tgList;
    TGAdapter tgAdapter;
    public static KCFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type",type);
        KCFragment fragment = new KCFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(this, inflater, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListener();
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        getData(type);

    }
    void getData(int type){
        if (type==1) {
            OkHttpDownloadJsonUtil.downloadJson(getActivity(), new Path(getContext()).CENTER(), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
                @Override
                public void onsendJson(String json) {
                    Gson gson = new Gson();
                    final List<ProBean.TxBean> tx = gson.fromJson(json, ProBean.class).getTx();
                    lv.setAdapter(new ListItem2Adapter(tx, getContext()));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), DetailActivity.class);
                            String proid = tx.get(position).getId();
                            intent.putExtra("id", proid);
                            startActivity(intent);
                        }
                    });
                }
            });
        }else if (type==0){
            OkHttpDownloadJsonUtil.downloadJson(getActivity(), Path.TG(page), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {


                @Override
                public void onsendJson(String json) {
                    Gson gson = new Gson();
                    tgList = gson.fromJson(json, new TypeToken<List<TGBean>>(){}.getType());
                    SharedPreferences preferences =getActivity().getSharedPreferences("userjson", Context.MODE_PRIVATE);
                    String ktagent = preferences.getString("ktagent", "");
                    tgAdapter = new TGAdapter(tgList,null,getContext(),ktagent);
                    lv.setAdapter(tgAdapter);
                }
            });
        }
    }
    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void init(View view) {
        swipeLayout = (RefreshLayout)view.findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeResources(R.color.darkgray, R.color.blue2, R.color.red, R.color.green);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                swipeLayout.setLoading(false);
                if (tgList!=null){

                page++;
                OkHttpDownloadJsonUtil.downloadJson(getActivity(), Path.TG(page), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
                    @Override
                    public void onsendJson(String json) {
                        Gson gson = new Gson();
                        List<TGBean> lists = gson.fromJson(json, new TypeToken<List<TGBean>>(){}.getType());

                        for (TGBean b :
                                lists) {
                            tgList.add(b);
                        }
                        tgAdapter.notifyDataSetChanged();
                    }
                });
            }
            }
        }, 2000);

    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                getData(type);
                swipeLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
