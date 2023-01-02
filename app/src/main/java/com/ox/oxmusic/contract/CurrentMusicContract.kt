package com.ox.oxmusic.contract

import android.widget.SeekBar
import com.ox.oxmusic.dto.MusicDetailDTO
import java.time.Duration

interface CurrentMusicContract {

    interface OnFinishedListener{
        fun getDataFinished(musicData: MusicDetailDTO)
        fun getDataFailure(msg: String)
    }

    interface Model{
        fun getMusicData(musicCode: String, onFinishedListener: OnFinishedListener)
    }

    interface View{
        fun getDataSucceed(musicData: MusicDetailDTO)
        fun getDataFailure(msg: String)
        fun setPlayButton(isPlaying: Boolean)
        fun setSeekBar(duration: Int)
        fun setSeekBarProgress(progress: Int)
    }

    interface Presenter: OnFinishedListener{
        fun onStartGetData(id: String)
        fun playMusic()
        fun setData(musicData: MusicDetailDTO)
        fun setSeekBar(seekbar: SeekBar)
    }

}