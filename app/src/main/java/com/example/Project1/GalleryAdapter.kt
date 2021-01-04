package com.example.Project1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.R
import kotlinx.android.synthetic.main.grid_item.view.*
import java.util.*


class GalleryAdapter(val context: Context?, private val datasetList : MutableList<ImageData>, private val folderImageNum : MutableList<Int>) :
        RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    var mPosition = 0
    fun getPosition(): Int{
        return mPosition
    }
    fun setPosition(position:Int){
        mPosition = position
    }

    inner class GalleryViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private val gridImage = v.findViewById<ImageView>(R.id.imageView)
        @RequiresApi(Build.VERSION_CODES.P)
        fun bind(item: ImageData, context: Context){
            val uri = item.contentUri
            //Log.i("display",position.toString())
            //print(position)
            // if getPosition == 0:
            // add camerabtn

            //val context : Context
            //context.resources.getIdentifier(item.contentUri, "drawable", context.packageName)
            /*
            var id : Long,
            var dateTaken: Date,
            var displayName: String,
            var contentUri: Uri,
            var bucketID: Long,
            var bucketName: String*/
            val decode = ImageDecoder.createSource(context.contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(decode)
            gridImage.setImageBitmap(bitmap)
        }
    }


    fun addItem(new : ImageData){
        datasetList.add(new)
        notifyDataSetChanged()
    }

    fun removeItem(position : Int){
        if (position>0){
            datasetList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() : Int{
        return datasetList.size
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): GalleryViewHolder{
        val inflatedView = LayoutInflater.from(parent.context).inflate(
                com.example.project1.R.layout.grid_item,
                parent,
                false
        )
        return GalleryViewHolder(inflatedView)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int){
        val item = datasetList[position]
        if (context != null) {
            holder.bind(item, context)

            holder.itemView.setOnClickListener{
                v -> setPosition(position)
                //Toast.makeText(v.context, "click", Toast.LENGTH_SHORT).show()
                //open another activity
                //val intent = Intent(context, AnotherActivity::class.java)
                //val ImageViewClickListener : ImageClickListener = (ImageClickListener(context, position))
                //intent.putExtra("image_name", dataList[position].photo) // put image data in Intent
                //context.startActivity(intent) // start Intent
            }


        }
        /*
        holder.apply{
            bind(item)
        }*/
        // holder.itemView.setOnClickListener{
        //            Snackbar.make(holder.itemView, "힝 속았지", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null)
        //                .show()
        //        }
    }

}

