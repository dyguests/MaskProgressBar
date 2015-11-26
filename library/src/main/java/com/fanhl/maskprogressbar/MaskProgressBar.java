package com.fanhl.maskprogressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanhl on 15/11/26.
 */
public class MaskProgressBar extends View {
    

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

    }
}
