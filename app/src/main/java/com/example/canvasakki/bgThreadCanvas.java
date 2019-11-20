package com.example.canvasakki;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

public class bgThreadCanvas extends SurfaceView implements Runnable{

    Paint paint;
    int x =0;
    Thread t = null;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    boolean isRunning= false;

    public bgThreadCanvas(Context context) {
        super(context);

        inIt();
    }

    private void inIt() {

        paint = new Paint();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                startThread();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    void startThread()
    {
        t = new Thread(this);
        t.start();
    }


    @SuppressWarnings("WrongCall")
    @Override
    public void run() {

        do{


               try {
                   synchronized (this) {

                       Thread.sleep(100);
                       canvas = surfaceHolder.lockCanvas();
                       onDraw(canvas);
                   }

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               finally {
                   if(canvas != null)
                       surfaceHolder.unlockCanvasAndPost(canvas);
                   postInvalidate();
               }




        }while (isRunning);

    }



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(surfaceHolder.getSurface().isValid()) {
            canvas.drawColor(Color.LTGRAY);

            paint.setColor(Color.YELLOW);
            canvas.drawArc(500, 500, 800, 800, x + 0, 30, true, paint);
            canvas.drawArc(500, 500, 800, 800, x + 120, 30, true, paint);
            canvas.drawArc(500, 500, 800, 800, x + 240, 30, true, paint);


            x = x + 5;
        }
        // canvas.drawBitmap(bitmap,x,500,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                isRunning = true;
                startThread();
                break;
            case MotionEvent.ACTION_UP:
                stopThread();
                break;

        }
        return true;
    }

    private void stopThread() {
        try {
            isRunning = false;
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
