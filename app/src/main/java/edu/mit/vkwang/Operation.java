package edu.mit.vkwang;

import android.annotation.SuppressLint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Operation extends AppCompatActivity{

    private ImageView img;
    private int resID;
    private String type;
    private float xCoOrdinate;
    private float yCoOrdinate;
    private float startX;
    private float startY;

    @SuppressLint("ClickableViewAccessibility")
    public Operation(ImageView img, int resID, String type, final OpSlot[] opSlots){
        this.img = img;
        this.resID = resID;
        this.type = type;
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = view.getX();
                        startY = view.getY();
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
                        float eventX = event.getRawX();
                        float eventY = event.getRawY();
                        OpSlot newOpSlot = null;

                        for(OpSlot s : opSlots){
                            float opSlotHeight = s.getDimensions()[0];
                            float opSlotWidth = s.getDimensions()[1];
                            float opSlotX = s.getDimensions()[2];
                            float opSlotY = s.getDimensions()[3];

                            if(eventX > opSlotX && eventX < opSlotX + opSlotWidth && eventY > opSlotY && eventY < opSlotY + opSlotHeight){
                                newOpSlot = s;
                                break;
                            }
                        }

                        addToOpSlot(newOpSlot);

                        view.animate().x(startX);
                        view.animate().y(startY);
                }
                return true;
            }
        });
    }

    public void addToOpSlot(OpSlot opSlot){
        if(opSlot != null){
            opSlot.setOp(this);
        }
    }

    public int getResID(){
        System.out.println("in operation, resID: " + resID);
        return resID;
    }

    public String getType(){
        return type;
    }
}