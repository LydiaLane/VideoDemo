package com.lydialane.videodemo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.volokh.danylo.video_player_manager.ui.VideoPlayerView

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val mPlayer: VideoPlayerView = view.findViewById(R.id.player) as VideoPlayerView
    val mTitle: TextView = view.findViewById(R.id.title) as TextView
    val mCover: ImageView = view.findViewById(R.id.cover) as ImageView
    val mVisibilityPercents: TextView = view.findViewById(R.id.visibility_percents) as TextView

}