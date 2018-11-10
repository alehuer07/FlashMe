package com.example.alehu.flashme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If the Question is showing, then clicking the Question will show the Answer
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        //If the Answer is showing, then clicking the Answer will show the Question
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });


        //When answerA is clicked, it will turn red, and the correct answer will turn green.
        findViewById(R.id.answerA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answerA).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                findViewById(R.id.answerB).setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
            }
        });
        //When answerB is clicked it will turn green.
        findViewById(R.id.answerB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answerB).setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
            }
        });
        //When answerC is clicked, it will turn red, and the correct answer will turn green.
        findViewById(R.id.answerC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answerC).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                findViewById(R.id.answerB).setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
            }
        });


    }
}
