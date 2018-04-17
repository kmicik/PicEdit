package com.mangrummicik.picedit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class PicEditView extends View {

    private int mSize;
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 100;
    private final int MIN_SIZE = 5;

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
        mSize = DEFAULT_SIZE;
    }

    public int getSize() {
        return mSize;
    }

    public void changeSize(int increment) {
        this.mSize += increment;

        this.mSize = Math.max(mSize, MIN_SIZE);
        this.mSize = Math.min(mSize, MAX_SIZE);
    }
}
