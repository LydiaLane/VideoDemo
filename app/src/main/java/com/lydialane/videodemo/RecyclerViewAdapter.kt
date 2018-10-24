package com.lydialane.videodemo

import android.content.Context
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.telecom.VideoProfile.isVideo
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lydialane.videodemo.R.id.parent
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView

class RecyclerViewAdapter(
    private val mVideoPlayerManager: VideoPlayerManager<*>,
    private val mContext: Context,
    private val mList: List<BaseVideoItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewVideo = 0
    private val viewImage = 1

//    override fun onBindViewHolder(videoViewHolder: VideoViewHolder, imageViewHolder: ImageViewHolder, position : Int) {
//        val videoItem = mList[position]
//        videoItem.update(position, videoViewHolder, mVideoPlayerManager)
//
//        val imageItem = mList[position]
//        videoItem.update(position, imageViewHolder, mVideoPlayerManager)
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if ( holder.itemViewType == viewVideo) {
            videoItem.update(position, VideoViewHolder(), mVideoPlayerManager)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val videoItem = mList[viewType]

        if (viewType == 0) {
            val resultView = videoItem.createView(parent, mContext.resources.displayMetrics.widthPixels)
            return resultView
        }
    } else {
        return ImageViewHolder(parent)
    }

//        when (viewType) {
//            0 -> return VideoViewHolder(parent)
//            1 -> return ImageViewHolder(parent)
//        }
//        return VideoViewHolder(parent)
    }

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mPlayer: VideoPlayerView = itemView.findViewById(R.id.player)
        val mTitle: TextView = itemView.findViewById(R.id.title)
        val mCover: ImageView = itemView.findViewById(R.id.cover)
              //  val mVisibilityPercents: TextView = itemView.findViewById(R.id.visibility_percents)
        }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val mImage: ImageView = itemView.findViewById(R.id.cover)
        val mTitle: TextView = itemView.findViewById(R.id.title)
    }

    override fun getItemViewType(position: Int) : Int {

        // check for file extension .mp4??
        if (isVideo(position)) {

            return viewVideo

        } else {
            return viewImage
        }
         //   return position % 2 * 2
    }

//    private boolean isVideo(position){
//
//        String filename=list.get(position); //list Your Strings
//        String filenameArray[] = filename.split("\\.");
//        String extension = filenameArray[filenameArray.length-1];
//        return extension=="mp4"; //change this to more flexible
//    }
}