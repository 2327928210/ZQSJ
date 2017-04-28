package com.example.haier.sheji.hudong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by GHZ_PC on 2016/12/26.
 * 这个是自定义TextView 让第二行居中显示
 */

public class CustomTextView extends TextView {

        private StaticLayout myStaticLayout;
        private TextPaint tp;

        public CustomTextView(Context context, AttributeSet attrs)
        {
            super(context, attrs);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh)
        {
            super.onSizeChanged(w, h, oldw, oldh);
            initView();
        }

    private void initView()
    {
        tp = new TextPaint(Paint.ANTI_ALIAS_FLAG);//,ANTI_ALIAS_FLAG这个标志位意指抗锯齿的
        tp.setTextSize(getTextSize());
        tp.setColor(getCurrentTextColor());
        myStaticLayout = new StaticLayout(getText(), tp, getWidth(), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        myStaticLayout.draw(canvas);
    }
}

