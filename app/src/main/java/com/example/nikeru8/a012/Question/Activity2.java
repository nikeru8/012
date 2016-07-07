package com.example.nikeru8.a012.Question;


import com.example.nikeru8.a012.QuestionActivity;

public class Activity2 extends QuestionActivity {

    @Override
    protected Class getBackActivityClass() {
        return Activity1.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return Activity3.class;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }
}