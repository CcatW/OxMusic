package com.ox.oxmusic.presenter

import android.content.Context
import android.util.Log
import com.ox.oxmusic.Contract.CurrentMusicContract
import com.ox.oxmusic.Dto.MusicDetailDTO
import com.ox.oxmusic.model.CurrentMusicModel

class CurrentMusicPresenter(
    private val mContext: Context,
    private val view: CurrentMusicContract.View,
    private val model: CurrentMusicModel = CurrentMusicModel()
    ): CurrentMusicContract.Presenter {

    override fun onStartGetData(id: String) {
        Log.e("Data222",":pre")
        model.getMusicData("1234", this)
    }

    override fun getDataFinished(musicData: MusicDetailDTO) {
        val code = musicData?.code
        Log.e("Data2223",":${musicData}")
        if(code == "00"){
            view.getDataSucceed()
        } else if( code == "01") {
            view.getDataFailure("서버와의 문제가 생겼습니다 code:01")
        } else view.getDataFailure("인터넷 연결을 확인하여 주십시오 code:02")
    }

    override fun getDataFailure(msg: String) {
        view.getDataFailure("인터넷 연결을 확인하여 주십시오 code:03")
    }
}