package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    int x = 0;
    int y = 0;
    Card[] cards = new Card[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String string = "";
        ArrayList<String> combos = new ArrayList<String>();
        InputStream is = this.getResources().openRawResource(R.raw.combinations);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            combos.add(string);
        }

        Collections.shuffle(combos);
        String comboStr = combos.remove(0);
        combos.add(comboStr);

        String[] cardIDs = comboStr.split("\\s+");
        String[] suits = new String[]{"s","c","d","h"};

        for(int i = 0; i < cards.length; i++){
            String suit = suits[(int)(Math.random() * suits.length)];
            String cardID = cardIDs[i] + suit;
            int resID = getResources().getIdentifier(cardID, "drawable", getPackageName());

            ImageView image = null;
            if(i == 0){
                image = (ImageView) findViewById(R.id.card1);
            }
            else if(i == 1){
                image = (ImageView) findViewById(R.id.card2);
            }
            else if(i == 2){
                image = (ImageView) findViewById(R.id.card3);
            }
            else{
                image = (ImageView) findViewById(R.id.card4);
            }
            cards[i] = new Card(image, resID);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        x = (int)event.getX();
        y = (int)event.getY();

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
