package com.example.nikeru8.a012;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.nikeru8.a012.Question.Activity1;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener

{

    //常景變數
    private Context context;


    //適用於PageAdapter 在ViewPager 所變換的頁面(View)
    //打氣筒，用來
    private LayoutInflater inflater;
    private View page1, page2, page3;

    //適用於 Fragment_PageAdapter 在ViewPager 所變換的頁面(Fragment)，
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    //滑動指示-線
    private ImageView m_tv_line;

    //滑動指示-線的移動距離
    private int moviwOne = 0;

    //滑動指示-文字
    private TextView m_tv_tab1, m_tv_tab2, m_tv_tab3;

    //存放在ViewPager轉動的 頁面
    private List<View> list;
    private List<Fragment> list_fragment;
    private Fragment[] collection＿fragment;

    // ViewPager
    private ViewPager viewPager;
    // Adapter
    private PagerAdapter adapter;

    // RadioGroup
    private RadioGroup radioGroup;

    //紀錄上一次 移動頁面，
    private int offset = 0;

    //Flag 手指是否在滑动
    private boolean isScrolling = false;

    //Flag 手指离开后的回弹c
    private boolean isOutScrolling = false;

    private int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("mailto:nikeru888@gmail.com");
                Intent it=new Intent(Intent.ACTION_SENDTO,uri);
                startActivity(it);

                Snackbar.make(view, "聯絡我們", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    ActionBarView();
        findID();
        initial_Line();


        //Base_PageAdapter_start();
        Fragment_PageAdapter_start();
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    public void findID() {
        context = this;
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        m_tv_tab1 = (TextView) findViewById(R.id.tv_tab1);
        m_tv_tab2 = (TextView) findViewById(R.id.tv_tab2);
        m_tv_tab3 = (TextView) findViewById(R.id.tv_tab3);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        m_tv_line = (ImageView) findViewById(R.id.tv_line);
        RadioButton m_radiobutton1=(RadioButton)findViewById(R.id.radioButton1);


    }

    private void Base_PageAdapter_start() {

        inflater = getLayoutInflater();
        page1 = inflater.inflate(R.layout.view1, null);
        page2 = inflater.inflate(R.layout.view2, null);
        page3 = inflater.inflate(R.layout.view3, null);

        list = new ArrayList();
        list.add(page1);
        list.add(page2);
        list.add(page3);


        adapter = new MyPageAdapter(list);

        viewPager.setAdapter(adapter);


    }

    private void Fragment_PageAdapter_start() {

        //創建Fragment
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //Fragment 放入 集合中
        list_fragment = new ArrayList<>();
        list_fragment.add(fragment1);
        list_fragment.add(fragment2);
        list_fragment.add(fragment3);

        //Fragment 放入 陣列中
        collection＿fragment = new Fragment[]{fragment1, fragment2, fragment3};


        //創建FragmentAdapter
        MyFragmentPagerAdapter adapter;


// adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), collection＿fragment);


        //viewPager 裝一個監聽器（產生一個接口）
        viewPager.setOnPageChangeListener(this);

        //預設 大麥克堡 的文字為紅色
        m_tv_tab1.setTextColor(Color.WHITE);

        //裝入Adapter
        viewPager.setAdapter(adapter);


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_tab1:
                m_tv_tab1.setTextColor(Color.WHITE);
                m_tv_tab2.setTextColor(Color.BLACK);
                m_tv_tab3.setTextColor(Color.BLACK);
                radioGroup.check(R.id.radioButton1);



                //m_RadioButton1.isChecked();

                viewPager.setCurrentItem(0);
// Toast.makeText(context, "onClick tv_tab1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_tab2:
                m_tv_tab1.setTextColor(Color.BLACK);
                m_tv_tab2.setTextColor(Color.WHITE);
                m_tv_tab3.setTextColor(Color.BLACK);
                radioGroup.check(R.id.radioButton2);

// m_RadioButton2.isChecked();
                viewPager.setCurrentItem(1);
// Toast.makeText(context, "onClick tv_tab2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_tab3:
                m_tv_tab1.setTextColor(Color.BLACK);
                m_tv_tab2.setTextColor(Color.BLACK);
                m_tv_tab3.setTextColor(Color.WHITE);
// m_RadioButton3.isChecked();
                radioGroup.check(R.id.radioButton3);

                viewPager.setCurrentItem(2);
// Toast.makeText(context, "onClick tv_tab3", Toast.LENGTH_SHORT).show();
                break;

        }

    }


    // MainActivity implements ViewPager.OnPageChangeListener
    // 滑動的頁面 如何跟 MainActivity做溝通


    private void initial_Line() {

        //取得螢幕的解析度(width)
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int indext = dm.widthPixels;

        //取得 m_tv_line的Layout訊息包
        ViewGroup.LayoutParams lp = m_tv_line.getLayoutParams();
        lp.width = indext / 4;
        m_tv_line.setLayoutParams(lp);

        moviwOne = (lp.width) * 4 / 3;

//2

// float moviwOne_float = (float) moviwOne;
// Matrix matrix = new Matrix();
// matrix.postTranslate(indext/3,0);
// m_tv_line.setImageMatrix(matrix);
    }

    private void Current_movie_line(int arg0) {

/**
 *
 *float fromXDelta: 畫面一開始 View X坐标上數值；
 *float toXDelta, 畫面結束後，View X坐标上數值；
 *float fromYDelta, 畫面一開始 View Y坐标上數值；
 *float toYDelta) 畫面結束後，View Y坐标上數值；
 */
        //方法1 TranslateAnimation
// Animation animation2;
// animation2= new TranslateAnimation(offset * moviwOne, moviwOne*arg0, 0, 0);
// animation.setFillAfter(true);// True:图片停在动画结束位置
// animation.setDuration(500);
// m_tv_line.startAnimation(animation);

        //方法2 ObjectAnimator
        ObjectAnimator animation;
        animation = ObjectAnimator.ofFloat(m_tv_line, "translationX", offset * moviwOne, arg0 * moviwOne);

        //邏輯判斷用，初始位置=0，當滑動到=1 →View位置改變由0→1
        //邏輯判斷用，上個位置=1，當滑動到=2 →View位置改變由1→2
        //邏輯判斷用，上個位置=2，當滑動到=1 →View位置改變由2→1
        //邏輯判斷用，上個位置=1，當滑動到=0 →View位置改變由1→0
        offset = arg0;

        animation.setDuration(200);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.start();
       // Toast.makeText(context, "第" + arg0+"頁", Toast.LENGTH_SHORT).show();



    }

    private void Current_movie_line(int arg0, float positionOffset) {
/**
 *
 *float fromXDelta: 畫面一開始 View X坐标上數值；
 *float toXDelta, 畫面結束後，View X坐标上數值；
 *float fromYDelta, 畫面一開始 View Y坐标上數值；
 *float toYDelta) 畫面結束後，View Y坐标上數值；
 */
        //頁面0→頁面1 =


        float start = m_tv_line.getTranslationX();
// float start2 = offset * moviwOne+(boleen)*positionOffset;
        float end = arg0 * moviwOne + positionOffset;


        ObjectAnimator animation;
        animation = ObjectAnimator.ofFloat(
                m_tv_line, "translationX"
                , start
                , end);

// Log.d("tag getTranslationX ", "getTranslationX= " + m_tv_line.getTranslationX());
        offset = arg0;

        animation.setDuration(400);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.start();
        Toast.makeText(context, "您选择了" + arg0, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //ViewPager跳转到新页面时触发该方法
    @Override
    public void onPageSelected(int position) {
// Toast.makeText(context, "onPageSelected First" , Toast.LENGTH_SHORT).show();
// Log.d("tag",position+"");
// movie_line(position);
        switch (position) {
            case 0:
                Current_movie_line(0);
                m_tv_tab1.setTextColor(Color.WHITE);
                m_tv_tab2.setTextColor(Color.BLACK);
                m_tv_tab3.setTextColor(Color.BLACK);
                radioGroup.check(R.id.radioButton1);

// Toast.makeText(context, "onPageSelected " + (position + 1), Toast.LENGTH_SHORT).show();

                break;
            case 1:
                Current_movie_line(1);
                m_tv_tab1.setTextColor(Color.BLACK);
                m_tv_tab2.setTextColor(Color.WHITE);
                m_tv_tab3.setTextColor(Color.BLACK);
                radioGroup.check(R.id.radioButton2);

// Toast.makeText(context, "onPageSelected " + (position + 1), Toast.LENGTH_SHORT).show();

                break;
            case 2:
                Current_movie_line(2);
                m_tv_tab1.setTextColor(Color.BLACK);
                m_tv_tab2.setTextColor(Color.BLACK);
                m_tv_tab3.setTextColor(Color.WHITE);
                radioGroup.check(R.id.radioButton3);

// Toast.makeText(context, "onPageSelected " + (position + 1), Toast.LENGTH_SHORT).show();

                break;
        }


    }




    public void ActionBarView(){

        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundtext2));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(context, "首頁", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_facebook) {
            Uri uri=Uri.parse("https://www.facebook.com/QuanAlley/");
            Intent it=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(it);

        } else if (id == R.id.nav_asks) {
Intent Question=new Intent(this,Activity1.class);
            startActivity(Question);
        } else if (id == R.id.nav_camera) {
Intent camera=new Intent(this,Camera.class);
            startActivity(camera);
        } else if (id == R.id.nav_order) {
            Intent camera=new Intent(this,order.class);
            startActivity(camera);
        } else if (id == R.id.nav_map) {
Intent map=new Intent(this,map.class);
            startActivity(map);
        } else if (id == R.id.nav_send) {
            Uri uri=Uri.parse("mailto:nikeru888@gmail.com");
            Intent it=new Intent(Intent.ACTION_SENDTO,uri);
            startActivity(it);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
