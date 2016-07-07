package com.example.nikeru8.a012;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikeru8.a012.Question.MyApp;
import com.example.nikeru8.a012.Question.QuestionAdapter;
import com.example.nikeru8.a012.Question.QuestionAdapterFactory;
import com.example.nikeru8.a012.Question.UserAnswers;


// abstract activity 不需要宣告在 manifest
public abstract class QuestionActivity extends AppCompatActivity {
    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private Button m_btn_back;
    private Button m_btn_next;

    private static int sLastQuestionIndex;   // 上個畫面的 index
    private static int sQuestionIndex = 0;   // 只需要一個 index，所以宣告為靜態
    private static QuestionAdapter sAdapter; // 只需要一個 adapter，所以宣告為靜態

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initQuestions();
        initBackNextButtons();
        Log.d(this.toString(), "onCreate , index = " + sQuestionIndex);
        ActionBarView();
    }


    public void ActionBarView(){

        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundtext2));

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        menu.add(0, 1, android.view.Menu.NONE, "首頁");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case 1:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "首頁", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }





    // 抑制 setVisibility() 錯誤訊息
    @SuppressWarnings("ResourceType")
    private void initBackNextButtons() {
        m_btn_back = (Button)findViewById(R.id.btn_back);
        m_btn_next = (Button)findViewById(R.id.btn_next);


        m_btn_back.setVisibility(getBackButtonVisibility());
        m_btn_next.setVisibility(getNextButtonVisibility());
    }

    private void initQuestions() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        // 題號
        String no = String.valueOf(sQuestionIndex +1);
        m_tv_no.setText(no);


        if(sAdapter == null) {
            sAdapter = QuestionAdapterFactory.getQuestionAdapter();
        }

        m_tv_question.setText(sAdapter.getQuestion(sQuestionIndex));
        m_radio_a.setText(sAdapter.getQuestionOptionsA(sQuestionIndex));
        m_radio_b.setText(sAdapter.getQuestionOptionsB(sQuestionIndex));
        m_radio_c.setText(sAdapter.getQuestionOptionsC(sQuestionIndex));
    }

    // 按下 BACK
    public void back(View view) {
        sLastQuestionIndex = sQuestionIndex; // 準備要切換到前一個 Activity，備份目前 index
        sQuestionIndex--;  // 1
        // 建立新 Intent: new Intent( 來源 , 目的)
        Intent intent = new Intent(this, getBackActivityClass());
        // 將先前的 Acvivity 移到最上層，而非產生新的 Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        // overridePendingTransition( 進場效果 , 出場效果 )
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    // 按下 NEXT
    public void next(View view) {
        sLastQuestionIndex = sQuestionIndex; // 準備要切換到前一個 Activity，備份目前 index
        sQuestionIndex++;



        Intent intent = new Intent(this, getNextActivityClass());




        // 將先前的 Acvivity 移到最上層，而非產生新的 Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

        // overridePendingTransition( 進場效果 , 出場效果 )
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    // 設定 Back 按鈕文字
    public void setBackButtonText(CharSequence text) {
        m_btn_back.setText(text);
    }

    // 設定 Next 按鈕文字
    public void setNextButtonText(CharSequence text) {
        m_btn_next.setText(text);
    }

    // 按下 RadioButton 按鈕
    public void click(View view) {
        RadioButton radio = (RadioButton)view;
        UserAnswers userAnswers = MyApp.getUserAnswers();
        switch (radio.getId()) {
            case R.id.radio_a:
                userAnswers.setAnswer(sQuestionIndex, 'A', radio.getText());
                Log.d("QuestionActivity", "questionIndex=" + sLastQuestionIndex + " 選了 A");
                break;
            case R.id.radio_b:
                userAnswers.setAnswer(sQuestionIndex, 'B', radio.getText());
                Log.d("QuestionActivity", "questionIndex=" + sLastQuestionIndex + " 選了 B");
                break;
            case R.id.radio_c:
                userAnswers.setAnswer(sQuestionIndex, 'C', radio.getText());
                Log.d("QuestionActivity", "questionIndex=" + sLastQuestionIndex + " 選了 C");
                break;
        }
    }

    @Override
    public void onBackPressed() { // 當按下返回鍵
        return; // 直接返回不處理
    }

    @Override
    protected void onResume() { // 當畫面恢復(重現)，執行轉場動畫
        super.onResume();
        Log.d(this.toString(), "onResume , index = " + sQuestionIndex);
        if(sQuestionIndex < sLastQuestionIndex) { // 比較當前的 index 與 上個 Activity 的 index
            overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
        } else if(sQuestionIndex > sLastQuestionIndex) {
            overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.toString(), "onStart , index = " + sQuestionIndex);
    }

    @Override
    protected void onPause() { // 當畫面暫時離開
        super.onPause();
        Log.d(this.toString(), "onPause , index = " + sQuestionIndex);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.toString(), "onStop , index = " + sQuestionIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.toString(), "onDestroy , index = " + sQuestionIndex);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(this.toString(), "onRestart , index = " + sQuestionIndex);
    }

    // 索引編號歸零
    public static void resetQuestionIndex() {
        sQuestionIndex = 0;
        sLastQuestionIndex = 0;
    }


    protected abstract Class getBackActivityClass(); // 切換下個畫面的 Activity.class
    protected abstract Class getNextActivityClass(); // 切換上個畫面的 Activity.class
    protected abstract @Visibility int getBackButtonVisibility(); // Back 按鈕是否能被看見
    protected abstract  @Visibility int getNextButtonVisibility(); // Next 按鈕是否能被看見

    // @Visibility 返回的 int 只能是 View.VISIBLE View.INVISIBLE View.GONE 其中之一
    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.INVISIBLE;
    public static final int GONE = View.GONE;

    @IntDef({VISIBLE, INVISIBLE, GONE})
    public @interface Visibility {
    }


}