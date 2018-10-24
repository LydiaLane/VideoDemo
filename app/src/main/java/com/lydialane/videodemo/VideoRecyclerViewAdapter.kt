//package com.lydialane.videodemo
//
//import android.content.Context
//import android.support.v7.widget.RecyclerView
//import android.view.ViewGroup
//import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
//
//
///**
// * Created by danylo.volokh on 9/20/2015.
// */
//class VideoRecyclerViewAdapter(
//    private val mVideoPlayerManager: VideoPlayerManager<*>,
//    private val mContext: Context,
//    private val mList: List<BaseVideoItem>
//) : RecyclerView.Adapter<VideoViewHolder>() {
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): VideoViewHolder {
//        val videoItem = mList[position]
//        val resultView = videoItem.createView(viewGroup, mContext.resources.displayMetrics.widthPixels)
//        return VideoViewHolder(resultView)
//    }
//
//    override fun onBindViewHolder(viewHolder: VideoViewHolder, position: Int) {
//        val videoItem = mList[position]
//        videoItem.update(position, viewHolder, mVideoPlayerManager)
//    }
//
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//}
