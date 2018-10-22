package com.lydialane.videodemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
// * Created by danylo.volokh on 06.01.2016.
// */
//
//class VisibilityUtilsAdapter(private val mList: List<BaseVideoItem>) : RecyclerView.Adapter<VideoViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.visibility_adapter_item, parent, false)
//        val layoutParams = parent.layoutParams
//        layoutParams.height = parent.resources.displayMetrics.widthPixels
//        return VideoViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//
//    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
//
//        val item = mList[position]
//        item.onBindViewHolder(holder, position)
//    }
//
//}