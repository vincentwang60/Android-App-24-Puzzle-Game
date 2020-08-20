package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int x = 0;
    int y = 0;
    Card card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView number2 = (ImageView) findViewById(R.id.card1);
        card = new Card(number2,R.drawable.nines);
        /*final TextView number2 = (TextView) findViewById(R.id.number2);
        final TextView number3 = (TextView) findViewById(R.id.number3);
        final TextView number4 = (TextView) findViewById(R.id.number4);*/
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        x = (int)event.getX();
        y = (int)event.getY();
        card.setTouchPos(x,y);
        //card.update();
        int[] offset = {-100,-150};

        TextView text = (TextView) findViewById(R.id.text1);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                text.setText(Integer.toString(x)+','+Integer.toString(y));
            case MotionEvent.ACTION_UP:
        }

        return false;
    }
}
