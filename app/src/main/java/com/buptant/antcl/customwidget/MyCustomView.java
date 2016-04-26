package com.buptant.antcl.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by antcl on 2016/4/26.
 */
public class MyCustomView extends View{
    private static final String TAG = MyCustomView.class.getSimpleName();

    private static final boolean DEBUG = false;
    private String titleText = "Hello world";

    private int titleColor = Color.BLUE;
    private int titleBackgroundColor = Color.YELLOW;
    private int titleSize = 30;

    private Paint mPaint;
    private Rect mRect;
    public MyCustomView(Context context) {
        this(context, null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final Resources.Theme theme = context.getTheme();
        //获得自定义控件的主题样式数组
        TypedArray array = theme.obtainStyledAttributes(attrs,R.styleable.MyCustomView, defStyleAttr, 0);
        if(array != null){
            int count = array.getIndexCount();
            //遍历每个属性来获得对应属性的值，也就是我们在xml布局文件中写的属性值
            for(int i=0; i<count; i++){
                int attr = array.getIndex(i);
                switch (attr){
                    case R.styleable.MyCustomView_titleColor:
                        titleColor = array.getColor(attr, Color.BLUE);
                        break;
                    case R.styleable.MyCustomView_titleSize:
                        titleSize = array.getDimensionPixelSize(attr, titleSize);
                        break;
                    case R.styleable.MyCustomView_titleText:
                        titleText = array.getString(attr);
                        break;
                    case R.styleable.MyCustomView_titleBackgroundColor:
                        titleBackgroundColor = array.getColor(attr, Color.YELLOW);
                        break;
                }
            }
        }
        array.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 测量模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        /**
         * 父布局希望子布局的大小,如果布局里面设置的是固定值,这里取布局里面的固定值和父布局大小值中的最小值.
         * 如果设置的是match_parent,则取父布局的大小
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (DEBUG)
            Log.e(TAG, "the widthSize:" + widthSize + " the heightSize" + heightSize);

        int width;
        int height;
        Rect mRect = new Rect();
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(titleSize);
            mPaint.getTextBounds(titleText, 0, titleText.length(), mRect);
            float textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {

            height = width;
        }
        /**
         * 最后调用父类方法,把View的大小告诉父布局。
         */
        setMeasuredDimension(width, height);
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(titleSize);
        /**
         * 得到自定义View的titleText内容的宽和高
         */
        mRect = new Rect();
        mPaint.getTextBounds(titleText, 0, titleText.length(), mRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(titleBackgroundColor);
//        canvas.drawCircle(getWidth() / 2f, getWidth() / 2f, getWidth() / 2f, mPaint);
        canvas.drawRect(0, 0, getWidth(), getWidth(), mPaint);
        mPaint.setColor(titleColor);
        canvas.drawText(titleText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }
}
