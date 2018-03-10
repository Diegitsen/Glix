package com.example.diego.glix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.TextView
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnTabSelectListener
import kotlinx.android.synthetic.main.layout_center_viewpager.*


class MainActivity : AppCompatActivity() {

    var tvTitulo:TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvTitulo = findViewById(R.id.toolbarTitulo)

        val bottomBar = findViewById<View>(R.id.bottomBar) as BottomBar


        bottomBar.setOnTabSelectListener { tabId ->
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()


            when (tabId) {
                R.id.tab_letras ->
                {
                    tvTitulo!!.text = "ｔｉｔｕｌｏ"
                    transaction.replace(R.id.contentContainer, LetraFragment()).commit()
                }


                R.id.tab_temp ->
                {
                    tvTitulo!!.text = "ｔｅｍｐｏｒｉｚａｄｏｒ"
                    transaction.replace(R.id.contentContainer, TempFragment()).commit()
                }

                R.id.tab_musica ->
                {
                    tvTitulo!!.text = "ｍｕｓｉｃａ"
                    transaction.replace(R.id.contentContainer, MusicaFragment()).commit()
                }

                R.id.tab_ajustes ->
                {
                    tvTitulo!!.text = "ａｊｕｓｔｅｓ"
                    transaction.replace(R.id.contentContainer, AjustesFragment()).commit()
                }

                R.id.tab_support ->
                {
                    tvTitulo!!.text = "ｓｕｐｐｏｒｔ"
                    transaction.replace(R.id.contentContainer, SupportFragment()).commit()
                }
            }
        }
    }
}
