package com.adapter.smart.more;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.smart.moretext.UtilMoreText;


public class MainActivity extends AppCompatActivity {


    private TextView tv2;
    private TextView tv1;
    private TextView tv;
    private String msg ="在Android开发中，有许多信息展示需要通过TextView来展现有许多" +
            "信息展示需要通过TextView来展现有许多信息展示需要通过TextView来展现有许多信" +
            "息展示需要通过TextView来展现，如果只是普通的信息展现，使用TextView setText(CharSequence " +
            "str)设置即可，但是当在TextView里的这段内容需要截取某一部分字段，可以被点击以及响应响应的操" +
            "作，这时候就需要用到SpannableString了，下面通过一段简单的代码实现部分文字被点击响应，及富文本表情的实现";
    public static final String TAG = "main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = (TextView) findViewById(R.id.tv2);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv = (TextView) findViewById(R.id.tv);

        tv2.setTextSize(25);
        tv1.setTextSize(25);
        tv.setTextSize(25);

        tv.setText(msg);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Log.i(TAG, "onCreate: "+width);

        Drawable down = getResources().getDrawable(R.drawable.down);
        Drawable up = getResources().getDrawable(R.drawable.up);
        new UtilMoreText(tv2,msg,down,up).createImg();
        float real = DensityUtil.px2sp(this,width);
        Log.i(TAG, "onCreate: "+real);
        new UtilMoreText(tv1,msg).setSpanTextColor(R.color.colorAccent).createString();
    }
}
