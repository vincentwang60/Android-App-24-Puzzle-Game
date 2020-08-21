package edu.mit.vkwang;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity{
    ImageView card;
    boolean selected = false;
    private float xCoOrdinate;
    private float yCoOrdinate;

    @SuppressLint("ClickableViewAccessibility")
    public Card(ImageView gCard1, final int resId){
        card = gCard1;
        card.setImageResource(resId);
        card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
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
    public void setSelected(boolean gSelected){
        selected = gSelected;
    }

    public void setTouchPos(int x, int y){
        card.setX(x);
        card.setY(y);
    }
}