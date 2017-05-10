package com.adapter.smart.more;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xbb on 2017/5/10.
 */

/*
* 简单的富文本工具类
*
* 使用说明 new UtilMoreText().setClickableSpan(tv2,msg);
* */
public class UtilMoreText  {

    private OnSpanTextClickListener mSpanTextClickListener;

    private TextView mTextView;//显示富文本的控件
    private String mOriMsg;//全部文本信息

    private boolean spread =true;
    private int mStartPos;
    private int mEndPos;
    private Integer mSpanTextColor;
    private Integer mCharNum;

    public UtilMoreText(TextView textView, String oriMsg) {
        mTextView = textView;
        mOriMsg = oriMsg;
        textView.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
        textView.setText(part());
    }

    public UtilMoreText setCharNum(int charNum){
        mCharNum = charNum;
        return this;
    }
    public UtilMoreText setSpanTextColor(int spanTextColor){
        mSpanTextColor = spanTextColor;
        return this;
    }

    public UtilMoreText setOnSpanTextClickListener(OnSpanTextClickListener spanTextClickListener){
        mSpanTextClickListener = spanTextClickListener;
        return this;
    }
    public UtilMoreText setPos(int startPos, int endPos){
        mStartPos = startPos;
        mEndPos = endPos;
        return this;
    }


    class SpanTextClickable extends ClickableSpan implements View.OnClickListener{
        @Override
        public void onClick(View widget) {
            if (spread) {//调用展开的方法
                spread = false;
                if (mSpanTextClickListener == null) {
                    mTextView.setText(open());
                }else {
                    mSpanTextClickListener.setSpanText(mTextView,open());
                }

            }else {
                spread = true;
                if (mSpanTextClickListener == null) {
                    mTextView.setText(part());
                }else {
                    mSpanTextClickListener.setSpanText(mTextView,part());
                }

            }
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            if (mSpanTextColor == null) {
                ds.setColor(Color.BLUE);
            }else {
                ds.setColor(ds.linkColor);
            }

            ds.setUnderlineText(false);    //去除超链接的下划线
        }
    }

    private SpannableString part() {
        String temp="";
        if (mCharNum != null) {
            temp = mOriMsg.substring(0,mCharNum)+"...展开";
        }else {
            temp = mOriMsg.substring(0,mOriMsg.length()/2)+"...展开";
        }
        return getSpannableString(temp);
    }

    private SpannableString open() {
        String temp = mOriMsg+"收起";
        return getSpannableString(temp);
    }

    private SpannableString getSpannableString(CharSequence temp) {
        SpannableString spanableInfo = new SpannableString(temp);
        spanableInfo.setSpan(new  SpanTextClickable(), mStartPos==0?temp.length()-2:mStartPos, mEndPos==0?temp.length():mEndPos,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }

    public interface OnSpanTextClickListener {
        void setSpanText(TextView view,SpannableString s);
    }
}
