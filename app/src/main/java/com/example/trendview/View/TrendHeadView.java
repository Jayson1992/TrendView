package com.example.trendview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.trendview.utils.DisplayUtil;


public class TrendHeadView extends View {
    private String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    //网格线画笔
    private Paint mLinePaint;
    //文字画笔
    private Paint mTextPaint;
    //头部的高度为一个网格的高度
    private float mDeltaY;

    public TrendHeadView(Context context) {
        this(context, null);
    }

    public TrendHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrendHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSource();
    }

    private void initSource() {
        //网格线画笔
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 1));
        //小球号数画笔

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 0.5f));
        mDeltaY = DisplayUtil.getScreenWidth(getContext()) / 12;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, (int) mDeltaY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#0078d7"));
        drawDateText(canvas);
        drawLine(canvas);
        drawText(canvas);
    }

    private void drawLine(Canvas canvas) {
        for (int i = 2; i <= 11; i++) {
            canvas.drawLine(mDeltaY * i, 0, mDeltaY * i, getMeasuredHeight(), mLinePaint);
        }
    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < str.length; i++) {
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            float y = (mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2);
            canvas.drawText(str[i], mDeltaY * 2 + +mDeltaY * i + (mDeltaY - mTextPaint.measureText(str[i])) / 2, y, mTextPaint);
        }
    }

    private void drawDateText(Canvas canvas) {
        String date = "期数";
        float textWidth = mTextPaint.measureText(date);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float y = mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(date, mDeltaY - textWidth / 2, y, mTextPaint);
    }
}
