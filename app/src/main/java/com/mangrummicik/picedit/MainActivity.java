package com.mangrummicik.picedit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private PicEditView drawingPad;
    Button redButton, blueButton, greenButton, resetButton, plusButton, minusButton;
    TextView sizeTv;

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

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        plusButton = findViewById(R.id.plus_button);
        plusButton.setOnClickListener(this);

        minusButton = findViewById(R.id.minus_button);
        minusButton.setOnClickListener(this);

        sizeTv = findViewById(R.id.sizeTextView);

        drawingPad = findViewById(R.id.drawingPad);
    }

    @Override
    public void onClick(View v) {

        Button b = findViewById(v.getId());

        switch (v.getId()) {
            case R.id.red_button:
                Log.d("Button pressed: ", b.getText() + "");
                break;
            case R.id.blue_button:
                Log.d("Button pressed: ", b.getText() + "");
                break;
            case R.id.green_button:
                Log.d("Button pressed: ", b.getText() + "");
                break;
            case R.id.plus_button:
                Log.d("Button pressed: ", b.getText() + "");
                break;
            case R.id.minus_button:
                Log.d("Button pressed: ", b.getText() + "");
                break;
        }
    }
}
