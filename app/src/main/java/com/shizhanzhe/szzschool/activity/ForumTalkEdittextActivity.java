package com.shizhanzhe.szzschool.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhanzhe.szzschool.R;
import com.shizhanzhe.szzschool.utils.StatusBarUtil;
import com.shizhanzhe.szzschool.video.PolyvTalkEdittextActivity;
import com.shizhanzhe.szzschool.video.PolyvTalkFragment;

/**
 * Created by zz9527 on 2017/6/15.
 * 评论
 */

public class ForumTalkEdittextActivity extends Activity {
    // 讨论的输入框
    private EditText et_talk;
    // 发送按钮
    private TextView tv_send;
    // 非发送内容的字符串长度
    private int length;
    private String nickname;
    private String questionid;
    private void findIdAndNew() {
        et_talk = (EditText) findViewById(R.id.et_talk);
        tv_send = (TextView) findViewById(R.id.tv_send);
    }

    private void initView() {
        final SpannableString spanStr = new SpannableString("回复 @" + nickname + " : ");
        length = spanStr.length();
        spanStr.setSpan(new BackgroundColorSpan(Color.MAGENTA), 0, length - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.center_left_text_color_gray)), 0,
                length - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        et_talk.setText(spanStr);
        et_talk.setSelection(length);
        et_talk.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String all = et_talk.getText().toString();
                String msg = all.substring(all.indexOf(" : ") + 3);
                if (all.length() < length || all.indexOf(" : ") == -1)
                    msg = "";
                if (start < length && !et_talk.getText().toString().equals(spanStr.toString() + msg)) {
                    final SpannableString spanStr1 = new SpannableString(spanStr + msg);
                    spanStr1.setSpan(new BackgroundColorSpan(Color.MAGENTA), 0, spanStr1.length() - msg.length() - 3,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanStr1.setSpan(
                            new ForegroundColorSpan(getResources().getColor(R.color.center_left_text_color_gray)), 0,
                            spanStr1.length() - msg.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    et_talk.setText(spanStr1);
                    et_talk.setSelection(et_talk.getText().toString().length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String sendMsg = et_talk.getText().toString().substring(length);
                if (sendMsg.trim().length()==0) {
                    Toast.makeText(ForumTalkEdittextActivity.this,"发送信息不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ForumTalkEdittextActivity.this, PolyvTalkFragment.class);
                intent.putExtra("sendMsg", sendMsg);
                intent.putExtra("questionid", questionid);
                setResult(19, intent);
                finish();
            }
        });
    }

    private void initData() {
        nickname = getIntent().getStringExtra("nickname");
        questionid= getIntent().getStringExtra("questionid");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polyv_activity_talk_edittext);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this,R.color.white); }
        initData();
        findIdAndNew();
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP)
            finish();
        return true;
    }
}
