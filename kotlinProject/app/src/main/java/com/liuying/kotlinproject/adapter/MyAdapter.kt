package com.liuying.kotlinproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.liuying.kotlinproject.R

/**
 * Created by liuying on 2017/11/9 15:40.
 * Email: ly1203575492@163.com
 */

class MyAdapter : BaseAdapter() {
  internal var mStrings: List<String>? = null

  override fun getCount(): Int {
    return if (mStrings == null) 0 else mStrings!!.size
  }

  override fun getItem(i: Int): String? {
    return if (mStrings == null) null else mStrings!![i]
  }

  override fun getItemId(i: Int): Long {
    return 0
  }

  override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
    var view = view
    val mViewHolder: ViewHolder
    if (view == null) {
      view = LayoutInflater.from(viewGroup.context).inflate(
          R.layout.item_layout, null)
      mViewHolder = ViewHolder(view)
      view!!.tag = mViewHolder
    } else {
      mViewHolder = view.tag as ViewHolder
    }

    mViewHolder.mBankBranchLabel.text = mStrings!![i]
    return view
  }
  

  internal class ViewHolder(view: View) {
    var mBankBranchLabel: TextView

    init {
      mBankBranchLabel = view.findViewById(R.id.item_text)
    }
  }
}
