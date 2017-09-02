//package com.chowen.apackage.testkitdemo.animator;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Handler;
//import android.os.Message;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
///**
// * Created by zhouwen on 17/3/clip_22.
// */
//public class ClockView extends View {
//    //View默认宽度
//    private static final int DEFAULT_MIN_WIDTH = 640;
//
//    private String mDataStr;
//
//    private String mWeekdayStr;
//
//    private Drawable mHourDrawable;
//
//    private Drawable mHourShadowDrawable;
//
//    private Drawable mMinuteDrawable;
//
//    private Drawable mMinuteShadowDrawable;
//
//    private Paint mPaint;
//
//    private Thread mTimeThread = new Thread() {
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    mUpdateHandler.sendEmptyMessage(0);
//                    Thread.sleep(30 * 1000);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    private Handler mUpdateHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            formatDateLabel();
//            invalidate();
//        }
//    };
//
//    public ClockView(Context context) {
//        this(context, null);
//    }
//
//    public ClockView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        mTimeThread.start();
//        mPaint = new Paint();
//        mHourDrawable = getResources().getDrawable(R.drawable.ss_hour);
//        mHourShadowDrawable = getResources().getDrawable(R.drawable.ss_hour_shadow);
//        mMinuteDrawable = getResources().getDrawable(R.drawable.ss_min);
//        mMinuteShadowDrawable = getResources().getDrawable(R.drawable.ss_min_shadow);
//    }
//
//    private void formatDateLabel() {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
//        Date curDate = new Date(System.currentTimeMillis());
//        mDataStr = formatter.format(curDate);
//        formatter = new SimpleDateFormat("E");
//        mWeekdayStr = formatter.format(curDate);
//    }
//
//    /**
//     * 停止更新View
//     */
//    public void stopUpdate() {
//        if (mUpdateHandler != null) {
//            mUpdateHandler.removeCallbacks(mTimeThread);
//        }
//    }
//
//    /**
//     * 根据角度和长度计算线段的起点和终点的坐标
//     *
//     * @param angle  角度
//     * @param length 长度
//     * @return float[]
//     */
//    private float[] calculatePoint(float angle, float length) {
//        float[] points = new float[4];
//        if (angle <= 90f) {
//            points[0] = -(float) Math.sin(angle * Math.PI / 180);
//            points[1] = (float) Math.cos(angle * Math.PI / 180);
//            points[2] = (float) Math.sin(angle * Math.PI / 180) * length;
//            points[3] = -(float) Math.cos(angle * Math.PI / 180) * length;
//        } else if (angle <= 180f) {
//            points[0] = -(float) Math.cos((angle - 90) * Math.PI / 180);
//            points[1] = -(float) Math.sin((angle - 90) * Math.PI / 180);
//            points[2] = (float) Math.cos((angle - 90) * Math.PI / 180) * length;
//            points[3] = (float) Math.sin((angle - 90) * Math.PI / 180) * length;
//        } else if (angle <= 270f) {
//            points[0] = (float) Math.sin((angle - 180) * Math.PI / 180);
//            points[1] = -(float) Math.cos((angle - 180) * Math.PI / 180);
//            points[2] = -(float) Math.sin((angle - 180) * Math.PI / 180) * length;
//            points[3] = (float) Math.cos((angle - 180) * Math.PI / 180) * length;
//        } else if (angle <= 360f) {
//            points[0] = (float) Math.cos((angle - 270) * Math.PI / 180);
//            points[1] = (float) Math.sin((angle - 270) * Math.PI / 180);
//            points[2] = -(float) Math.cos((angle - 270) * Math.PI / 180) * length;
//            points[3] = -(float) Math.sin((angle - 270) * Math.PI / 180) * length;
//        }
//        return points;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        //日历
//        paintDateLabel(canvas);
//        //时针
//        paintPointer(canvas);
//        //刻度数字
//        paintDegreeNum(canvas);
//
//    }
//
//    /**
//     * 画刻度
//     *
//     * @param canvas canvas
//     */
//    private void paintDegreeNum(Canvas canvas) {
//        float r = Math.min(getHeight() / 2, getWidth() / 2);
//        int degreeNumberSize = 30;
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setTextSize(degreeNumberSize);
//        mPaint.setFakeBoldText(true);
//        for (int i = 0; i < 12; i++) {
//            float[] temp = calculatePoint((i + 1) * 30, r - degreeNumberSize / 2 - 15);
//            canvas.drawText((i + 1) + "", temp[2], temp[3] + degreeNumberSize / 2 - 6, mPaint);
//            Log.e("calculatePoint>",(i+1)+"calculatePoint>>1111>"+temp[2]+">>>"+temp[3]);
//            float[] temp1 = calculatePoint((i + 1) * 40, r - degreeNumberSize / 2 - 10);
//            Log.e("calculatePoint>",(i+1)+"calculatePoint>>2222>"+temp1[2]+">>>"+temp1[3]);
//        }
//        //canvas.getClipBounds();
//    }
//
//    /**
//     * 画指针
//     *
//     * @param canvas canvas
//     */
//    private void paintPointer(Canvas canvas) {
//        Calendar now = Calendar.getInstance();
//        //分针
//        canvas.save();
//        canvas.translate(320, 320);
//        canvas.rotate(now.get(Calendar.MINUTE) / 60f * 360);
//        canvas.translate(-43, -mMinuteShadowDrawable.getIntrinsicHeight() + 43);
//        canvas.drawBitmap(((BitmapDrawable) mMinuteShadowDrawable).getBitmap(), 0, 0, null);
//        canvas.restore();
//        //分针阴影
//        canvas.save();
//        canvas.translate(320, 320);
//        canvas.rotate(now.get(Calendar.HOUR_OF_DAY) % 12 / 12f * 360);
//        canvas.translate(-40, -mHourShadowDrawable.getIntrinsicHeight() + 40);
//        canvas.drawBitmap(((BitmapDrawable) mHourShadowDrawable).getBitmap(), 0, 0, null);
//        canvas.restore();
//        //时针
//        canvas.save();
//        canvas.translate(320, 320);
//        canvas.rotate(now.get(Calendar.MINUTE) / 60f * 360);
//        canvas.translate(-26, -mMinuteDrawable.getIntrinsicHeight() + 26);
//        canvas.drawBitmap(((BitmapDrawable) mMinuteDrawable).getBitmap(), 0, 0, null);
//        canvas.restore();
//        //时针阴影
//        canvas.save();
//        canvas.translate(320, 320);
//        canvas.rotate(now.get(Calendar.HOUR_OF_DAY) % 12 / 12f * 360);
//        canvas.translate(-23, -mHourDrawable.getIntrinsicHeight() + 23);
//        canvas.drawBitmap(((BitmapDrawable) mHourDrawable).getBitmap(), 0, 0, null);
//        canvas.restore();
//    }
//
//    /**
//     * 画星期，日期
//     *
//     * @param canvas canvas
//     */
//    private void paintDateLabel(Canvas canvas) {
//        mPaint.setTextSize(46f);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setFakeBoldText(true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        //C++(320, 426)--> getHeight()-426
//        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 426, getResources().getDisplayMetrics());
//        canvas.drawText(mWeekdayStr, 0, mWeekdayStr.length(), getWidth() / 2, (getHeight() - 426), mPaint);
//
//        mPaint.setTextSize(30f);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setFakeBoldText(true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        //C++(320, 219)--> getHeight()-219
//        canvas.drawText(mDataStr, 0, mDataStr.length(), getWidth() / 2, (getHeight() - 219), mPaint);
//    }
//
//    /**
//     * 当布局为wrap_content时设置默认长宽
//     *
//     * @param widthMeasureSpec
//     * @param heightMeasureSpec
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
//        setMeasuredDimension(DEFAULT_MIN_WIDTH, DEFAULT_MIN_WIDTH);
//    }
//}
