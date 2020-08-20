package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView number2 = (ImageView) findViewById(R.id.card1);
        card obj = new card(number2,R.drawable.nines);
        /*final TextView number2 = (TextView) findViewById(R.id.number2);
        final TextView number3 = (TextView) findViewById(R.id.number3);
        final TextView number4 = (TextView) findViewById(R.id.number4);*/
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();
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
