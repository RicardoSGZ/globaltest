package com.rs1.globaltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideosActivity extends AppCompatActivity {
    private VideoView videoView;
    private VideoView videoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    protected void onStart() {
        super.onStart();
        videoView = findViewById(R.id.videoView1);
        videoView2 = findViewById(R.id.videoView2);
        initVideo(videoView, "trans");
        initVideo(videoView2, "http://xyz.rs1.es/public/otros/gala_aragoneses.mp4");
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView = findViewById(R.id.videoView1);
        videoView2 = findViewById(R.id.videoView2);
        releaseVideo(videoView);
        releaseVideo(videoView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView = findViewById(R.id.videoView1);
        videoView2 = findViewById(R.id.videoView2);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            pauseVideo(videoView);
            pauseVideo(videoView2);
        }
    }

    private Uri getVideo(String res) {
        if(URLUtil.isValidUrl(res)){
            return Uri.parse(res);
        }else{
            String url = "android.resource://" + getPackageName() + "/raw/" + res;
            return Uri.parse(url);
        }
    }

    private void initVideo(VideoView videoView, String res){
        final MediaController mediaController = new MediaController(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        videoView.setMediaController(mediaController);
        videoView.setVideoURI(getVideo(res));
        //videoView.start();
    }

    private void releaseVideo(VideoView videoView){
        videoView.stopPlayback();
    }

    private void pauseVideo(VideoView videoView) {
        videoView.pause();
    }


}
