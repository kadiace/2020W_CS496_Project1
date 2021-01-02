package com.example.project1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log
import android.widget.GridView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Project1.ImageData
import com.example.Project1.GalleryAdapter
import com.example.Project1.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_2.view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Fragment2 : Fragment() {
    //private val Gallery = 0
    //private var imageList = emptyArray<Image>()

        /*
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_2)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ImageDataset = getFileList(requireContext(), MediaStoreFileType.IMAGE)
        val folderDataset = mutableListOf<ImageData>()
        val countImages = mutableListOf<Int>()
        val folderId = mutableListOf<Long>()
        print(ImageDataset.size)

        val condition: (ImageData, ImageData) -> Boolean =
            { mdf1: ImageData, mdf2: ImageData -> mdf1.bucketID == mdf2.bucketID }
        ImageDataset.forEach {
            if (listContainsContitionedItem(folderDataset, it, condition).not()) {
                folderDataset.add(it)
                countImages.add(1)
                folderId.add(it.bucketID)
            } else {
                countImages[folderId.indexOf(it.bucketID)] += 1
            }
        }

        val recyclerview: RecyclerView = view.gallery
        recyclerview.addItemDecoration(GridItemDecoration(10))
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerview.adapter = GalleryAdapter(requireContext(), ImageDataset, countImages)
    }

    lateinit var currentPhotoPath: String
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun getFileList(context: Context, type: MediaStoreFileType): List<ImageData> {
        val imageList = mutableListOf<ImageData>()
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_TAKEN,
            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Files.FileColumns.BUCKET_ID
        )
        val sortOrder = "${MediaStore.Files.FileColumns.DATE_TAKEN} DESC"
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            //null,
            null,
            null,
            sortOrder
        )

        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val dateTakenColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_TAKEN)
            val displayNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val bucketIDColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
            val bucketNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val dateTaken = Date(cursor.getLong(dateTakenColumn))
                val displayName = cursor.getString(displayNameColumn)
                val contentUri = Uri.withAppendedPath(
                    type.externalContentUri,
                    id.toString()
                )
                val bucketID = cursor.getLong(bucketIDColumn)
                val bucketName = cursor.getString(bucketNameColumn)
                Log.d(
                    "test",
                    "id: $id, display_name: $displayName, date_taken: $dateTaken, content_uri: $contentUri\n"
                )
                val MDF = ImageData(id, dateTaken, displayName, contentUri, bucketID, bucketName)
                imageList.add(MDF)
            }
        }
        return imageList
    }

    private fun <E> listContainsContitionedItem(
        list: MutableList<E>,
        item: E,
        condition: (E, E) -> Boolean
    ): Boolean {
        list.forEach {
            when (condition(it, item)) {
                true -> return true
            }
        }
        return false
    }

    enum class MediaStoreFileType(
        val externalContentUri: Uri,
        val mimeType: String,
        val pathByDCIM: String
    ) {
        IMAGE(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*", "/image"),
        //AUDIO(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "audio/*", "/audio"),
        //VIDEO(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*", "/video");
    }



}

    /*
    private val OPEN_GALLERY = 1
    private fun displayGalleryImg(){
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var currentImageUrl : Uri? = data?.data
                try {
                    //val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                    //imageList.add(currentImageUrl)
                }catch(e:Exception){
                    e.printStackTrace()
                }
                val allpath = data?.getStringArrayExtra("all_path")
                if (allpath != null) {
                    for (string in allpath){
                        var item : Image? = null
                        //item?.sdcardPath = string
                        //imageList.add(string)
                    }
                }
            }
        }else{
            Log.d("ActivityResult", "something wrong")
        }
    }*/

    ///////////LOCAL///////////
    private fun displayLocalImg() {
        /*
        val imageIDs: IntArray = intArrayOf(
            R.drawable.img_01,
            R.drawable.img_02,
            R.drawable.img_03,
            R.drawable.img_04,
            R.drawable.img_05,
            R.drawable.img_06,
            R.drawable.img_07,
            R.drawable.img_08,
            R.drawable.img_09,
            R.drawable.img_11
        )
        var n:Int = imageIDs.size

         */
        /*
        // 사진들을 보여줄 GridView 뷰의 어댑터 객체를 정의하고 그것을 이 뷰의 어댑터로 설정합니다.
        Log.i("display","1")
        var gridview: GridView = view!!.findViewById(R.id.gridView)
        Log.i("display","2")
        val adapter = GalleryAdapter(context, imageList)
        Log.i("display","3")
        gridview.adapter = adapter
        Log.i("display","4")*/
    }



}

//    private fun imgFromGallery(){ // load image
//        checkPermission()
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)

        //Intent intent = new Intent(Intent.ACTION_PICK);
        //                    intent.setType
        //                            (android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        //                    startActivityForResult(intent, RequestCode.PICK_IMAGE);
//    }

    /*
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Gallery){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    imageView.setImageBitmap(bitmap)}
                catch(e:Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                //something wrong
            }
        }
    }

     */