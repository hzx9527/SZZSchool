package com.shizhanzhe.szzschool.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.shizhanzhe.szzschool.Bean.ForumCommentBean;
import com.shizhanzhe.szzschool.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by zz9527 on 2017/6/15.
 */

public class ForumCommentSubTalkListViewAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private ForumCommentSubTalkListViewAdapter.ViewHolder viewHolder;
    private List<ForumCommentBean.ManReplyBean> lists;
    private DisplayImageOptions options;
    private SpannableString ss;

    public ForumCommentSubTalkListViewAdapter(Context context, List<ForumCommentBean.ManReplyBean> lists) {
        this.context = context;
        this.lists = lists;
        if (this.lists == null)
            this.lists = new LinkedList<>();
        this.inflater = LayoutInflater.from(context);

        this.options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.polyv_avatar_def) // resource
                // or
                // drawable
                .showImageForEmptyUri(R.drawable.polyv_avatar_def) // resource or
                // drawable
                .showImageOnFail(R.drawable.polyv_avatar_def) // resource or drawable
                .bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true).cacheOnDisk(true)
                .build();
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 如果是ListView，那么会由于无法测量高度，导致这个方法不断地执行
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.polyv_listview_talk_sub_item, parent, false);
            viewHolder = new ForumCommentSubTalkListViewAdapter.ViewHolder();
            viewHolder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
            viewHolder.iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ForumCommentSubTalkListViewAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.iv_avatar.setVisibility(View.GONE);
        ForumCommentBean.ManReplyBean replyBean = lists.get(position);
        ss = new SpannableString(replyBean.getAuthor()
                +"："+replyBean.getComment());
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0,
                replyBean.getAuthor().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),replyBean.getAuthor().length(),
                replyBean.getAuthor().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //为回复的人昵称添加点击事件
        viewHolder.tv_msg.setText(ss);
        //添加点击事件时，必须设置
        viewHolder.tv_msg.setMovementMethod(LinkMovementMethod.getInstance());
        viewHolder.tv_time.setText(replyBean.getDateline());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_time;
        TextView tv_msg;
        ImageView iv_avatar;
    }
    public static String getSpaceTime(Long millisecond) {
        long currentMillisecond = System.currentTimeMillis();
        //间隔秒
        Long spaceSecond = (currentMillisecond - millisecond*1000) / 1000;
        //一分钟之内
        if (spaceSecond >= 0 && spaceSecond < 60) {
            return "刚刚";
        }
        //一小时之内
        else if (spaceSecond / 60 > 0 && spaceSecond / 60 < 60) {
            return spaceSecond / 60 + "分钟之前";
        }
        //一天之内
        else if (spaceSecond / (60 * 60) > 0 && spaceSecond / (60 * 60) < 24) {
            return spaceSecond / (60 * 60) + "小时之前";
        }
        //3天之内
        else if (spaceSecond/(60*60*24)>0&&spaceSecond/(60*60*24)<3){
            return spaceSecond/(60*60*24)+"天之前";
        }else {
            return getDateTimeFromMillisecond(millisecond);
        }
    }
    /**
     * 将毫秒转化成固定格式的时间
     * 时间格式: yyyy-MM-dd HH:mm:ss
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(long millisecond){
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millisecond*1000);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}

