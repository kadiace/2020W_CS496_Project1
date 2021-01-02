package com.example.project1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {
    private val REQ_STORAGE_PERMISSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰페이저 설정
        val fragmentList = listOf(Fragment1(), Fragment2(), Fragment3())
        val adapter = ViewPagerAdapter(this)
        adapter.fragments = fragmentList


        //뷰페이저와 어댑터 연결
        view_pager.adapter = adapter

        // 탭레이아웃 관리
        TabLayoutMediator(tabs, view_pager) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Phone Book"
                1 -> tab.text = "Gallery"
                2 -> tab.text = "Temp"
                else -> tab.text = "Phone Book"
            }
            view_pager.setCurrentItem(0)
        }.attach()

        checkGalleryPermission(cancel = {showPermissionInfoDialog()}, ok = {})
    }

    private fun checkGalleryPermission(cancel: () -> Unit, ok: () -> Unit) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {  // <PERMISSION_DENIED가 반환됨>
            // 이전에 사용자가 앱 권한을 허용하지 않았을 때 -> 왜 허용해야되는지 알람을 띄움
            // shouldShowRequestPermissionRationale메소드는 이전에 사용자가 앱 권한을 허용하지 않았을 때 ture를 반환함
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                cancel()
            }
            // 앱 처음 실행했을 때
            else
            // 권한 요청 알림
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQ_STORAGE_PERMISSION
                )
        } else // 앱에 권한이 허용됨
            ok()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            // () 사용에 대한 사용자의 요청)일 때
            REQ_STORAGE_PERMISSION -> {
                // 요청이 비허용일 때
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    toast("권한 거부 됨")
                    finish()
                }
            }
        }
    }
    // 사용자가 이전에 권한을 거부했을 때 호출된다.
    private fun showPermissionInfoDialog() {
        alert("갤러리 사진을 가져오려면 권한이 필수로 필요합니다", "") {
            yesButton {
                // 권한 요청
                ActivityCompat.requestPermissions(
                    //this@MapsActivity,
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQ_STORAGE_PERMISSION
                )
            }
            noButton {
                toast("권한 거부 됨")
                finish()
            }
        }.show()
    }


        /*
        var readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if(readPermission == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQ_STORAGE_PERMISSION)
        }

         */
    
        //스토리지 읽기 퍼미션을 permission 변수에 담는다
        //val permission = context?.let {
            // context가 null이 아닐 경우만 시행
//            ContextCompat.checkSelfPermission(
//                it, Manifest.permission.READ_EXTERNAL_STORAGE)
        //}
        //val permission = ContextCompat.checkSelfPermission(getContext(),
        //            Manifest.permission.READ_EXTERNAL_STORAGE)

        //현재 안드로이드 버전이 ~~미만이면 메서드를 종료한다.
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//            return

//        if(permission != PackageManager.PERMISSION_DENIED){
        //imgFromGallery()
//            title.setText("YEP")
//        }

}