package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Stoper extends AppCompatActivity {

    TextView TopicTextView;
    TextView TimerText;
    ImageView trainingImageView;
    Button StartButton;
    MediaPlayer applouse;
    MediaPlayer bipSound;
    boolean running;
    boolean isTraining=true;
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
        trainingImageView = findViewById(R.id.DeskaImageView);
        applouse = MediaPlayer.create(Stoper.this,R.raw.applouse);
        bipSound = MediaPlayer.create(Stoper.this,R.raw.bip);


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

    /// Stoper
    public void startTimer(){
        countDownTimer = new CountDownTimer(this.timeLeft,1000){

            @Override
            public void onTick(long l) {
                timeLeft = l;                       // aktualizuje czas
                updateTimerText();                  // aktualizuje text
            }

            @Override
            public void onFinish() {
                if(isTraining){
                    restTimer();

                }
                else{
                    seriaTimer();
                }

            }

        }.start();                                  // zaczynamy liczyc

        StartButton.setText("Zatrzymaj");          //jeśli leci czas to Start = Zatrzymaj
        running = true; // leci czas
    }



/////////////Funkcje Stopera//////////////
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
   /////odpoczynek
   public void restTimer(){

       TopicTextView.setText("Czas na odpoczynek");
       trainingImageView.setImageResource(R.drawable.woda);
       timeLeft = 20000;
       updateTimerText();
       startTimer();
       running = true;
       seria += 1;
       isTraining=false;
   }
   //////seria
    public void seriaTimer(){
        if(seria <=5){
            TopicTextView.setText("Trening początkujący" + "\n" + seria+ "/" + "5" );
            bipSound.start();                                                           //powiadomienie
            trainingImageView.setImageResource(R.drawable.deska);
            timeLeft = 30000;
            updateTimerText();
            startTimer();
            running = true;
            isTraining=true;
        }
        else{
            TopicTextView.setText("Udało Ci się! \n Ukończyłeś trening!");
            trainingImageView.setImageResource(R.drawable.pucahr);
            applouse.start();
            StartButton.setText("powrót");
            TimerText.setVisibility(TextView.INVISIBLE);                //ukrywamy stoper
            StartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToMainActivity();
                }
            });


        }
    }
    /////////////////// powrót do menu////////////////////
    private void moveToMainActivity(){
        Intent intent = new Intent(Stoper.this, MainActivity.class);
        startActivity(intent);
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
