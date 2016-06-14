package com.example.echozhou.scrolldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by EchoZhou on 2016/6/14.
 */
public class MoveLayout extends View {
    private Scroller mScroller;
    private int lastX;
    private int lastY;
    private int offsetX;
    private int offsetY;
    private Paint paint;

    public MoveLayout(Context context) {
        super(context);
        initView(context);
    }

    public MoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(100,100,90,paint);
        super.onDraw(canvas);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setColor(Color.rgb(33, 65, 98));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = x - lastX;
                offsetY = y - lastY;
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                View group = ((View) getParent());
                group.scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                View view = ((View) getParent());
//                view.scrollBy(-view.getScrollX(),-view.getScrollY());
                Toast.makeText(getContext(),view.getScrollX()+""+view.getScrollY(),Toast.LENGTH_SHORT).show();
                mScroller.startScroll(view.getScrollX(),view.getScrollY(),-view.getScrollX(),-view.getScrollY(),1000);
                invalidate();
                break;
        }
        return true;
    }
}
