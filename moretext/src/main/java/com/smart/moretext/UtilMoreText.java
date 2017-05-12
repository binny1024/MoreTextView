package com.smart.moretext;


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
* 使用说明 new UtilMoreText(tv2,msg);
* */
public class UtilMoreText  {

    private OnSpanTextClickListener mSpanTextClickListener;
    private float mExpectedWidth; //期望行宽度
    private TextView mTextView;//显示富文本的控件
    private String mOriMsg;//全部文本信息

    private boolean spread =true;
    private int mStartPos;
    private int mEndPos;
    private Integer mSpanTextColor;

    public UtilMoreText(final TextView textView, String oriMsg,float expectedWidth) {
        mTextView = textView;
        mOriMsg = oriMsg;
        mExpectedWidth = expectedWidth;
        textView.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
        textView.setText(part());
    }

    public UtilMoreText setExpectedWidth(float mExpectedWidth) {
        this.mExpectedWidth = mExpectedWidth;
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
                ds.setColor(Color.parseColor("#0061ff"));
            }else {
                ds.setColor(ds.linkColor);
            }

            ds.setUnderlineText(false);    //去除超链接的下划线
        }
    }

    private SpannableString part() {
        return getSpannableString(getCompressedString(mOriMsg)+"...展开");
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

    private String getCompressedString(String text){
        if (text.length() == 0) {
            return text;
        }
        String measuredString = text + "...展开";
        TextPaint paint = mTextView.getPaint();

        if (paint.measureText(measuredString + "...展开") < mExpectedWidth *2) {
            return measuredString;
        }
        boolean finished = false;
        int mPos = 0;
        while (!finished ){

            mPos++;
            measuredString = text.substring(0,mPos);
            if (paint.measureText(measuredString + "...展开") > mExpectedWidth *2 ) {
                finished = true;
            }
        }
        return measuredString;
    }
}
