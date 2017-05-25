package com.jimmy.waterripple;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


public class WaterRipple extends View {

    private static final int INT_WAVE_LENGTH = 500;
    private int range = 60;
    private int height = 100;
    private float faction;

    private Paint mPaint;

    private Path mPath;

    private PathMeasure mPathMeasure;


    public WaterRipple(Context context) {
        this(context, null);
    }

    public WaterRipple(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(-INT_WAVE_LENGTH + faction * INT_WAVE_LENGTH, getHeight() - height);
        for (int x = 0; x < getWidth() + INT_WAVE_LENGTH; x += INT_WAVE_LENGTH) {
            mPath.rQuadTo(INT_WAVE_LENGTH / 4, range, INT_WAVE_LENGTH / 2, 0);
            mPath.rQuadTo(INT_WAVE_LENGTH / 4, -range, INT_WAVE_LENGTH / 2, 0);
        }
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);

        mPathMeasure = new PathMeasure(mPath, false);



    }


    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                faction = (float) animation.getAnimatedValue();
                if (height < getHeight() - range) {
                    height += 1;
                }
                postInvalidate();
            }
        });
        animator.start();
    }
}
