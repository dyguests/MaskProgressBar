package com.fanhl.maskprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanhl on 15/11/26.
 */
public class MaskProgressBar extends View {
    public static final int DEFAULT_MAX_VALUE  = 100;
    public static final int DEFAULT_MASK_COLOR = 0x6FFFFFFF;

    private Paint     mCirclePaint;
    private TextPaint mTextPaint;
    private Paint     mHollowPaint;

    private int mContentWidth;
    private int mContentHeight;

    private int mMaskColor;
    private int mMaxValue;

    private float mProgress = 0.3f;

    public MaskProgressBar(Context context) {
        super(context);
        init(null, 0);
    }

    public MaskProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MaskProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public MaskProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MaskProgressBar, defStyle, 0);

        mMaskColor = a.getColor(R.styleable.MaskProgressBar_maskColor, DEFAULT_MASK_COLOR);
        mMaxValue = a.getInteger(R.styleable.MaskProgressBar_maxValue, DEFAULT_MAX_VALUE);

        a.recycle();

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);

        mTextPaint = new TextPaint();

        mHollowPaint = new Paint();
        mHollowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize  = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        mContentWidth = widthSize - getPaddingLeft() - getPaddingRight();
        mContentHeight = heightSize - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
