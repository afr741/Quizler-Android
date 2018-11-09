package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.londonappbrewery.quizzler.R;
import com.londonappbrewery.quizzler.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static android.widget.Toast.makeText;

public class MainActivity extends Activity {
private TextView countLabel;
ProgressBar mProgressBar;
private TextView questionLabel;
private TextView quizTimer;
private Button answerButton1;
private Button answerButton2;
private Button answerButton3;
private Button answerButton4;
private CountDownTimer mCountDownTimer;
private String rightAnswer;
private int rightAnswerCount = 0;
private int quizCount = 1;

static final private int QUIZ_COUNT = 5;
static final long START_TIME_IN_MILIS = 60000;
private long mTimeLeftinMillis = START_TIME_IN_MILIS;
int PROGRESS_BAR_INCREMENT = 100/QUIZ_COUNT;
ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
String quizData[][] = {

        //{"Question", "right answer", "choicea", "choiceb", "choicec", "choiced"}
        {"Snow", "White", "Green", "Blue", "yellow"},
        {"Coal", "Black", "White", "Blue", "Orange"},
        {"Donald Trump", "Orange", "Black", "Green", "Blue"},
        {"Course", "cs3300", "cs4300", "cs1400", "cs4770"},
        {"Assignment number", "assign3", "assign2", "assign69", "assign5"}
};

private void startTimer()
{
 mCountDownTimer = new CountDownTimer(mTimeLeftinMillis, 1000) {
  @Override
  public void onTick(long millisUntilFinished) {
   mTimeLeftinMillis = millisUntilFinished;
   quizTimer.setText("Time: " + millisUntilFinished / 1000);
  }

  @Override
  public void onFinish() {
  quizTimer.setText("DONE!");
  }
 }.start();

}
/** SAMPLE TIMER
new CountDownTimer(30000, 1000) {

  public void onTick(long millisUntilFinished) {
   mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
  }

  public void onFinish() {
   mTextField.setText("done!");
  }
 }.start();
**/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
countLabel = (TextView)findViewById(R.id.countLabel);
questionLabel = (TextView)findViewById(R.id.question_text_view);
quizTimer = (TextView)findViewById(R.id.timer);
mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
answerButton1 = (Button)findViewById(R.id.answer1);
answerButton2 = (Button)findViewById(R.id.answer2);
answerButton3 = (Button)findViewById(R.id.answer3);
answerButton4 = (Button)findViewById(R.id.answer4);
startTimer();
//create quizArray from quizData

        for (int i = 0; i< quizData.length; i++)
        {

            //Prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Country
            tmpArray.add(quizData[i][1]); //Right Answer
            tmpArray.add(quizData[i][2]); //Choice1
            tmpArray.add(quizData[i][3]); //Choice2
            tmpArray.add(quizData[i][4]); //Choice3

            //Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }
        showNextQuiz();

}

public void showNextQuiz() {

        //Update quizCountLabel
    countLabel.setText("Q" + quizCount);
    //Generate random number between 0 and 14(quizArray's size -1).
    Random random = new Random();
    int randomNum = random.nextInt(quizArray.size());

    //Pick one quiz set.
    ArrayList<String> quiz = quizArray.get(randomNum);

    //set quetion and right answer.
    //Array format as above;
    questionLabel.setText(quiz.get(0));
    rightAnswer = quiz.get(1);

    //remove "Country" from quiz and Shuffle choices.
    quiz.remove(0);
 Collections.shuffle(quiz);

 //set choices
 answerButton1.setText(quiz.get(0));
 answerButton2.setText(quiz.get(1));
 answerButton3.setText(quiz.get(2));
 answerButton4.setText(quiz.get(3));

 quizArray.remove(randomNum);
}

public void checkAnswer(View view) {

 //Get pushed button

 Button answerBtn = (Button) findViewById(view.getId());
 String btnText = answerBtn.getText().toString();

 String alertTitle;

 if (btnText.equals(rightAnswer)) {

  //correct!
  alertTitle = "Correct";
 rightAnswerCount++;
 }
 else {
  //wrong
  alertTitle = "Wrong...";
 }

 AlertDialog.Builder builder = new AlertDialog.Builder(this);
 builder.setTitle(alertTitle);
 builder.setMessage("");

 builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
  @Override
  public void onClick(DialogInterface dialogInterface, int i) {
   if(quizCount == QUIZ_COUNT) {
    //show Result
    Intent intent = new Intent(getApplicationContext(), resultActivity.class);
       mProgressBar.setProgress(0);
    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
    startActivity(intent);
   }
   else {
      quizCount++;
    mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
      showNextQuiz();

   }
  }
 });
 builder.setCancelable(false);
 builder.show();
}
}



