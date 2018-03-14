package com.example.diego.glix.Musica

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.diego.glix.R
import kotlinx.android.synthetic.main.layout_list_view.*
import kotlinx.android.synthetic.main.layout_list_view.view.*
import kotlinx.android.synthetic.main.layout_song_ticket.view.*
import java.util.ArrayList
import android.widget.MediaController.MediaPlayerControl;
import kotlinx.android.synthetic.main.fragment_cancion.*
import kotlinx.android.synthetic.main.fragment_cancion.view.*
import kotlinx.android.synthetic.main.layout_seekbar_playing.*
import kotlinx.android.synthetic.main.layout_song_playing.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CancionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CancionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CancionFragment : Fragment()  {

    //vars
    var listSong = ArrayList<SongInfo>()
    var myAdapter:MySongAdapter? = null
    var mp: MediaPlayer? = null
    var songURL:String? = null
    var songAuthor:String? = null
    var songName:String?=null
    var songAlbum:String?=null
    var viewxd:View?=null
    var bPlayPause:ImageButton?=null
    var cont = 0;

    //var lvSong: ListView? = null



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
        viewxd = inflater!!.inflate(R.layout.fragment_cancion, container, false)



        viewxd!!.llSongIsPlaying.visibility = View.INVISIBLE
        viewxd!!.llSongIsPlaying.isEnabled = false

        //OJO no hay viewxd

        bPlayPause = viewxd!!.findViewById(R.id.bPlayPausePlaying)
        bPlayPause!!.isEnabled = true
        bPlayPause!!.setOnClickListener(mButtonListener)


        checkUserPermsions()

        var myTracking = mySongTrack()
        myTracking.start()

        return viewxd
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
         * @return A new instance of fragment CancionFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: Context): CancionFragment {
            val fragment = CancionFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2.toString())
            fragment.arguments = args
            return fragment
        }
    }


    //-------MUSICPLAYER-------------------------------------------------------------

    inner class MySongAdapter: BaseAdapter
    {
        var myListSong = ArrayList<SongInfo>()

        constructor(myListSong: ArrayList<SongInfo>):super()
        {
            this.myListSong = myListSong
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.layout_song_ticket, null)
            val song = this.myListSong[position]
            var url:String?=null
            myView.tvTituloCancion.text = song.song

            myView.LinearLayoutCancion.setOnClickListener(View.OnClickListener {

                //miniatura
                viewxd!!.llSongIsPlaying.visibility = View.VISIBLE
                viewxd!!.llSongIsPlaying.isEnabled = false

                mp = MediaPlayer()


                try {
                    mp!!.setDataSource(song.songURL)
                    //miniatura
                    tvSongSongPlaying.text = song.song
                    tvArtistSongPlaying.text = song.album
                    url = song.songURL
                    mp!!.prepare()
                    mp!!.start()
                    sbProgress.max = mp!!.duration
                } catch(ex: Exception) {

                }

            })

            return myView
        }



        override fun getItem(item: Int): Any {
            return this.myListSong[item]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }

    }


    inner class mySongTrack():Thread()
    {

        override fun run()
        {
            while(true)
            {
                try
                {
                    Thread.sleep(1000)
                }
                catch (ex:Exception)
                {
                }

                activity.runOnUiThread {
                    if(mp!=null)
                    {
                        sbProgress.progress = mp!!.currentPosition
                    }
                }
            }
        }
    }



    fun checkUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CODE_ASK_PERMISSIONS)
                return
            }
        }

        loadSong()

    }

    private val REQUEST_CODE_ASK_PERMISSIONS = 123

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when(requestCode)
        {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {/*loadSong()*/}
            else
            {
                //Permission Denied
                Toast.makeText(activity, "denail", Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun loadSong()
    {
        //lvSong = lvSongs
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = activity.contentResolver.query(allSongsURI, null, selection, null, null)
        if(cursor != null)
        {
            if(cursor!!.moveToFirst())
            {
                do
                {
                    songURL = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    songAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    songAlbum = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    songName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    listSong.add(SongInfo(songName.toString(), songAlbum.toString(), songAuthor.toString(), songURL.toString()))
                }while(cursor!!.moveToNext())
            }

            cursor!!.close()



            myAdapter = MySongAdapter(listSong)
           // lvSong!!.adapter = myAdapter
            viewxd!!.lvSongs.adapter = myAdapter
        }
    }

    //button play  pause
    private val mButtonListener = View.OnClickListener { v ->
        val controller = MediaControllerCompat.getMediaController(activity)
        val stateObj = controller.playbackState
        val state = stateObj?.state ?: PlaybackStateCompat.STATE_NONE
        when (v.id) {
            R.id.bPlayPausePlaying -> {
                if (state == PlaybackStateCompat.STATE_PAUSED ||
                        state == PlaybackStateCompat.STATE_STOPPED ||
                        state == PlaybackStateCompat.STATE_NONE) {
                    mp!!.start()
                } else if (state == PlaybackStateCompat.STATE_PLAYING ||
                        state == PlaybackStateCompat.STATE_BUFFERING ||
                        state == PlaybackStateCompat.STATE_CONNECTING) {
                    mp!!.pause()
                }
            }
        }
    }


}// Required empty public constructor
