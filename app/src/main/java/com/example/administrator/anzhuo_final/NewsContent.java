package com.example.administrator.anzhuo_final;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class NewsContent extends AppCompatActivity {
    private TextView title;
    private TextView content;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        Intent intent=getIntent();
        String Title=intent.getStringExtra("Title");
        String Content=intent.getStringExtra("Content");
        String url=intent.getStringExtra("VideoUrl");
        title=(TextView)findViewById(R.id.textView4);
        content=(TextView)findViewById(R.id.textView5);
        videoView=(VideoView)findViewById(R.id.videoView);
        title.setText(Title);
        content.setText(Content);
        if(!url.equals(""))
        {
            String vurl="http://192.168.0.11:8081/AndroidSever/video/";
            vurl+=url;
            Toast.makeText( NewsContent.this, vurl, Toast.LENGTH_SHORT).show();
            videoView.setMediaController(new MediaController(this));
            videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
            videoView.setVideoURI(Uri.parse(vurl));
            videoView.start();
        }


    }

    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( NewsContent.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
