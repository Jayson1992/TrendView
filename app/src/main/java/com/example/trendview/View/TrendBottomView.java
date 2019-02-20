package com.example.trendview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.example.trendview.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendBottomView extends View {
    private Paint mDatePaint;
    //网格的水平间距
    private float mDeltaX;
    //网格垂直间距
    private float mDeltaY;
    //红球和篮球分布数据集合
    private List<Map<String, Object>> mBallList = new ArrayList<>();

    public TrendBottomView(Context context) {
        this(context, null);
    }

    public TrendBottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrendBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSource();
        initData();
    }

    private void initSource() {
        //期数画笔
        mDatePaint = new Paint();
        mDatePaint.setColor(Color.BLACK);
        mDatePaint.setAntiAlias(true);
        mDatePaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 0.5f));
        mDatePaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        //设置单个网格的水平和垂直间距
        mDeltaX = DisplayUtil.getScreenWidth(getContext()) / 12;
        mDeltaY = DisplayUtil.getScreenWidth(getContext()) / 12;
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            Map<String, Object> map = new HashMap<>();
            if (i == 0)
                map.put("date", "出现次数");
            else if (i == 1)
                map.put("date", "平均遗漏");
            else if (i == 2)
                map.put("date", "最大遗漏");
            else if (i == 3)
                map.put("date", "最大连出");
            map.put("num0", "5");
            map.put("num1", "6");
            map.put("num2", "9");
            map.put("num3", "11");
            map.put("num4", "22");
            map.put("num5", "33");
            map.put("num6", "44");
            map.put("num7", "46");
            map.put("num8", "89");
            map.put("num9", "32");
            mBallList.add(map);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置测量View的大小:宽度和高度
        setMeasuredDimension(widthMeasureSpec, (int) (mBallList.size() * mDeltaY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXYLine(canvas);
        drawDateText(canvas);
        drawYlText(canvas);
    }

    /***
     * 绘制遗漏文字
     * @param canvas 画布
     */
    private void drawYlText(Canvas canvas) {
        for (int i = 0; i < mBallList.size(); i++) {
            Map<String, Object> map = mBallList.get(i);
            Paint.FontMetrics fontMetrics = mDatePaint.getFontMetrics();
            float y = (mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2) + (i * mDeltaY);
            for (int j = 0; j <= 9; j++) {
                canvas.drawText(map.get("num" + j) + "",
                        mDeltaX * 2 + j * mDeltaX + (mDeltaX - mDatePaint.measureText(map.get("num" + j) + "")) / 2,
                        y, mDatePaint);
            }
        }
    }

    /***
     * 绘制X轴和Y轴的网格线
     * @param canvas 画布
     */
    private void drawXYLine(Canvas canvas) {
        for (int i = 0; i <= mBallList.size(); i++) {
            canvas.drawLine(0, this.mDeltaY * i, getMeasuredWidth(), this.mDeltaY * i, mDatePaint);
        }
        for (int i = 2; i <= 11; i++) {
            canvas.drawLine(this.mDeltaX * i, 0, this.mDeltaX * i, getMeasuredHeight(), mDatePaint);
        }
    }

    private void drawDateText(Canvas canvas) {
        for (int i = 0; i < mBallList.size(); i++) {
            String date = mBallList.get(i).get("date") + "";
            float textWidth = mDatePaint.measureText(date);
            Paint.FontMetrics fontMetrics = mDatePaint.getFontMetrics();
            float y = mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
            canvas.drawText(date, mDeltaX - textWidth / 2, y + (i * mDeltaY), mDatePaint);
        }
    }

}