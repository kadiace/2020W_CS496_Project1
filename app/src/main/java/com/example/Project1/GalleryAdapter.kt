package com.example.project1
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
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
            holder.itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?){
                    val intent = Intent(context, ImageActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("uri", item.contentUri.toString())
                    intent.putExtras(bundle)    // intent 객체에 Bundle을 저장
                    ActivityCompat.startActivityForResult(context as Activity, intent, 1, bundle)
                }
                //val intent = Intent(context, ImageActivity)
                //intent.putExtra("FUCK", getPosition())
                //context.startActivity(intent)
                //Toast.makeText(v.context, "click", Toast.LENGTH_SHORT).show()
                //open another activity
                //
                //val ImageViewClickListener : ImageClickListener = (ImageClickListener(context, position))
                //intent.putExtra("image_name", dataList[position].photo) // put image data in Intent
                //context.startActivity(intent) // start Intent
            })
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