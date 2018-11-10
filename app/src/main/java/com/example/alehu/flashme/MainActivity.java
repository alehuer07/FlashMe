package com.example.alehu.flashme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("newQuestion"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("newAnswer");

            //Set text
            TextView newQuestionCard = findViewById(R.id.flashcard_question);
            newQuestionCard.setText(string1);
            TextView newAnswerCard = findViewById(R.id.flashcard_answer);
            newAnswerCard.setText(string2);
        }
    }

}
