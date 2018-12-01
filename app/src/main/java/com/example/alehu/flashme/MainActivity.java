package com.example.alehu.flashme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        //if list is not empty, display a saved flashcard.
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }


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

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("newQuestion"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("newAnswer");

            //Set text
            TextView newQuestionCard = findViewById(R.id.flashcard_question);
            newQuestionCard.setText(question);
            TextView newAnswerCard = findViewById(R.id.flashcard_answer);
            newAnswerCard.setText(answer);

            //insert new card into database
            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }

    List<Flashcard> allFlashcards;
    FlashcardDatabase flashcardDatabase;

    int currentCardDisplayedIndex = 0;



}
