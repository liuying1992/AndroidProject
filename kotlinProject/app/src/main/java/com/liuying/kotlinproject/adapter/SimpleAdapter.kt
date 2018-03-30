package com.liuying.kotlinproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.liuying.kotlinproject.R

/**
 * Created by liuying on 2017/11/9 15:26.
 * Email: ly1203575492@163.com
 */
class SimpleAdapter : BaseAdapter() {
  //  val myData = List<>()
  var myData: List<String>? = null

  override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
    var rootView: View? = null

    if (rootView == null) {
      rootView = LayoutInflater.from(p2?.context).inflate(R.layout.item_layout, null)
    } else {

    }
    return rootView!!
  }

  override fun getItem(p0: Int): Any {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getItemId(p0: Int): Long {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getCount(): Int {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  open class MyViewHolder {
    
  }
}