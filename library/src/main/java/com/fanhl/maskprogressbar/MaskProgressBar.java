package com.fanhl.maskprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.NonNull;
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

    private float mProgress = 0.8f;

    private Bitmap mMaskBitmap;
    private Bitmap mHollowBitmap;

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
        mCirclePaint.setStyle(Paint.Style.STROKE);
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

        refreshMaskBitmap();
        refreshHollowBitmap();
    }

    private void refreshMaskBitmap() {
        //遮罩区域
        mMaskBitmap = Bitmap.createBitmap(mContentWidth, mContentHeight, Bitmap.Config.ARGB_8888);
        Canvas maskCanvas = new Canvas(mMaskBitmap);
        maskCanvas.drawColor(mMaskColor);
    }

    private void refreshHollowBitmap() {
        float radius = Math.min(mContentWidth, mContentHeight) / 2 / 2;

        mCirclePaint.setStrokeWidth(radius / 8);

        int centerX = mContentWidth / 2;
        int centerY = mContentHeight / 2;

        RectF mCircleBounds = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        //镂空区域
        mHollowBitmap = Bitmap.createBitmap(mContentWidth, mContentHeight, Bitmap.Config.ARGB_8888);
        Canvas hollowCanvas = new Canvas(mHollowBitmap);
        hollowCanvas.drawArc(mCircleBounds, -90, getCurrentRotation(), false, mCirclePaint);
        hollowCanvas.drawText(getProgressBarText(), centerX, centerY, mTextPaint);

        hollowCanvas.drawBitmap(mMaskBitmap, 0, 0, mHollowPaint);
    }

    @NonNull
    private String getProgressBarText() {
        return "吃了没";// FIXME: 15/11/25
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mHollowBitmap, getPaddingLeft(), getPaddingTop(), null);
    }

    /**
     * Gets the current rotation.
     *
     * @return the current rotation
     */
    private float getCurrentRotation() {
        return 360 * mProgress;
    }
}
