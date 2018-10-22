//package com.lydialane.videodemo
//
//import android.app.Activity
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//
//import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback
//import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator
//import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator
//import com.volokh.danylo.visibility_utils.items.ListItem
//import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter
//import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter
//import kotlinx.android.synthetic.main.activity_video_list.*
//
//import java.util.ArrayList
//import java.util.Arrays
//
///**
// * Created by danylo.volokh on 08.01.2016.
// */
//class VisibilityUtilsFragment : Fragment() {
//
//    private var mRecyclerView: RecyclerView? = null
//    private var mLayoutManager: LinearLayoutManager? = null
//   // private var mVisibilityUtilsCallback: VisibilityUtilsCallback? = null
//
//    private val mListItemVisibilityCalculator =
//        SingleListViewItemActiveCalculator(DefaultSingleItemCalculatorCallback(), mList)
//
//    private var mItemsPositionGetter: ItemsPositionGetter? = null
//
//    private var mScrollState: Int = 0
//    private var mToast: Toast? = null
//
////    override fun onAttach(activity: Activity?) {
////        super.onAttach(activity)
////        mVisibilityUtilsCallback = activity as VisibilityUtilsCallback?
////    }
//
////    override fun onDetach() {
////        super.onDetach()
////        mVisibilityUtilsCallback = null
////    }
//
//    override fun onCreateView(savedInstanceState: Bundle?): View? {
//
//        mRecyclerView = fragment_container.findViewById(R.id.recycler_view)
//
//        mRecyclerView!!.setHasFixedSize(true)
//
//        // use a linear layout manager
//        mLayoutManager = LinearLayoutManager(activity)
//        mRecyclerView!!.layoutManager = mLayoutManager
//
//        val adapter = VisibilityUtilsAdapter(mList)
//
//        mRecyclerView!!.adapter = adapter
//        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
//                mScrollState = scrollState
//                if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()) {
//
//                    mListItemVisibilityCalculator.onScrollStateIdle(
//                        mItemsPositionGetter,
//                        mLayoutManager!!.findFirstVisibleItemPosition(),
//                        mLayoutManager!!.findLastVisibleItemPosition()
//                    )
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                if (!mList.isEmpty()) {
//                    mListItemVisibilityCalculator.onScroll(
//                        mItemsPositionGetter,
//                        mLayoutManager!!.findFirstVisibleItemPosition(),
//                        mLayoutManager!!.findLastVisibleItemPosition() - mLayoutManager!!.findFirstVisibleItemPosition() + 1,
//                        mScrollState
//                    )
//                }
//            }
//        })
//
//        adapter.notifyDataSetChanged()
//
//
//        mItemsPositionGetter = RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView)
//        return root
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (!mList.isEmpty()) {
//            // need to call this method from list view handler in order to have filled list
//
//            mRecyclerView!!.post {
//                mListItemVisibilityCalculator.onScrollStateIdle(
//                    mItemsPositionGetter,
//                    mLayoutManager!!.findFirstVisibleItemPosition(),
//                    mLayoutManager!!.findLastVisibleItemPosition()
//                )
//            }
//        }
//    }
//}