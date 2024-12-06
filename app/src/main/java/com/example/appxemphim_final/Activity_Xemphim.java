package com.example.appxemphim_final;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Xemphim extends AppCompatActivity {
    private ImageButton btn_back;
    private VideoView videoView;
    private SeekBar seekBar;
    private AudioManager audioManager;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xemphim);
        // ánh xạ
        btn_back = findViewById(R.id.icon_back_screen);
        videoView = findViewById(R.id.videoView);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.txt_screen_tenphim);

        // nhận dữ liệu từ sub
        Bundle bundle = getIntent().getExtras();
        int resource_video = bundle.getInt("resource_video");
        String tenPhim = bundle.getString("tenPhim");
        textView.setText("Bạn đang xem: "+tenPhim);
        // tao file uri
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+resource_video);
        videoView.setVideoURI(uri);

        // tao mediacontroller
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        // khoi tao audioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_back.setOnClickListener(view -> {
            finish();
        });
    }
    private void releaseResources() {
        if (videoView != null) {
            videoView.stopPlayback(); // Dừng phát video
            videoView = null; // Giải phóng tham chiếu videoView
        }
        if (audioManager != null) {
            audioManager = null; // Giải phóng tham chiếu audioManager
        }
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(null); // Xóa listener của SeekBar
            seekBar = null;
        }
    }
    @Override
    protected void onDestroy() {
        releaseResources();
        super.onDestroy();
    }
}