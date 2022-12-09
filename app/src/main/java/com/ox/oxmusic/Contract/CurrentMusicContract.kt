package com.ox.oxmusic.Contract

import com.ox.oxmusic.Dto.MusicDetailDTO

interface CurrentMusicContract {

    interface OnFinishedListener{
        fun getDataFinished(musicData: MusicDetailDTO)
        fun getDataFailure(msg: String)
    }

    interface Model{
        fun getMusicData(musicCode: String, onFinishedListener: OnFinishedListener)
    }

    interface View{
        fun getDataSucceed()
        fun getDataFailure(msg: String)
    }

    interface Presenter: OnFinishedListener{
        fun onStartGetData(id: String)
    }

}