/**

 Toast mToastMessage; // For keeping track if a Toast message is being shown.

 // Create question bank using the Questions class for each item in the array
 @NonNull
 private Questions[] mQuestionBank = new Questions[] {
 new Questions(R.string.question_1, 4),
 new Questions(R.string.question_2, 4),
 new Questions(R.string.question_3, 4),
 new Questions(R.string.question_4, 4),
 new Questions(R.string.question_5, 4),
 new Questions(R.string.question_6, 2),
 new Questions(R.string.question_7, 3),
 new Questions(R.string.question_8, 4),
 new Questions(R.string.question_9, 1),
 new Questions(R.string.question_10, 2),
 new Questions(R.string.question_11, 1),
 new Questions(R.string.question_12, 2),
 new Questions(R.string.question_13,1)
 };





 // Declaring constants here. Rather than a fixed number, choosing to make it a function
 // of the length of the question bank (the number of questions)
 final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);



 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 // In API 26 'casting' is no longer needed.
 // For API 25 and lower: mTrueButton = (Button) findViewById(R.id.true_button);
 // For API 26 and higher can use: mTrueButton = findViewById(R.id.true_button);
 mTrueButton = findViewById(R.id.true_button);
 mFalseButton = findViewById(R.id.false_button);
 mFalseButton2 = findViewById(R.id.false_button2);
 mFalseButton3 = findViewById(R.id.false_button3);
 mQuestionTextView = findViewById(R.id.question_text_view);
 mScoreTextView = findViewById(R.id.score);
 mProgressBar = findViewById(R.id.progress_bar);

 // Restores the 'state' of the app upon screen rotation:
 if (savedInstanceState != null) {
 mScore = savedInstanceState.getInt("ScoreKey");
 mIndex = savedInstanceState.getInt("IndexKey");
 mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
 } else {
 mScore = 0;
 mIndex = 0;
 }

 mQuestion = mQuestionBank[mIndex].getQuestionID();
 mQuestionTextView.setText(mQuestion);

 mTrueButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 checkAnswer(1);
 updateQuestion();
 }
 });

 mFalseButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 checkAnswer(2);
 updateQuestion();
 }
 });

 mFalseButton2.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 checkAnswer(3);
 updateQuestion();
 }
 });

 mFalseButton3.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 checkAnswer(4);
 updateQuestion();
 }
 });

 }

 private void updateQuestion() {
 // This takes the modulus. Not a division.
 mIndex = (mIndex + 1) % mQuestionBank.length;

 // Present an alert dialog if we reach the end.
 if(mIndex == 0) {
 AlertDialog.Builder alert = new AlertDialog.Builder(this);
 alert.setTitle("Game Over");
 alert.setCancelable(false);
 alert.setMessage("You scored " + mScore + " points!");
 alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
 @Override
 public void onClick(DialogInterface dialog, int which) {
 finish();
 }
 });
 alert.show();
 }

 mQuestion = mQuestionBank[mIndex].getQuestionID();
 mQuestionTextView.setText(mQuestion);
 mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
 mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
 }

 private void checkAnswer(int userSelection) {

 int correctAnswer = mQuestionBank[mIndex].isAnswer();

 // Can cancel the Toast message if there is one on screen and a new answer
 // has been submitted.
 if (mToastMessage != null) {
 mToastMessage.cancel();
 }

 if(userSelection == correctAnswer) {
 mToastMessage = makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
 mScore = mScore + 1;

 } else {
 mToastMessage = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_LONG);
 }

 mToastMessage.show();

 }

 // This callback is received when the screen is rotated so we can save the 'state'
 // of the app in a 'bundle'.
 @Override
 public void onSaveInstanceState(Bundle outState){
 super.onSaveInstanceState(outState);

 outState.putInt("ScoreKey", mScore);
 outState.putInt("IndexKey", mIndex);
 }

 **/