package com.ox.oxmusic.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ox.oxmusic.Contract.CurrentMusicContract
import com.ox.oxmusic.R
import com.ox.oxmusic.databinding.ActivityCurrentMusicBinding
import com.ox.oxmusic.presenter.CurrentMusicPresenter

class CurrentMusicActivity: AppCompatActivity(), CurrentMusicContract.View {
    lateinit var binding: ActivityCurrentMusicBinding

    private val presenter: CurrentMusicPresenter by lazy {
        CurrentMusicPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_current_music)
        init()
    }

    fun init(){
        getMusicData()
    }

    fun getMusicData(){
        val intent = intent
//        val musicCode = intent.getStringExtra("musicId")
        val musicCode = "1234"
        Log.e("Data222",":getMusicData")
        if( musicCode != null ){
            presenter.onStartGetData(musicCode)
        } else{
            // 오류처리
        }

    }

    override fun getDataSucceed() {
        //프로그레스바 멈추기
        //이미지 및 음악 정보 넣어주기
    }

    override fun getDataFailure(msg: String) {
        // 오류 처리
    }
}