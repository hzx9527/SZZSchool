package com.shizhanzhe.szzschool.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.shizhanzhe.szzschool.Bean.CollectListBean;
import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.utils.OkHttpDownloadJsonUtil;
import com.shizhanzhe.szzschool.utils.Path;

import java.util.List;

//import com.shizhanzhe.szzschool.video.AnimateFirstDisplayListener;

/**
 * Created by hasee on 2016/12/20.
 */

public class CollectAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<CollectListBean>  list;
    private Context context;

    public CollectAdapter(Context context, List<CollectListBean> list) {
        this.context=context;
        this.list=list;

        inflater = LayoutInflater.from(context);
    }
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            // 是否设置为圆角，弧度为多少，当弧度为90时显示的是一个圆
            .displayer(new RoundedBitmapDisplayer(15))
            .showImageOnLoading(R.drawable.img_load)
            .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
            .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
            .build();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.adapter_collect,null);
            holder=new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.collect_img);
            holder.title= (TextView) convertView.findViewById(R.id.collect_title);
//            holder.del= (TextView) convertView.findViewById(R.id.delcollect);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        final CollectListBean.SysinfoBean bean = list.get(position).getSysinfo().get(0);
        ImageLoader imageloader = ImageLoader.getInstance();
        imageloader.displayImage(Path.IMG(bean.getThumb()), holder.iv, options);
        holder.title.setText(bean.getStitle());

//        holder.del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    list.remove(position);
//                    notifyDataSetChanged();
//
//                    OkHttpDownloadJsonUtil.downloadJson(context, new Path(context).DELCOLLECT( bean.getId()), new OkHttpDownloadJsonUtil.onOkHttpDownloadListener() {
//                        @Override
//                        public void onsendJson(String json) {
//                        }
//                    });
//
//            }
//        });
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView title;
        TextView del;
    }
}
