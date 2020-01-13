package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Stoper extends AppCompatActivity {

    TextView TopicTextView;
    TextView TimerText;
    Button StartButton;
    boolean running;
    private CountDownTimer countDownTimer;
    public long timeLeft = 30000; // 30 sec
    private int seria = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoper);

        TimerText = findViewById(R.id.TimerText);
        StartButton = findViewById(R.id.StartButton);
        TopicTextView = findViewById(R.id.TopicTextView);

        TopicTextView.setText("Trening początkujący" + "\n" + seria+ "/" + "5" );


        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimerText();
    }


    ////////////////////////////Metody/////////////////////////////////////////

    /// Timer
    public void startTimer(){
        countDownTimer = new CountDownTimer(this.timeLeft,1000){

            @Override
            public void onTick(long l) {
                timeLeft = l;                       // aktualizuje czas
                updateTimerText();                  // aktualizuje text
            }

            @Override
            public void onFinish() {
                odpoczynek();

            }

        }.start();                                  // zaczynamy liczyc

        StartButton.setText("Zatrzymaj");          //jeśli leci czas to Start = Zatrzymaj
        running = true; // leci czas
    }


    public void startStop (){
       if(running){
           stopTimer();
       }
       else{
           startTimer();
       }
    }

    public void stopTimer(){
        countDownTimer.cancel();                    //zatrzymujemy czas
        running = false;
        StartButton.setText("Wznów");               //jeśli wstrzymano to zatrzymaj = wznów
    }

    public void updateTimerText() {
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000; //zmieniamy czas z milisekund na sekundy i minuty

        String timeLeftText;


        timeLeftText = minutes + ":";
        if(seconds<10) timeLeftText += "0";
        timeLeftText += seconds;
        
        TimerText.setText(timeLeftText);            // tworzymy zmienna tekstowa do wyswietlania czasu i przypisujemy textVIew
   }
   public void odpoczynek(){

       TopicTextView.setText("Czas na odpoczynek");
       timeLeft = 20000;
       updateTimerText();
       startTimer();
       running = true;
       seria += 1;
   }







//    new CountDownTimer(30000, 1000) {
//
//        public void onTick(long millisUntilFinished) {
//            mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
//        }
//
//        public void onFinish() {
//            mTextField.setText("done!");
//        }
//    }.start();




}
