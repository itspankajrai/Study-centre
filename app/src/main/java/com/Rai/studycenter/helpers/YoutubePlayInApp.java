package com.Rai.studycenter.helpers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.Rai.studycenter.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YoutubePlayInApp extends AppCompatActivity {
    String TAG="erros";
    String video_id;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        video_id=getIntent().getStringExtra("video_id");
        setContentView(R.layout.activity_youtube_player);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(video_id, 0);
                }
            });


        Log.d(TAG, "onCreate() returned: " +video_id );

    }

    @Override
    protected void onStart() {
        super.onStart();
        youTubePlayerView.setEnableAutomaticInitialization(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() returned: stooped"  );
        youTubePlayerView.release();
    }
}