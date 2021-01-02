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


class GalleryAdapter(val context: Context?, private val datasetList : List<ImageData>, private val folderImageNum : MutableList<Int>) :
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
        //datasetList.add(new)
        notifyDataSetChanged()
    }

    fun removeItem(position : Int){
        if (position>0){
            //datasetList.removeAt(position)
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
                Toast.makeText(v.context, "click", Toast.LENGTH_SHORT).show()
                //open another activity
                //val intent = Intent(context, AnotherActivity::class.java)
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