package com.example.canvasakki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

import java.nio.file.Path;

import androidx.annotation.RequiresApi;

public class mainThreadCanvas extends View {


    Paint paint;
    Bitmap bitmap;
    int x;

    public mainThreadCanvas(Context context) {
        super(context);

        paint = new Paint();
        x = 0;
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.iron);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.LTGRAY);
        paint.setColor(Color.WHITE);
        canvas.drawRect(100,100,500,500,paint);

        paint.setColor(Color.YELLOW);
        canvas.drawArc(500,500,800,800,x+0,30,true,paint);
        canvas.drawArc(500,500,800,800,x+120,30,true,paint);
        canvas.drawArc(500,500,800,800,x+240,30,true,paint);


       // canvas.drawBitmap(bitmap,x,500,null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                startFan();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }


        return true;
    }

    private void startFan() {
        x = x+5;
        postInvalidate();
    }
}
