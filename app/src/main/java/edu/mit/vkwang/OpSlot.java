package edu.mit.vkwang;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class OpSlot {

    private ImageView opSlot;
    private int resID;
    private Operation op;
    private float height;
    private float width;
    private float x;
    private float y;
    private float startX;
    private float startY;
    private float xCoOrdinate;
    private float yCoOrdinate;

    @SuppressLint("ClickableViewAccessibility")
    public OpSlot(final ImageView opSlot, int resID, final MainActivity mainActivity){
            this.opSlot = opSlot;
            this.resID = resID;
            op = null;
            opSlot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    height = opSlot.getHeight();
                    width = opSlot.getWidth();
                    x = opSlot.getLeft();
                    y = opSlot.getTop();

                    opSlot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

            opSlot.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if(op != null) {
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
                                OpSlot[] opSlots = mainActivity.opSlots;

                                if (!(eventX > x && eventX < x + width && eventY > y && eventY < y + height)) {
                                    setOp(null);
                                }
                                view.animate().x(startX);
                                view.animate().y(startY);
                        }
                    }
                    return true;
                }
            });
    }

    public void setOp(Operation toSet){
        op = toSet;
        if(op != null){
            int opResID = op.getResID();
            System.out.println("in opslot, resID: " + resID);
            opSlot.setImageResource(opResID);
        }
        else{
            opSlot.setImageResource(resID);
        }
    }

    public Operation getOp(){
        return op;
    }

    public float[] getDimensions(){
        return new float[]{height, width, x, y};
    }
}
