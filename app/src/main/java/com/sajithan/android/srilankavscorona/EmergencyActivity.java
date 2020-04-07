package com.sajithan.android.srilankavscorona;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EmergencyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive;
    public static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        initWidgets();

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Emergency Numbers");


    }

    private void initWidgets() {

       buttonOne = findViewById(R.id.button_one);
       buttonTwo = findViewById(R.id.button_two);
       buttonThree = findViewById(R.id.button_three);
       buttonFour = findViewById(R.id.button_four);
       buttonFive = findViewById(R.id.button_five);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_one:
                String numOne = "118 / 119";
                makePhoneDial(numOne);
                break;

            case R.id.button_two:
                String numTwo = "110";
               makePhoneDial(numTwo);
                break;

            case R.id.button_three:
                String numThree = "0112691111";
                makePhoneDial(numThree);
                break;

            case R.id.button_four:
                String numFour = "011-2421052";
                makePhoneDial(numFour);
                break;

            case R.id.button_five:
                String numFive = "0112433333";
                makePhoneDial(numFive);
                break;
            case R.id.button_six:
                String numSix = "1919";
                makePhoneDial(numSix);

            case R.id.button_seven:
                String numSeven = "0112691500";
                makePhoneDial(numSeven);
                break;
            case R.id.button_eight:
                String numEight = "0115717171";
                makePhoneDial(numEight);
                break;
            case R.id.button_nine:
                String numNine = "0112422222";
                makePhoneDial(numNine);
                break;
        }
    }


    private void makePhoneDial(String number) {

        Intent i = new Intent(Intent.ACTION_DIAL);
        if(number.isEmpty()) {

            i.setData(Uri.parse("tel:7788994455"));
        } else {
            i.setData(Uri.parse("tel:"+number));
        }
        startActivity(i);
    }
}
