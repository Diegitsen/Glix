package com.example.diego.glix.Musica

/**
 * Created by diego on 11/03/18.
 */
class SongInfo {

    var song:String? = null
    var author:String? = null
    var album:String? = null
    var songURL:String? = null

    constructor(song:String, album:String, author:String, songURL:String)
    {
        this.song = song
        this.author = author
        this.album = album
        this.songURL = songURL
    }
}