package com.example.diego.glix.Musica

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.diego.glix.R
import kotlinx.android.synthetic.main.layout_center_viewpager.view.*
import kotlinx.android.synthetic.main.layout_song_ticket.view.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MusicaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MusicaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicaFragment : Fragment() {


    private var adapter: SectionPageAdapter? = null
    private var viewPager:ViewPager?=null
    private var tabLayout:TabLayout?=null




    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        // Inflate the layout for this fragment
        var view:View = inflater!!.inflate(R.layout.fragment_musica, container, false)

        adapter = SectionPageAdapter(activity.supportFragmentManager) as SectionPageAdapter
        adapter!!.addFragment(PlaylistFragment()) //index 0
        adapter!!.addFragment(CancionFragment()) //index 1
        adapter!!.addFragment(AlbumFragment()) //index 2
        adapter!!.addFragment(ArtistaFragment()) //index 3

        viewPager = view.containerViewPager as ViewPager
        viewPager!!.adapter = adapter

        tabLayout = view.findViewById<View>(R.id.tabsMusica) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)

        tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.icplaylist)
        tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.iccancion)
        tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.icalbum)
        tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.icartista)

        return view



    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment MusicaFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MusicaFragment {
            val fragment = MusicaFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }







}// Required empty public constructor
