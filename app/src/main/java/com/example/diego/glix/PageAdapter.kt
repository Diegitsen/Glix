package com.example.diego.glix

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by diego on 9/03/18.
 */
class PageAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            //OJO estoy cambiando el param2 de string a context
            0 -> return PlaylistFragment.newInstance("", context)
            1 -> return CancionFragment.newInstance("", context)
            2 -> return AlbumFragment.newInstance("", context)
            3 -> return ArtistaFragment.newInstance("", context)
        }
        return CancionFragment.newInstance("", context)
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // return null to show no title.
        return null

    }

}