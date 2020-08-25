package com.example.myapplication


//class imageAdapter(val context:Context, val flickerImages:List<flickerImage>) : RecyclerView.Adapter<imageAdapter.ImageViewHolder>() {
//
//    inner class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
//    {
//        fun setData(image:flickerImage?)
//        {
//            Log.d("message in holder",image!!.name.toString())
//            itemView.textofview.text = image!!.name
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.layout,parent,false)
//        return ImageViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return flickerImages.size
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        val image = flickerImages[position]
//        holder.setData(image)
//    }
//}

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout.view.*


class PhotosAdapter(val photos: MutableList<Photo> = mutableListOf()) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }


    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {
            Picasso.get().load(photo.url).into(itemView.imageView)
        }
    }
}