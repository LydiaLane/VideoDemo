package com.lydialane.videodemo


import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.lydialane.videodemo.R

/**
 * This activity contains a fragment and gives the switch option between two fragments.
 * 1. [VideoRecyclerViewFragment]
 * 2. [VideoListFragment]
 */
class VideoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_list)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, VideoRecyclerViewFragment())
                .commit()
        }

//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        VideoView videoHolder = new VideoView(this);
////if you want the controls to appear
//        VideoViewHolder.setMediaController(new MediaController(this));
//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
//                + R.raw.your_raw_file); //do not add any extension
////if your file is named sherif.mp4 and placed in /raw
////use R.raw.sherif
//        videoHolder.setVideoURI(video);
//        setContentView(videoHolder);
//        videoHolder.start();
    }
}