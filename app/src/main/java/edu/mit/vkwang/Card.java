package edu.mit.vkwang;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity{

    private ImageView img;
    private int value;
    private CardSlot cardSlot;
    private boolean firstTouch;
    private float startX;
    private float startY;
    private float oldCardSlotX;
    private float oldCardSlotY;
    private float xCoOrdinate;
    private float yCoOrdinate;

    @SuppressLint("ClickableViewAccessibility")
    public Card(ImageView img, int resId, int value, final CardSlot[] cardSlots){
        this.img = img;
        this.value = value;
        cardSlot = null;
        firstTouch = true;

        img.setImageResource(resId);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        if(firstTouch){
                            startX = view.getX();
                            startY = view.getY();
                            firstTouch = false;
                        }
                        if(cardSlot != null){
                            oldCardSlotX = view.getX();
                            oldCardSlotY = view.getY();
                        }
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate);
                        view.animate().y(event.getRawY() + yCoOrdinate);
                        view.animate().setDuration(0);
                        view.animate().start();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = startX;
                        float endY = startY;
                        float eventX = event.getRawX();
                        float eventY = event.getRawY();
                        CardSlot newCardSlot = null;

                        for(CardSlot s : cardSlots){
                            float cardSlotHeight = s.getDimensions()[0];
                            float cardSlotWidth = s.getDimensions()[1];
                            float cardSlotX = s.getDimensions()[2];
                            float cardSlotY = s.getDimensions()[3];

                            if(eventX > cardSlotX && eventX < cardSlotX + cardSlotWidth && eventY > cardSlotY && eventY < cardSlotY + cardSlotHeight){
                                newCardSlot = s;
                                endX = cardSlotX;
                                endY = cardSlotY;
                                break;
                            }
                        }

                        if(newCardSlot != null) {
                            addToCardSlot(newCardSlot);
                        }
                        else{
                            if(cardSlot != null) {
                                cardSlot.setCard(null);
                            }
                            cardSlot = null;
                        }

                        view.animate().x(endX);
                        view.animate().y(endY);
                }
                return true;
            }
        });
    }

    public void addToCardSlot(CardSlot cardSlot){
        Card toSwap = cardSlot.getCard();
        if(toSwap != null) {
            if(this.cardSlot != null){
                toSwap.setPosition(oldCardSlotX, oldCardSlotY);
                toSwap.setCardSlot(this.cardSlot);
                this.cardSlot.setCard(toSwap);
            }
            else{
                toSwap.resetPosition();
                toSwap.setCardSlot(null);
            }
        }
        else{
            if(this.cardSlot != null) {
                this.cardSlot.setCard(null);
            }
        }
        cardSlot.setCard(this);
        setCardSlot(cardSlot);
    }

    public void setCardSlot(CardSlot cardSlot){
        this.cardSlot = cardSlot;
    }

    public void setPosition(float x, float y){
        img.animate().x(x);
        img.animate().y(y);
    }
    public void resetPosition(){
        img.animate().x(startX);
        img.animate().y(startY);
    }

    public int getValue(){
        return value;
    }
}