package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import kotlinx.android.synthetic.main.activity_image.*

class activity_image : AppCompatActivity() {
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photosAdapter = PhotosAdapter()
    private var flag = false
    private var currentItem = 0
    private var scrolledItem = 0
    private var  totalItem = 0
    private var page_counter = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val bundle : Bundle?  = intent.extras
        val message = bundle!!.get("message").toString()
        Log.d("Message",message)

        val layoutManager = GridLayoutManager(this,3)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = photosAdapter
        photosViewModel.photosLiveData.observe(this,
            Observer { list ->
                with(photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })
        photosViewModel.fetchImages(message,page_counter)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.d("Message ","Scroll state changed")
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    flag = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = layoutManager.childCount
                totalItem = layoutManager.itemCount
                scrolledItem = layoutManager.findFirstVisibleItemPosition()
                Log.d("Message ","Data fetched")
                if(flag && (currentItem + scrolledItem == totalItem))
                {
                    photosViewModel.fetchImages(message,page_counter)
                    page_counter += 1
                }

            }
        })
//        photosViewModel.fetchImages(message,1)
    }
}


