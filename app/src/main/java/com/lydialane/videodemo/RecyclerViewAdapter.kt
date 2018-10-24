package com.lydialane.videodemo

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.telecom.VideoProfile.isVideo
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView

class RecyclerViewAdapter(
    private val mVideoPlayerManager: VideoPlayerManager<*>,
    private val mContext: Context,
    private val mList: List<BaseVideoItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewVideo = 0
    private val viewImage = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val videoItem = mList[viewType]
        val resultView = videoItem.createView(viewGroup, mContext.resources.displayMetrics.widthPixels)

        if (viewType == viewVideo) {
            return VideoViewHolder(resultView)
        } else {
            return ImageViewHolder(resultView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val videoItem = mList[position]
        if ( holder.itemViewType == viewVideo) {
            videoItem.update(position, holder as VideoViewHolder, mVideoPlayerManager)
        }
//        else {
//            videoItem.update(position, ImageViewHolder(), mVideoPlayerManager)
//        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mPlayer: VideoPlayerView = itemView.findViewById(R.id.player)
        val mTitle: TextView = itemView.findViewById(R.id.title)
        val mCover: ImageView = itemView.findViewById(R.id.cover)
        val mVisibilityPercents: TextView = itemView.findViewById(R.id.visibility_percents)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mImage: ImageView = itemView.findViewById(R.id.cover)
        val mTitle: TextView = itemView.findViewById(R.id.title)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getItemViewType(position: Int) : Int {

        // check for file extension .mp4??
        if (isVideo(position)) {

            return viewVideo

        } else {

            return viewImage
        }
    }
}