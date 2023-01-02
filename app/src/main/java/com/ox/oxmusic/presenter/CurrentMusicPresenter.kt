package com.ox.oxmusic.presenter

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.SeekBar
import com.ox.oxmusic.contract.CurrentMusicContract
import com.ox.oxmusic.dto.MusicDetailDTO
import com.ox.oxmusic.model.CurrentMusicModel
import kotlinx.coroutines.*

class CurrentMusicPresenter(
    private val mContext: Context,
    private val view: CurrentMusicContract.View,
    private val model: CurrentMusicModel = CurrentMusicModel()
    ): CurrentMusicContract.Presenter {
    var mp3 = MediaPlayer()

    override fun onStartGetData(id: String) {
        Log.e("Data222",":pre")
        model.getMusicData("1234", this)
    }

    override fun getDataFinished(musicData: MusicDetailDTO) {
        val code = musicData?.code
        if(code == "00"){
            view.getDataSucceed(musicData)
            setData(musicData)
        } else if( code == "01") {
            view.getDataFailure("서버와의 문제가 생겼습니다 code:01")
        } else view.getDataFailure("인터넷 연결을 확인하여 주십시오 code:02")
    }

    override fun getDataFailure(msg: String) {
        view.getDataFailure("인터넷 연결을 확인하여 주십시오 code:03")
    }

    override fun setData(musicData: MusicDetailDTO){
        val url = musicData.url
        mp3.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
        }

        view.setSeekBar(mp3.duration)
    }

    override fun setSeekBar(seekBar: SeekBar){

    }

    fun moveSeekBar(progress: Int){
        mp3.seekTo(progress)
    }

    override fun playMusic(){
        if(!mp3.isPlaying) {
            mp3.start()
            view.setPlayButton(mp3.isPlaying)
        } else if(mp3.isPlaying){
            mp3.pause()
            view.setPlayButton(mp3.isPlaying)
        }
        //여기 안되는 이유 아침에 찾자
        //그리고 다음앱은 텍스트뷰어임

        CoroutineScope(Dispatchers.Default).launch {

            CoroutineScope(Dispatchers.Main).launch{
                while (mp3.isPlaying){
                    delay(1000)
                    view.setSeekBarProgress(mp3.currentPosition)
                    Log.e("123", "1234444")
                }
            }
        }


    }

}