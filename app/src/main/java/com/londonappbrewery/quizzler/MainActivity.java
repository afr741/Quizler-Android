package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuetionTextView;
    int mIndex;
    int mQuestion;
  //   TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuetionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestion = mQuestionBank[mIndex].getQuestionID();

        mQuetionTextView.setText(mQuestion);

        // create onclick listener object

        //set onclick listener to true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Quizzler", " True Button Clicked!!!");

                //Show toast message without creating new object
                Toast.makeText(getApplicationContext(),"True Button Clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        //creating anonymous listener for false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Quizzler", " False Button Clicked!!!");

                //create Toast message
                Toast toastMessage  = Toast.makeText(getApplicationContext(),"False Button Clicked!",Toast.LENGTH_SHORT);
                toastMessage.show();
            }
        });


        //TrueFalse exampleQuestion = new TrueFalse(R.string.question_1,true);


    }
}
