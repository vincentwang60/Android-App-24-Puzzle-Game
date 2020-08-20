package edu.mit.vkwang;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity{
    int x = 0;
    int y = 0;
    ImageView card;
    public Card(ImageView gCard1,int resId){
        card = gCard1;
        card.setImageResource(resId);
    }
    public void setTouchPos(int gX,int gY){
        x = gX;
        y = gY;
        card.setX(x);
        card.setY(y);
    }
}