package com.app;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DrawerActivity extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitiamgeId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar= (Toolbar) findViewById(R.id.fruit_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView imageView= (ImageView) findViewById(R.id.fruit_image_view);
        TextView textView= (TextView) findViewById(R.id.fruit_context_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle("满汉全席");
        Glide.with(this).load(fruitiamgeId).into(imageView);
        String fruitContent=generateFruitContent(fruitName);
        textView.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {

        StringBuilder fruitContent=new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
