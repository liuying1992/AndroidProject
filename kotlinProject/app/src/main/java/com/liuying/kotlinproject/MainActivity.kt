package com.liuying.kotlinproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.liuying.kotlinproject.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    var data: List<Integer>? = null

    if (data == null) {

    }


    runOnUiThread(Runnable {

    })
    val tv = findViewById<TextView>(R.id.txt_content)
    tv.text = "sdasdasd"

    tv.visibility = View.GONE


    val btn = findViewById<Button>(R.id.btn)
    btn.setOnClickListener(View.OnClickListener {

    })


    val listView = findViewById<ListView>(R.id.list_view)

    listView.adapter = MyAdapter()




    listView.setOnItemClickListener { adapterView, view, position, l ->

    }

    txt_content.text = "你好"
    btn.setOnClickListener(
        View.OnClickListener { Toast.makeText(this, "点击了我", Toast.LENGTH_SHORT).show() })
  }

}


