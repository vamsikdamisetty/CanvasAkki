package com.example.canvasakki;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class customET extends AppCompatEditText {

    Drawable LIGHT , DARK;

    public customET(Context context, AttributeSet attrs) {
        super(context, attrs);
        inIt();
    }

    private void inIt() {
        LIGHT = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_clear_black_24dp_light,null);
        DARK = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_clear_black_24dp,null);
        addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {  //start count after
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {  //start before count
                showButton();
            }
            @Override
            public void afterTextChanged(Editable editable)
            {//Do something if change is done
            }
        });
        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                boolean isClicked =false;
                float clearButtonStart = getWidth()-getPaddingEnd()-LIGHT.getIntrinsicWidth();
                if(motionEvent.getX() > clearButtonStart)
                {
                    isClicked = true;
                }
                if(isClicked) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            getText().clear();
                            break;
                        case MotionEvent.ACTION_UP:
                            showDarkButton();
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void showButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,LIGHT,null);

    }
    public void showDarkButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,DARK,null);

    }
}
