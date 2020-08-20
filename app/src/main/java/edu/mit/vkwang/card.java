package edu.mit.vkwang;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class card extends AppCompatActivity{
    public card(ImageView gCard1,int resId){
        ImageView card1 = gCard1;
        card1.setImageResource(resId);
    }
}