package edu.mit.vkwang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndScreen extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);
    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}