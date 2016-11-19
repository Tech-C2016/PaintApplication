package com.example.teacher.paintapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacher on 2016/11/12.
 */
public class PaintView extends View {

    private float mOldX;
    private float mOldY;
    private Path mPath;
    private List<Path> mListPath;

    public PaintView(Context context) {
        super(context);
        mListPath = new ArrayList<>();
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mListPath == null) {
            return;
        }

        Paint paint = new Paint();

        // 線の情報を設定
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        for (Path path : mListPath) {
            canvas.drawPath(path, paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath = new Path();

                mOldX = event.getX();
                mOldY = event.getY();
                mPath.moveTo(mOldX,mOldY);

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                mOldX += (event.getX() - mOldX) / 1.5 ;
                mOldY += (event.getY() - mOldY) / 1.5 ;
                mPath.lineTo(mOldX,mOldY);

                if(event.getAction() == MotionEvent.ACTION_UP){
                    mListPath.add(mPath);
                }

                invalidate();

                break;

        }

        return true;

    }
}
