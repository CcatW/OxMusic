package com.ox.oxmusic.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import com.ox.oxmusic.contract.CurrentMusicContract
import com.ox.oxmusic.R
import com.ox.oxmusic.common.Common
import com.ox.oxmusic.databinding.ActivityCurrentMusicBinding
import com.ox.oxmusic.dto.MusicDetailDTO
import com.ox.oxmusic.presenter.CurrentMusicPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.time.Duration
import java.util.concurrent.TimeUnit

class CurrentMusicActivity: AppCompatActivity(), CurrentMusicContract.View {
    lateinit var binding: ActivityCurrentMusicBinding
    lateinit var mContext: Context

    private val presenter: CurrentMusicPresenter by lazy {
        CurrentMusicPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_current_music)
        mContext = this
//        init()
    }

    fun init(){
        getMusicData()
        setEvent()
    }

    fun getMusicData(){
        val intent = intent
        val musicCode = intent.getStringExtra("musicCode")
        if( musicCode != null ){
            presenter.onStartGetData(musicCode)
        } else{
            Common.showShortToastMsg(mContext, "음악 정보를 불러오지 못하였습니다")
            finish()
        }
    }

    fun setEvent(){
        val disposables = CompositeDisposable()

        val btnPlay = binding.btnPlay.clicks().map {binding.btnPlay}

        val disposable = Observable.mergeArray(btnPlay)
            .throttleFirst(500, TimeUnit.MICROSECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                    binding.btnPlay -> {
                        presenter.playMusic()
                    }
                }
            }
        disposables.add(disposable)
    }

    override fun setSeekBar(duration: Int){
        presenter.setSeekBar(binding.progressSong)
        binding.progressSong.max = duration

        binding.progressSong.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    presenter.moveSeekBar(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    override fun setPlayButton(isPlaying: Boolean){
        if(isPlaying){
            Glide.with(this).load(R.drawable.pause).into(binding.btnPlay)
        } else{
            Glide.with(this).load(R.drawable.play).into(binding.btnPlay)
        }
    }

    override fun getDataSucceed(musicData: MusicDetailDTO) {
        Glide
            .with(this)
            .load(musicData.album)
            .thumbnail(0.1f)
            .into(binding.ivAlbum)

        binding.tvTitle.text = musicData.title
        binding.tvSinger.text = musicData.singer
        //프로그레스바 멈추기
        //이미지 및 음악 정보 넣어주기
    }

    override fun getDataFailure(msg: String) {
        Common.showShortToastMsg(mContext, msg)
        finish()
    }

    override fun setSeekBarProgress(progress: Int){
        binding.progressSong.setProgress(progress)
    }
}