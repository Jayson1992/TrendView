package com.example.trendview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.example.trendview.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendView extends View {
    private boolean isShow = true;
    //网格线画笔
    private Paint mLinePaint;
    //左边日期画笔
    private Paint mDatePaint;
    //遗漏画笔
    private Paint mYlPaint;
    //小球画笔
    private Paint mBallPaint;
    //小球号数画笔
    private Paint mNumPaint;
    //小球之间的连线画笔
    private Paint mLinkPaint;

    //网格的水平间距
    private float mDeltaX;

    //网格垂直间距
    private float mDeltaY;
    private OnItemSelectedListener listener;

    public void setItemSelectedListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnItemSelectedListener {
        void itemSelected(int position);
    }

    //红球和篮球分布数据集合
    private List<Map<String, Object>> mBallList = new ArrayList<>();

    public TrendView(Context context) {
        this(context, null);
    }

    public TrendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSource();
        initData();
    }

    private void initSource() {
        //网格线画笔
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 1));
        //期数画笔
        mDatePaint = new Paint();
        mDatePaint.setColor(Color.BLACK);
        mDatePaint.setAntiAlias(true);
        mDatePaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 0.5f));
        mDatePaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        //遗漏画笔
        mYlPaint = new Paint();
        mYlPaint.setColor(Color.GRAY);
        mYlPaint.setAntiAlias(true);
        mYlPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 0.5f));
        mYlPaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        //小球画笔
        mBallPaint = new Paint();
        mBallPaint.setAntiAlias(true);
        mBallPaint.setColor(Color.RED);
        mBallPaint.setStyle(Paint.Style.FILL);
        //小球号数画笔
        mNumPaint = new Paint();
        mNumPaint.setColor(Color.WHITE);
        mNumPaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        mNumPaint.setAntiAlias(true);
        mNumPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 0.5f));
        //小球之间连线画笔
        mLinkPaint = new Paint();
        mLinkPaint.setColor(Color.RED);
        mLinkPaint.setAntiAlias(true);
        mLinkPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 1f));
        //设置单个网格的水平和垂直间距
        mDeltaX = DisplayUtil.getScreenWidth(getContext()) / 12;
        mDeltaY = DisplayUtil.getScreenWidth(getContext()) / 12;
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();
            if (i == 0)
                map.put("red", 1);
            if (i == 1)
                map.put("red", 3);
            if (i == 2)
                map.put("red", 9);
            if (i == 3)
                map.put("red", 5);
            if (i == 4)
                map.put("red", 2);
            if (i == 5)
                map.put("red", 0);
            if (i == 6)
                map.put("red", 6);
            if (i == 7)
                map.put("red", 4);
            if (i == 8)
                map.put("red", 1);
            if (i == 9)
                map.put("red", 3);
            if (i == 10)
                map.put("red", 9);
            if (i == 11)
                map.put("red", 5);
            if (i == 12)
                map.put("red", 2);
            if (i == 13)
                map.put("red", 0);
            if (i == 14)
                map.put("red", 6);
            if (i == 15)
                map.put("red", 4);
            if (i == 16)
                map.put("red", 1);
            if (i == 17)
                map.put("red", 3);
            if (i == 18)
                map.put("red", 9);
            if (i == 19)
                map.put("red", 5);
            if (i == 20)
                map.put("red", 2);
            if (i == 21)
                map.put("red", 0);
            if (i == 22)
                map.put("red", 6);
            if (i == 23)
                map.put("red", 4);
            if (i == 24)
                map.put("red", 3);
            if (i == 25)
                map.put("red", 9);
            if (i == 26)
                map.put("red", 5);
            if (i == 27)
                map.put("red", 2);
            if (i == 28)
                map.put("red", 0);
            if (i == 29)
                map.put("red", 6);
            map.put("date", "0111-035");
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
        if (isShow)
            drawYlText(canvas);
        drawRedBall(canvas);
        drawNumBall(canvas);
        drawLinkLine(canvas);
    }

    private float mStartY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float mEndY = event.getY();
                if (mStartY == mEndY) {
                    //点击的行数
                    int lineIndex = (int) (mStartY / getHeight() * mBallList.size());
                    if (null != listener) {
                        listener.itemSelected(lineIndex + 1);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    /***
     * 绘制红球文字
     * @param canvas 画布
     */
    private void drawNumBall(Canvas canvas) {
        for (int i = 0; i < mBallList.size(); i++) {
            int red = (int) mBallList.get(i).get("red");
            float textWidth = mNumPaint.measureText(red + "");
            Paint.FontMetrics fontMetrics = mNumPaint.getFontMetrics();
            float y = mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
            canvas.drawText(red + "", mDeltaX * 2 + mDeltaX * red + (mDeltaX - textWidth) / 2, y + (i * mDeltaY), mNumPaint);
        }
    }

    public void setShow(boolean show) {
        isShow = show;
        invalidate();
    }

    /***
     * 绘制遗漏文字
     * @param canvas 画布
     */
    private void drawYlText(Canvas canvas) {
        for (int i = 0; i < mBallList.size(); i++) {
            Map<String, Object> map = mBallList.get(i);
            Paint.FontMetrics fontMetrics = mYlPaint.getFontMetrics();
            float y = (mDeltaY / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2) + (i * mDeltaY);
            for (int j = 0; j <= 9; j++) {
                canvas.drawText(map.get("num" + j) + "",
                        mDeltaX * 2 + j * mDeltaX + (mDeltaX - mYlPaint.measureText(map.get("num" + j) + "")) / 2,
                        y, mYlPaint);
            }
        }
    }

    /***
     * 绘制X轴和Y轴的网格线
     * @param canvas 画布
     */
    private void drawXYLine(Canvas canvas) {
        for (int i = 0; i <= mBallList.size(); i++) {
            canvas.drawLine(0, this.mDeltaY * i, getMeasuredWidth(), this.mDeltaY * i, mLinePaint);
        }
        for (int i = 2; i <= 11; i++) {
            canvas.drawLine(this.mDeltaX * i, 0, this.mDeltaX * i, getMeasuredHeight(), mLinePaint);
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

    /***
     * 绘制红球在网格中的分布图
     * @param canvas 画布
     */
    private void drawRedBall(Canvas canvas) {
        for (int i = 0; i < mBallList.size(); i++) {
            Map<String, Object> map = mBallList.get(i);
            int red = (int) map.get("red");
            canvas.drawCircle(mDeltaX * 2 + red * mDeltaX + mDeltaX / 2, mDeltaY / 2 + i * mDeltaY, (mDeltaX / 2 - 8.8f), mBallPaint);
            //记录小球的坐标，连线时，直接使用
            map.put("x", mDeltaX * 2 + red * mDeltaX + mDeltaX / 2);
            map.put("y", mDeltaY / 2 + i * mDeltaY);
        }
    }

    /**
     * 红球之间的连线
     *
     * @param canvas
     */
    private void drawLinkLine(Canvas canvas) {
        float startX = (float) mBallList.get(0).get("x");
        float startY = (float) mBallList.get(0).get("y");
        float endX = 0;
        float endY = 0;
        for (int i = 1; i < mBallList.size(); i++) {
            Map<String, Object> map = mBallList.get(i);
            if (i % 2 == 0) {
                startX = (float) map.get("x");
                startY = (float) map.get("y");
            } else {
                endX = (float) map.get("x");
                endY = (float) map.get("y");
            }
            canvas.drawLine(startX, startY, endX, endY, mLinkPaint);
        }
    }
}