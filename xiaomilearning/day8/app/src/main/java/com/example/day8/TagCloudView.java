package com.example.day8;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TagCloudView extends View {
    private List<String> tags = new ArrayList<>();
    private int tagHorizontalPadding;
    private int tagVerticalPadding;
    private Paint tagPaint;
    private float offsetX = 0;
    private float lastTouchX = 0;
    private float offsetY = 0;
    private float lastTouchY = 0;
    private boolean isDragging = false;

    public TagCloudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCloudView);
        tagHorizontalPadding = a.getDimensionPixelSize(R.styleable.TagCloudView_tagHorizontalPadding, 24);
        tagVerticalPadding = a.getDimensionPixelSize(R.styleable.TagCloudView_tagVerticalPadding, 12);
        a.recycle();
        tagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tagPaint.setTextSize(40);
        tagPaint.setStyle(Paint.Style.STROKE);
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = offsetX + tagHorizontalPadding;
        float y = offsetY + tagVerticalPadding + 40;
        float maxWidth = getWidth();
        for (String tag : tags) {
            float textWidth = tagPaint.measureText(tag);
            float tagWidth = textWidth + tagHorizontalPadding * 2;
            if (x + tagWidth > maxWidth) {
                x = offsetX + tagHorizontalPadding;
                y += 40 + tagVerticalPadding * 2;
            }
            canvas.drawRect(x, y - 40, x + tagWidth, y + tagVerticalPadding, tagPaint);
            canvas.drawText(tag, x + tagHorizontalPadding, y, tagPaint);
            x += tagWidth + tagHorizontalPadding;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                isDragging = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    offsetX += event.getX() - lastTouchX;
                    offsetY += event.getY() - lastTouchY;
                    lastTouchX = event.getX();
                    lastTouchY = event.getY();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                return true;
        }
        return super.onTouchEvent(event);
    }
} 