package edu.mit.vkwang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout screen;
    private ArrayList<String> combos;
    private CardSlot[] cardSlots;
    private Card[] cards;
    private OpSlot[] opSlots;
    private Operation[] ops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        screen = (ConstraintLayout) findViewById(R.id.screen);

        readComboFile();
        initCardSlots();
        initCards();
        initOpSlots();
        initOps();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

    }

    public boolean checkWin(){
        for(CardSlot s : cardSlots){
            if(s.getCard() == null){
                return false;
            }
        }

        for(OpSlot s : opSlots){
            if(s.getOp() == null){
                return false;
            }
        }

        int result = cardSlots[0].getCard().getValue();
        for(int i = 0; i < opSlots.length; i++){
            String type = opSlots[i].getOp().getType();
            int value =  cardSlots[i + 1].getCard().getValue();
            if(type.equals("+")){
                result += value;
            }
            else if(type.equals("-")){
                result -= value;
            }
            else if(type.equals("*")){
                result *= value;
            }
            else{
                result /= value;
            }
        }

        return result == 24;
    }

    public void readComboFile(){
        String string = "";
        combos = new ArrayList<String>();
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
    }

    public void initCardSlots(){
        cardSlots = new CardSlot[4];
        cardSlots[0] = new CardSlot((ImageView) findViewById(R.id.cardSlot1));
        cardSlots[1] = new CardSlot((ImageView) findViewById(R.id.cardSlot2));
        cardSlots[2] = new CardSlot((ImageView) findViewById(R.id.cardSlot3));
        cardSlots[3] = new CardSlot((ImageView) findViewById(R.id.cardSlot4));
    }

    public void initCards(){
        Collections.shuffle(combos);
        String comboStr = combos.remove(0);
        combos.add(comboStr);

        String[] cardNums = comboStr.split("\\s+");
        String[] suits = new String[]{"s","c","d","h"};
        String[] values = new String[]{"a", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        cards = new Card[4];
        for(int i = 0; i < cards.length; i++){
            String suit = suits[(int)(Math.random() * suits.length)];
            String cardID = cardNums[i] + suit;
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

            int value = -1;
            for(int j = 0; j < values.length; j++){
                if(cardNums[i].equals(values[j])){
                    value = j + 1;
                    break;
                }
            }
            cards[i] = new Card(image, resID, value, cardSlots);
        }
    }

    public void initOpSlots(){
        int resID = getResources().getIdentifier("emptyop", "drawable", getPackageName());
        opSlots = new OpSlot[3];
        opSlots[0] = new OpSlot((ImageView) findViewById(R.id.opSlot1), resID);
        opSlots[1] = new OpSlot((ImageView) findViewById(R.id.opSlot2), resID);
        opSlots[2] = new OpSlot((ImageView) findViewById(R.id.opSlot3), resID);
    }

    public void initOps(){
        ops = new Operation[4];

        String[] types = new String[]{"+", "*", "-", "/"};
        for(int i = 0; i < ops.length; i++){
            String opID = null;
            ImageView image = null;
            if(i == 0){
                opID = "plus";
                image = (ImageView) findViewById(R.id.op1);
            }
            else if(i == 1){
                opID = "times";
                image = (ImageView) findViewById(R.id.op2);
            }
            else if(i == 2){
                opID = "minus";
                image = (ImageView) findViewById(R.id.op3);
            }
            else{
                opID = "divide";
                image = (ImageView) findViewById(R.id.op4);
            }
            int resID = getResources().getIdentifier(opID, "drawable", getPackageName());
            ops[i] = new Operation(image, resID, types[i], opSlots);
        }
    }

    public void newGame(){

    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
    }
}
