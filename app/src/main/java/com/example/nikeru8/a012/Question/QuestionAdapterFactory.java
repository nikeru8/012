package com.example.nikeru8.a012.Question;
import android.content.res.Resources;

/**
 * Created by nikeru8 on 2016/7/7.
 */
public class QuestionAdapterFactory {
private static QuestionAdapter adapter;

public static QuestionAdapter getQuestionAdapter() {
        if(adapter == null) {
        // 透過 MyApp 取得 Context，進一步取得 Resources
        Resources res = MyApp.getContext().getResources();
        adapter = new QuestionFromStringResource(res);
        }
        return adapter;
        }

// 私有建構子
private QuestionAdapterFactory() { // 產生轉接器
        }

        }