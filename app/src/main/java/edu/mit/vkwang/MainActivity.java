package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
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
    Card[] cards;
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
            int resID = getResources().getIdentifier(cardID, "id", "package.name");
            ImageView image = (ImageView) findViewById(resID);
            cards[i] = new Card(image, resID);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        x = (int)event.getX();
        y = (int)event.getY();
        for(Card card: cards){
                if(card.selected){
                    card.setTouchPos(x, y);
                }
        }
        //card.update();
        int[] offset = {-100,-150};

        TextView text = (TextView) findViewById(R.id.text1);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                text.setText(Integer.toString(x)+','+Integer.toString(y));
            case MotionEvent.ACTION_UP:
                for(Card card : cards){
                    card.selected = false;
                }
        }

        return false;
    }
}
