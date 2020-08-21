package edu.mit.vkwang;

import android.annotation.SuppressLint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Operation extends AppCompatActivity{
    ImageView opImg;
    boolean moved = false;
    boolean replaced = false;
    int type = 0;
    int resId = 0;
    private float xCoOrdinate;
    private float yCoOrdinate;

    @SuppressLint("ClickableViewAccessibility")
    public Operation(ImageView img, int gType,int gResId,final MainActivity test){
        resId = gResId;
        type = gType;
        opImg = img;
        opImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                test.makeNewOp(resId,type,resId);
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                }
                return true;
            }
        });

    }
}