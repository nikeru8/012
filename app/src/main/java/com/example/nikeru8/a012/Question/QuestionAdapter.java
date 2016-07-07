package com.example.nikeru8.a012.Question;

/**
 * Created by nikeru8 on 2016/7/7.
 */
public interface QuestionAdapter {

    int getQuestionCount();

    CharSequence getQuestion(int index);
    CharSequence getQuestionOptionsA(int index);
    CharSequence getQuestionOptionsB(int index);
    CharSequence getQuestionOptionsC(int index);

}