package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    int x = 0;
    int y = 0;
    Card[] cards = new Card[4];
    Operation[] ops = new Operation[4];
    ConstraintLayout screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (ConstraintLayout) findViewById(R.id.screen);

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

        ops[0] = new Operation((ImageView) findViewById(R.id.op1),0,R.id.op1,this);
        ops[1] = new Operation((ImageView) findViewById(R.id.op2),0,R.id.op2,this);
        ops[2] = new Operation((ImageView) findViewById(R.id.op3),0,R.id.op3,this);
        ops[3] = new Operation((ImageView) findViewById(R.id.op4),0,R.id.op4,this);

        for(int i =0; i < ops.length;i++){
            if(ops[i].moved && !ops[i].replaced){
                makeNewOp(ops[i].resId,ops[i].type,ops[i].resId);
            }
        }
    }
    public void makeNewOp(final int resId, int type, int parentId){
        ImageView newOpImg = new ImageView(getApplicationContext());
        newOpImg.setImageResource(getResources().getIdentifier("minus", "drawable", getPackageName()));
        newOpImg.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
        ops = Arrays.copyOf(ops, ops.length + 1);
        ops[ops.length - 1] = new Operation(newOpImg,type,resId,this);
        screen.addView(newOpImg);
    }
}
