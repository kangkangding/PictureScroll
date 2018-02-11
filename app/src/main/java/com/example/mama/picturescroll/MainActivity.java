package com.example.mama.picturescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,View.OnTouchListener{

    GestureDetector mGestureDetector;
    private ImageView iv_picture,iv_picture2,iv_test;
    private ScrollView sv_scoll,sv_scoll2,sv_scoll3;
    private String TAG="MainActivity";

    private Button btn_caijian;
    private int huadongx;
    private int huadongy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        iv_picture2 = (ImageView) findViewById(R.id.iv_picture2);
        sv_scoll = (ScrollView) findViewById(R.id.sv_scoll);
        sv_scoll2 = (ScrollView) findViewById(R.id.sv_scoll2);
        sv_scoll3 = (ScrollView) findViewById(R.id.sv_scoll3);

        btn_caijian = (Button) findViewById(R.id.btn_caijian);
        iv_test = (ImageView) findViewById(R.id.iv_test);
        mGestureDetector = new GestureDetector(this);
        iv_picture.setOnTouchListener(this);
        sv_scoll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                huadongx = scrollX;
                huadongy = scrollY;
                Log.d(TAG,"scrollX:::::::::"+scrollX+":::::::::::scrollY::::::::::"+scrollY);
                Log.d(TAG,"oldScrollX:::::::::"+oldScrollX+":::::::::::oldScrollY::::::::::"+oldScrollY);
                sv_scoll2.scrollTo(scrollX,scrollY);
                sv_scoll3.scrollTo(scrollX/2,scrollY/2);
            }
        });
        btn_caijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_test.setImageBitmap(getBitmapByView(sv_scoll));
                getBitmapByView(sv_scoll);
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG,"按下");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG,"onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG,"onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"onFling");
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG,"onTouch");
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * 截取scrollview的屏幕
     * @param scrollView
     * @return
     */
    public  Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
//        util.dip2px(this,200)
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);

        Matrix matrix = new Matrix();
//        matrix.setTranslate(100f,100f);

        bitmap = Bitmap.createBitmap(bitmap,0,huadongy,bitmap.getWidth(),util.dip2px(this,200),matrix,false);

        return bitmap;
    }
}
