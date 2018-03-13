package com.example.diego.glix.Musica

import android.content.Context
import android.view.View
import android.widget.MediaController
import com.example.diego.glix.R

/**
 * Created by diego on 12/03/18.
 */
class MPControl:MediaController.MediaPlayerControl {


    private var controller: MusicController? = null

    private lateinit var context: Context
    private lateinit var viewxd: View

    fun MPControl(context: Context, viewxd:View) {
        this.context = context
        this.viewxd = viewxd
    }


    //ON THE ONCREATE CALL THE METHOS SETCONTROLLER

    private fun setController() {
        //set the controller up
        controller = MusicController(context)
        controller!!.setMediaPlayer(this);
        controller!!.setAnchorView(viewxd.findViewById(R.id.lvSongs));
        controller!!.setEnabled(true);
    }

    fun controllerInAction()
    {

    }




    override fun isPlaying(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canSeekForward(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDuration(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBufferPercentage(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seekTo(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentPosition(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canSeekBackward(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAudioSessionId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canPause(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}