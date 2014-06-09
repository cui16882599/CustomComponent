package com.example.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class IndicatorView extends View{

    private static final String TAG = "IndicatorView";
    
    private Bitmap mActive;
    private Bitmap mUnActive;
    private int current = 0;
    private int total = 0;
    
    private int bitmapWidth = 0;
    private int bitmapHeight = 0;
    private int space = 0;
    
    private Paint paint = new Paint();
    
    public IndicatorView(Context context){
        this(context,null);
    }
    
    // 重写这个方法就可以在布局文件中定义添加这个自定义View 
    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.Indicator);
        // 从xml布局文件中读取属性
        Drawable active = a.getDrawable(R.styleable.Indicator_actived);
        Drawable unactive = a.getDrawable(R.styleable.Indicator_unactived);
        if (active != null){
            mActive = ((BitmapDrawable)active).getBitmap();
            bitmapWidth = mActive.getWidth();
            bitmapHeight = mActive.getHeight();
        }
        if (unactive != null){
            mUnActive = ((BitmapDrawable)unactive).getBitmap();
        }
        space = a.getDimensionPixelSize(R.styleable.Indicator_space, 0);
        total = a.getInteger(R.styleable.Indicator_total, 0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w;
        int h;
        Log.d(TAG, "widthMeasureSpec="+Integer.toBinaryString(widthMeasureSpec)+
                "heightMeasureSpec="+Integer.toBinaryString(heightMeasureSpec));
        if (mActive == null || mUnActive == null) {
            bitmapWidth = -1;
            bitmapHeight = -1;
            w = h = 0;
        } else {
            w = bitmapWidth*total + space*(total-1);
            h = bitmapHeight;
            if (w <= 0) w = 1;
            if (h <= 0) h = 1;
        }
        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);
        Log.d(TAG, "onMeasure:"+"widthSize="+widthSize+" heightSize="+heightSize);
        //此行代码决定自定义的View的大小
        setMeasuredDimension(widthSize, heightSize);
        
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        // 当系统在屏幕上绘制这个View之前会将view的位置信息传这个方法
        Log.d(TAG, "onlayout:left="+left+"  top="+top+"  right="+right+"  bottom="+bottom);
    }
    
    /**
     * 这个方法是Android里所有View真正绘制图形的方法<br>
     * 通常我们使用的TextView、Button、EditText都会重写onDraw方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        if (mActive == null || mUnActive == null){
            Log.d(TAG, "do you set image src");
            return;
        }
        for (int i = 0; i < total ; i++){
            if(i == current){
                canvas.drawBitmap(mActive, i*bitmapWidth+space*i, 0, paint);
            } else {
                canvas.drawBitmap(mUnActive, i*bitmapWidth+space*i, 0, paint);
            }
        }
    }
    
    /**
     * 设置指示图标
     * @param active
     * @param unActive
     */
    public void setImageSrc(Drawable active, Drawable unActive){
        this.mActive = ((BitmapDrawable)active).getBitmap();
        this.mUnActive = ((BitmapDrawable)unActive).getBitmap();
        updateSrc();
    }
    
    /**
     * 更新指示个数
     * @param total
     */
    public void updateTotal(int total){
        this.total = total;
        requestLayout();
        invalidate();
    }
    
    /**
     * 设置当前处于第几个
     * @param curr
     */
    public void setCurr(int curr){
        this.current = curr;
    }
    
    /**
     * 设置间隔
     * @param space
     */
    public void setSpace(int space){
        this.space = space;
    }
    
    public void next(){
        if (current == (total - 1)){
            this.current = 0;
        } else {
            this.current += 1;
        }
        invalidate();
    }
    
    public void prev(){
        if (current == 0) {
            this.current = total-1;
        } else {
            this.current -= 1;
        }
        invalidate();
    }
    
    private void updateSrc(){
        bitmapWidth = mActive.getWidth();
        requestLayout();
//        invalidate();
    }
}
