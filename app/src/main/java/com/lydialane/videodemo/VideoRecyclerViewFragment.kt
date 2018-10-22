package com.lydialane.videodemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.volokh.danylo.video_player_manager.Config
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter
import java.io.IOException
import java.util.ArrayList

/**
 * This fragment shows of how to use [VideoPlayerManager] with a RecyclerView.
 */
class VideoRecyclerViewFragment : Fragment() {

    private val mList = ArrayList<BaseVideoItem>()

    /**
     * Only the one (most visible) view should be active (and playing).
     * To calculate visibility of views we use [SingleListViewItemActiveCalculator]
     */
    private val mVideoVisibilityCalculator =
        SingleListViewItemActiveCalculator(DefaultSingleItemCalculatorCallback(), mList)

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null

    /**
     * ItemsPositionGetter is used by [ListItemsVisibilityCalculator] for getting information about
     * items position in the RecyclerView and LayoutManager
     */
    private var mItemsPositionGetter: ItemsPositionGetter? = null

    /**
     * Here we use [SingleVideoPlayerManager], which means that only one video playback is possible.
     */
    private val mVideoPlayerManager = SingleVideoPlayerManager(PlayerItemChangeListener { })

    private var mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_video_recycler_view, container, false)

        try {
            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )
            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )

            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )
            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )

            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )
            mList.add(
                ItemFactory.createItemFromAsset(
                    "samplevideo.mp4", R.drawable.video_thumbnail_1,
                    activity!!, mVideoPlayerManager
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }


        mRecyclerView = rootView.findViewById(R.id.recycler_view)
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = mLayoutManager

        val videoRecyclerViewAdapter = VideoRecyclerViewAdapter(mVideoPlayerManager, activity!!, mList)

        mRecyclerView!!.adapter = videoRecyclerViewAdapter
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, scrollState: Int) {
                mScrollState = scrollState
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()) {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                        mItemsPositionGetter,
                        mLayoutManager!!.findFirstVisibleItemPosition(),
                        mLayoutManager!!.findLastVisibleItemPosition()
                    )
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!mList.isEmpty()) {
                    mVideoVisibilityCalculator.onScroll(
                        mItemsPositionGetter,
                        mLayoutManager!!.findFirstVisibleItemPosition(),
                        mLayoutManager!!.findLastVisibleItemPosition() - mLayoutManager!!.findFirstVisibleItemPosition() + 1,
                        mScrollState
                    )
                }
            }
        })
        mItemsPositionGetter = RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        if (!mList.isEmpty()) {
            // need to call this method from list view handler in order to have filled list

            mRecyclerView!!.post {
                mVideoVisibilityCalculator.onScrollStateIdle(
                    mItemsPositionGetter,
                    mLayoutManager!!.findFirstVisibleItemPosition(),
                    mLayoutManager!!.findLastVisibleItemPosition()
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // we have to stop any playback in onStop
        mVideoPlayerManager.resetMediaPlayer()
    }

    companion object {

        private val SHOW_LOGS = Config.SHOW_LOGS
        private val TAG = VideoRecyclerViewFragment::class.java.simpleName
    }
}