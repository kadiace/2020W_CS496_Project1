package com.example.Project1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.GridView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.R
import kotlinx.android.synthetic.main.grid_item.view.*
import java.util.*

class GalleryViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v
    fun bind(item: ImageData){
        val uri = item.contentUri
        /*
        var id : Long,
        var dateTaken: Date,
        var displayName: String,
        var contentUri: Uri,
        var bucketID: Long,
        var bucketName: String*/
    }
}


class GalleryAdapter(val context: Context?, private val datasetList : MutableList<ImageData>, private val folderImageNum : MutableList<Int>) :
        RecyclerView.Adapter<GalleryViewHolder>(){

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

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int){
        val item = datasetList[position]
        holder.apply{
            bind(item)
        }
    }
    /*
   override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val photo: Image = photoList[position]
        val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(convertView == null) {
            val convertView = inflater.inflate(R.layout.grid_item, null)
        }
        val photoView: View = inflater.inflate(R.layout.grid_item, null)
        photoView.imageView.setBackgroundResource(photo.image!!)
        return photoView
    }

    override fun getItem(position: Int): Any{
        return position
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }*/

}

/*
class GalleryAdapter(val context: Context?, private val photoList: ArrayList<Image>) :
    BaseAdapter(){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val photo: Image = photoList[position]
        val inflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(convertView == null) {
            val convertView = inflater.inflate(R.layout.grid_item, null)
        }
        val photoView: View = inflater.inflate(R.layout.grid_item, null)
        photoView.imageView.setBackgroundResource(photo.image!!)
        return photoView
    }

    override fun getItem(position: Int): Any{
        return position
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }
    override fun getCount(): Int{
        return photoList.size
    }
}*/



/*
if(null == view) {
    var bmp: Bitmap =
        BitmapFactory.decodeResource(context.getResources(), photoList[position])
    bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);
    //---------------------------------------------------------------
    // GridView 뷰를 구성할 ImageView 뷰들을 정의합니다.
    // 뷰에 지정할 이미지는 앞에서 정의한 비트맵 객체입니다.
    var imageview : ImageView? = null
    imageView = new ImageView (context)
    imageView.setAdjustViewBounds(true)
    imageView.setImageBitmap(bmp)

    //---------------------------------------------------------------
    // 지금은 사용하지 않는 코드입니다.

    //imageView.setMaxWidth(320);
    //imageView.setMaxHeight(240);
    //imageView.setImageResource(imageIDs[position]);

    //---------------------------------------------------------------
    // 사진 항목들의 클릭을 처리하는 ImageClickListener 객체를 정의합니다.
    // 그리고 그것을 ImageView의 클릭 리스너로 설정합니다.
    /*
    ImageClickListener imageViewClickListener
    = new ImageClickListener(context, imageIDs[position]);
    imageView.setOnClickListener(imageViewClickListener);*/

}
return imageView
// xml에 ImageVeiw를 참조합니다.
//view.findViewById(R.id.)

// Get the custom view widgets reference
val tv = view.findViewById<TextView>(R.id.tv_name)
val card = view.findViewById<CardView>(R.id.card_view)
// Display color name on text view
tv.text = list[position].first
// Set background color for card view
card.setCardBackgroundColor(list[position].second)
// Finally, return the view
return view

}
*/