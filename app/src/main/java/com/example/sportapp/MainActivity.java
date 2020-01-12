package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonPRo, buttonEasy, buttonHard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEasy = findViewById(R.id.buttonEasy);
        buttonHard = findViewById(R.id.buttonHard);
        buttonPRo = findViewById(R.id.buttonPro);


        buttonEasy.setOnClickListener(new View.OnClickListener() { //dodanie funkcji button przejścia Stopera
            @Override
            public void onClick(View v) {
                moveToStoper();
            }
        });
        

    }

    private void moveToStoper(){
        Intent intent = new Intent(MainActivity.this, Stoper.class);
        startActivity(intent);
    }

    public Button getButtonEasy() {
        return buttonEasy;
    }
}
