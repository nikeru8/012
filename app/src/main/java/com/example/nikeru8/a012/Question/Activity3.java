package com.example.nikeru8.a012.Question;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.nikeru8.a012.MainActivity;
import com.example.nikeru8.a012.QuestionActivity;


public class Activity3 extends QuestionActivity {

    @Override
    protected void onStart() {
        super.onStart();
        setNextButtonText("Finish"); // 呼叫父類別寫好的功能
    }

    @Override
    protected Class getBackActivityClass() {
        return Activity2.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return null;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    public void next(View view) {

        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        UserAnswers userAnswers = MyApp.getUserAnswers();
        StringBuilder message = new StringBuilder();

        message.append("您的作答如下\n\n");
        for(int i = 0 ; i < adapter.getQuestionCount() ; i++) {
            message.append(String.valueOf(i+1))
                    .append(": ")
                    .append(userAnswers.getAnswer(i))
                    .append("\n")
                    .append(userAnswers.getDescription(i))
                    .append("\n\n");

        }
        message.append("\n確定要結束?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                        startActivity(intent); // 回主畫面
                        QuestionActivity.resetQuestionIndex(); // 索引編號歸零
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}