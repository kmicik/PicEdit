package com.mangrummicik.picedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PicEditView extends View implements View.OnTouchListener{

    private int mSize;
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 100;
    private final int MIN_SIZE = 5;
    private final int DEFAULT_COLOR = Color.WHITE;
    public static final int LINE = 1;
    public static final int RECTANGLE = 2;
    public static final int CIRCLE = 3;
    private int mPenColor;
    public int mCurrentShape;

    private ArrayList<Path> mPaths;
    private ArrayList<Paint> mPaints;

    private Path mPath;
    private Paint mPaint;
    private float mX, mY;

    public PicEditView(Context context) {
        super(context);
        this.init();
    }

    public PicEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public PicEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {

        this.mSize = DEFAULT_SIZE;
        this.mPenColor = DEFAULT_COLOR;
        this.mPaths = new ArrayList<Path>();
        this.mPaints = new ArrayList<Paint>();
        this.mPath = new Path();
        this.addPath();
        this.mX = mY = (float)0.0;
        this.setOnTouchListener(this);

    }

    private void addPath() {

        mPath = new Path();
        mPaths.add(mPath);
        mPaint = new Paint();
        mPaints.add(mPaint);
        mPaint.setColor(mPenColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSize);

    }

    public int getSize() {

        return mSize;

    }

    public int getPenColor() {

        return mPenColor;

    }

    public void setPenColor(int penColor) {

        this.mPenColor = penColor;

    }

    public void changeSize(int increment) {
        this.mSize += increment;

        this.mSize = Math.max(mSize, MIN_SIZE);
        this.mSize = Math.min(mSize, MAX_SIZE);
    }

    public void clear() {

        this.init();
        this.invalidate();

    }

/*///////////////////////////////////////////////////////////////////////////////////////////////////

    Line

/////////////////////////////////////////////////////////////////////////////////////////////////*/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mPaths.size(); ++i) {

            canvas.drawPath(mPaths.get(i), mPaints.get(i));

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        mX = event.getX();
        mY = event.getY();
        Log.d("Touched: ", "(" + mX + ", " + mY + ")");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.addPath();
                this.mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                this.mPath.lineTo(mX, mY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        this.invalidate();

        return true;
    }
}
