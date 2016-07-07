package com.example.nikeru8.a012;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
public class Camera extends AppCompatActivity {
    private LinearLayout layout;
    final static private int ShotPhoto = 0;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        init();
        ActionBarView();
    }


    public void ActionBarView(){

        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundtext2));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 2, Menu.NONE, "首頁");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case 2:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "首頁", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void init() {
        imageView = (ImageView) findViewById(R.id.Photo_Place);
        layout=(LinearLayout)findViewById(R.id.tv_layout);
    }

    public void onclick(View view) {

        //建立一個意圖(內建方法)
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //開始並回傳意圖
        startActivityForResult(intent, ShotPhoto);
    }


    //回傳意圖的地方，用此方法回傳意圖
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ShotPhoto && resultCode == RESULT_OK) {
            //Bitmap點陣圖
            //取得資源>>Data.getExtras().get("data");在轉型成Bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //設定點陣圖片
            imageView.setImageBitmap(bitmap);
            //設定圖片最適化
            imageView.setAdjustViewBounds(true);

            //控管下面的scroll
            ImageView imageview2=new ImageView(this);
            //設定點陣圖到imageView2中
            imageview2.setImageBitmap(bitmap);
            //設定最適合大小
            imageview2.setAdjustViewBounds(true);
            //每拍一張新增一張
            layout.addView(imageview2);


        }

    }

    @Override
    protected void onPause() {

        super.onPause();
    }



}