package edu.mit.vkwang;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity{
    ImageView card;

    public Card(ImageView gCard1,int resId){
        card = gCard1;
        card.setImageResource(resId);
        card.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                setTouchPos(x, y);
                return true;
            }
        });
    }
    public void setTouchPos(int x, int y){
        card.setX(x);
        card.setY(y);
    }
}