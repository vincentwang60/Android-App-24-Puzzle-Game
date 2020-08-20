package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView image = (ImageView) findViewById((R.id.image1));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();
        int[] offset = {-30,-250};

        TextView text = (TextView) findViewById(R.id.text1);
        final ImageView image = (ImageView) findViewById((R.id.image1));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                text.setText(Integer.toString(x)+','+Integer.toString(y));
                image.setX((float) x+offset[0]);
                image.setY((float) y+offset[1]);
            case MotionEvent.ACTION_UP:
        }

        return false;
    }
}
