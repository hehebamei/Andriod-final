package com.example.administrator.anzhuo_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewsContent extends AppCompatActivity {
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        Intent intent=getIntent();
        String Title=intent.getStringExtra("Title");
        String Content=intent.getStringExtra("Content");
        title=(TextView)findViewById(R.id.textView4);
        content=(TextView)findViewById(R.id.textView5);
        title.setText(Title);
        content.setText(Content);
    }
}
