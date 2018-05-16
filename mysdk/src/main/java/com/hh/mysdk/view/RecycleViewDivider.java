package com.hh.mysdk.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerHeight = 2; //分割线高度
    private int mOrientation;
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public RecycleViewDivider(Context context, int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确的参数");
        }
        mOrientation = orientation;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public RecycleViewDivider(Context context,int orientation, int drawableId) {
        this(context,orientation);
        mDivider = ContextCompat.getDrawable(context,drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    public RecycleViewDivider(Context context,int orientation, int dividerHeight,int dividerColor) {
        this(context,orientation);
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
        Log.e("RecycleViewDivider","===============1");
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        if(mOrientation == LinearLayoutManager.HORIZONTAL){
//            //画横线，就是往下偏移一个分割线的高度
//            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//        }else {
//            //画竖线，就是往右偏移一个分割线的宽度
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//        }
        outRect.set(0,0,0,1);
    }


//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//        if (mOrientation == LinearLayoutManager.VERTICAL) {
//            drawVerticalLine(c,parent,state);
//        } else {
//            drawHorizontalLine(c,parent,state);
//        }
//    }
//
//    //画横线, 这里的parent其实是显示在屏幕显示的这部分
//    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++){
//            final View child = parent.getChildAt(i);
//
//            //获得child的布局信息
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
//            if (mDivider != null) {
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//            if (mPaint != null) {
//                c.drawRect(left,top,right,bottom,mPaint);
//            }
//
//
//            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
//        }
//    }
//
//    //画竖线
//    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
//        int top = parent.getPaddingTop();
//        int bottom = parent.getHeight() - parent.getPaddingBottom();
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++){
//            final View child = parent.getChildAt(i);
//
//            //获得child的布局信息
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
//            final int left = child.getRight() + params.rightMargin;
//            final int right = left + mDivider.getIntrinsicWidth();
//            if (mDivider != null) {
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//            if (mPaint != null) {
//                c.drawRect(left,top,right,bottom,mPaint);
//            }
//        }
//    }
}
