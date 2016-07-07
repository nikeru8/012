package com.example.nikeru8.a012;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class order extends AppCompatActivity {
    private Context context;
    private TextView textView1;
    private TextView textView2;
    private ListView listView;
    private SimpleAdapter adapter;
    /*下拉式選單*/
    private Spinner spinner;
    private List<String> adapterlist;
    private ArrayAdapter<String> arrayadapter;

    /**/
    private List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findID();
        initaladapter();


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
    public void initaladapter() {
        adapterlist = new ArrayList<>();
        adapterlist.add("湯品");
        adapterlist.add("肉品");
        adapterlist.add("創意料理");
        adapterlist.add("蔬菜、海鮮");
        adapterlist.add("甜點、飲料");

        arrayadapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, adapterlist);

        spinner.setAdapter(arrayadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterlist.get(position) == "湯品") {
                    startzero();

                } else if (adapterlist.get(position) == "肉品") {
                    startone();


                } else if (adapterlist.get(position) == "創意料理") {
                    starttwo();

                } else if (adapterlist.get(position) == "蔬菜、海鮮") {
                    startthree();

                } else if (adapterlist.get(position) == "甜點、飲料"){
                    startfour();

                }


                //Toast.makeText(context, adapterlist.get(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    public void findID() {
        spinner = (Spinner) findViewById(R.id.spinner);
        context = this;
        listView = (ListView) findViewById(R.id.listView1);
        textView1 = (TextView) findViewById(R.id.textView11);
        textView2 = (TextView) findViewById(R.id.textView22);

    }

    public void startzero() {
        //步驟一 先創建MAP
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "紅泥小染");
        map1.put("price", "四川鍋");
        map1.put("imageID", R.drawable.hotspot);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "元珠小染");
        map2.put("price", "台式鍋");
        map2.put("imageID", R.drawable.hotpot);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "白鳳千叟");
        map3.put("price", "養身鍋");
        map3.put("imageID", R.drawable.hotpot);
        //步驟二，list 放入Map
        list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);


        //步驟三 創建一個adapter
        adapter = new SimpleAdapter(
                context, list, R.layout.item
                , new String[]{"name", "price", "imageID"}
                , new int[]{R.id.textView11, R.id.textView22, R.id.imageView}
        );


        //ListView 設定進去
        listView.setAdapter(adapter);
    }


    //肉品
    public void startone() {

        //步驟一 先創建MAP
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "頂級和牛");
        map1.put("price", "500$");
        map1.put("imageID", R.drawable.beef1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "牛小排");
        map2.put("price", "500$");
        map2.put("imageID", R.drawable.beef2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "牛里肌");
        map3.put("price", "500$");
        map3.put("imageID", R.drawable.beef3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "綿羊豬");
        map4.put("price", "500$");
        map4.put("imageID", R.drawable.beef4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "霜降背肩");
        map5.put("price", "500$");
        map5.put("imageID", R.drawable.beef5);

        Map<String, Object> map6 = new HashMap<>();
        map5.put("name", "玫瑰松坂");
        map5.put("price", "500$");
        map5.put("imageID", R.drawable.combo1);

        //步驟二，list 放入Map
        list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);


        //步驟三 創建一個adapter
        adapter = new SimpleAdapter(
                context, list, R.layout.item
                , new String[]{"name", "price", "imageID"}
                , new int[]{R.id.textView11, R.id.textView22, R.id.imageView}
        );


        //ListView 設定進去
        listView.setAdapter(adapter);
    }

    //創意料理
    public void starttwo() {

        //步驟一 先創建MAP
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "好彩頭");
        map1.put("price", "500$");
        map1.put("imageID", R.drawable.good);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "枝仔冰");
        map2.put("price", "500$");
        map2.put("imageID", R.drawable.good2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "波波糖");
        map3.put("price", "500$");
        map3.put("imageID", R.drawable.good3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "千層蝦夷");
        map4.put("price", "500$");
        map4.put("imageID", R.drawable.good4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "墨條牛軋");
        map5.put("price", "500$");
        map5.put("imageID", R.drawable.good5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("name", "拼圖");
        map6.put("price","500$");
        map6.put("imageID", R.drawable.good6);

        //步驟二，list 放入Map
        list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);


        //步驟三 創建一個adapter
        adapter = new SimpleAdapter(
                context, list, R.layout.item
                , new String[]{"name", "price", "imageID"}
                , new int[]{R.id.textView11, R.id.textView22, R.id.imageView}
        );


        //ListView 設定進去
        listView.setAdapter(adapter);
    }

    //蔬菜海鮮
    public void startthree() {

        //步驟一 先創建MAP
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "花團錦簇");
        map1.put("price", "500$");
        map1.put("imageID", R.drawable.combo2);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "海鮮");
        map2.put("price", "500$");
        map2.put("imageID", R.drawable.ocean);


        //步驟二，list 放入Map
        list = new ArrayList<>();
        list.add(map1);
        list.add(map2);


        //步驟三 創建一個adapter
        adapter = new SimpleAdapter(
                context, list, R.layout.item
                , new String[]{"name", "price", "imageID"}
                , new int[]{R.id.textView11, R.id.textView22, R.id.imageView}
        );


        //ListView 設定進去
        listView.setAdapter(adapter);
    }

    //甜點飲料
    public void startfour() {

        //步驟一 先創建MAP
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "水果醋");
        map1.put("price", "500$");
        map1.put("imageID", R.drawable.juice);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "歐雷雪漾");
        map2.put("price", "500$");
        map2.put("imageID", R.drawable.sweet4);


        //步驟二，list 放入Map
        list = new ArrayList<>();
        list.add(map1);
        list.add(map2);


        //步驟三 創建一個adapter
        adapter = new SimpleAdapter(
                context, list, R.layout.item
                , new String[]{"name", "price", "imageID"}
                , new int[]{R.id.textView11, R.id.textView22, R.id.imageView}
        );


        //ListView 設定進去
        listView.setAdapter(adapter);
    }

}
