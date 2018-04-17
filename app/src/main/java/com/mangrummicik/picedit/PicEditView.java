package com.mangrummicik.picedit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PicEditView extends View {

    private int mSize;
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 100;
    private final int MIN_SIZE = 5;
    private final int DEFAULT_COLOR = Color.WHITE;
    private final int DEFAULT_SHAPE = 1;
    public static final int LINE = 1;
    public static final int RECTANGLE = 2;
    public static final int CIRCLE = 3;
    private int mPenColor;
    public int mCurrentShape;
    protected Canvas mCanvas;
    protected Bitmap mBitmap;

    private ArrayList<Path> mPaths;
    private ArrayList<Paint> mPaints;

    private Path mPath;
    private Paint mPaint;
    private float mX, mY, oldMx, oldMy;

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    private void init() {

        this.mSize = DEFAULT_SIZE;
        this.mPenColor = DEFAULT_COLOR;
        this.mCurrentShape = DEFAULT_SHAPE;
        this.mPaths = new ArrayList<Path>();
        this.mPaints = new ArrayList<Paint>();
        this.mPath = new Path();
        this.addPath();
        this.mX = mY = oldMx = oldMy =(float)0.0;

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

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mCurrentShape) {
            case LINE:
                onDrawLine(canvas);
                break;
            case RECTANGLE:
                onDrawRect(canvas);
                break;
            case CIRCLE:
                onDrawCircle(canvas);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = event.getX();
        mY = event.getY();

        switch (mCurrentShape) {
            case LINE:
                onTouchEventLine(event);
                break;
            case RECTANGLE:
                onTouchEventRectangle(event);
                break;
            case CIRCLE:
                onTouchEventCircle(event);
                break;
        }
        return true;
    }

    /*----------------------------------------------------------------------------------------------

        Line

    ----------------------------------------------------------------------------------------------*/

    private void onDrawLine(Canvas canvas) {

        for (int i = 0; i < mPaths.size(); ++i)

            canvas.drawPath(mPaths.get(i), mPaints.get(i));


    }

    private void onTouchEventLine(MotionEvent event) {

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
    }
    /*----------------------------------------------------------------------------------------------

        Rectangle

    ----------------------------------------------------------------------------------------------*/

    private void onDrawRect (Canvas canvas) {

            drawRectangle(canvas, mPaint);

    }

    private void onTouchEventRectangle(MotionEvent event) {
        mX = event.getX();
        mY = event.getY();
        Log.d("Touched: ", "(" + mX + ", " + mY + ")");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //this.addPath();
                oldMx = mX;
                oldMy = mY;
                break;
            case MotionEvent.ACTION_MOVE:
                //this.mPath.lineTo(mX, mY);
                break;
            case MotionEvent.ACTION_UP:
                drawRectangle(mCanvas, mPaint);
                break;
        }
        this.invalidate();
    }

    private void drawRectangle(Canvas canvas, Paint paint) {
        float right = oldMx > mX ? oldMx : mX;
        float left = oldMx > mX ? mX : oldMx;
        float bottom = oldMy > mY ? oldMy : mY;
        float top = oldMy > mY ? mY : oldMy;
        canvas.drawRect(left, top , right, bottom, paint);
    }

    /*----------------------------------------------------------------------------------------------

        Circle

    ----------------------------------------------------------------------------------------------*/

    private void onDrawCircle (Canvas canvas) {

    }

    private void onTouchEventCircle(MotionEvent event) {

    }
}
