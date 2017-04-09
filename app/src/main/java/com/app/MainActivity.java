package com.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mdrawerlayout;
    Fruit[] fruits={new Fruit("鸡蛋",R.mipmap.ti11),new Fruit("牛肉",R.mipmap.ti12),new Fruit("鸡肉",R.mipmap.ti13),
            new Fruit("海鲜",R.mipmap.ti14),new Fruit("鱼头",R.mipmap.ti15),new Fruit("火锅",R.mipmap.ti16),
            new Fruit("土豆",R.mipmap.ti17),new Fruit("米饭",R.mipmap.ti18),new Fruit("包子",R.mipmap.ti19),
    };
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toobar= (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toobar);
        mdrawerlayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        initFruits();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        GridLayoutManager layoutmanager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutmanager);
        adapter=new FruitAdapter(this,fruitList);
        recyclerView.setAdapter(adapter);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.fl11);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                mdrawerlayout.closeDrawers();
                return true;
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //本地数据刷新非常快，所以让线程沉睡2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //切换到主线程重新加载数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        //false表示刷新事件结束，隐藏进度条
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mdrawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.biaoji:
                Toast.makeText(this,"你好",Toast.LENGTH_SHORT).show();
                break;
            case R.id.dele:
                Toast.makeText(this,"我好",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"大家好",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
