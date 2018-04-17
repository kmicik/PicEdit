package com.mangrummicik.picedit;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private PicEditView drawingPad;
    Button redButton, blueButton, greenButton, resetButton, plusButton, minusButton;
    TextView sizeTv;
    private static final int SIZE_INCREMENT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        redButton = findViewById(R.id.red_button);
        redButton.setOnClickListener(this);

        blueButton = findViewById(R.id.blue_button);
        blueButton.setOnClickListener(this);

        greenButton = findViewById(R.id.green_button);
        greenButton.setOnClickListener(this);

        resetButton = findViewById(R.id.clear_button);
        resetButton.setOnClickListener(this);

        plusButton = findViewById(R.id.plus_button);
        plusButton.setOnClickListener(this);

        minusButton = findViewById(R.id.minus_button);
        minusButton.setOnClickListener(this);

        drawingPad = findViewById(R.id.drawingPad);

        sizeTv = findViewById(R.id.sizeTextView);
        sizeTv.setText("SIZE = " + drawingPad.getSize());

    }

    @Override
    public void onClick(View v) {

        Button b = findViewById(v.getId());

        switch (v.getId()) {
            case R.id.red_button:
                drawingPad.setPenColor(Color.RED);
                Log.d("Button test: ", b.getText() + "");
                break;
            case R.id.blue_button:
                drawingPad.setPenColor(Color.BLUE);
                Log.d("Button test: ", b.getText() + "");
                break;
            case R.id.green_button:
                drawingPad.setPenColor(Color.GREEN);
                Log.d("Button test: ", b.getText() + "");
                break;
            case R.id.plus_button:
                drawingPad.changeSize(+SIZE_INCREMENT);
                sizeTv.setText("SIZE = " + drawingPad.getSize());
                Log.d("Button test: ", b.getText() + "");
                break;
            case R.id.minus_button:
                drawingPad.changeSize(-SIZE_INCREMENT);
                sizeTv.setText("SIZE = " + drawingPad.getSize());
                Log.d("Button test: ", b.getText() + "");
                break;
            case R.id.clear_button:
                drawingPad.clear();
                sizeTv.setText("SIZE = " + drawingPad.getSize());
                Log.d("Button test: ", b.getText() + "");
                break;
        }
    }
}
