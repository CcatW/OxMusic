package com.ox.oxmusic.model

import android.util.Log
import com.ox.oxmusic.common.RetrofitSender
import com.ox.oxmusic.contract.CurrentMusicContract
import com.ox.oxmusic.dto.MusicDetailDTO
import retrofit2.Call
import retrofit2.Response

class CurrentMusicModel: CurrentMusicContract.Model {
    override fun getMusicData(
        id: String,
        onFinishedListener: CurrentMusicContract.OnFinishedListener
    ) {
        // 데이터 가져오기
        RetrofitSender.apiService?.apiGetMusicData(id)
            ?.enqueue(object :retrofit2.Callback<MusicDetailDTO>{
                override fun onResponse(
                    call: Call<MusicDetailDTO>,
                    response: Response<MusicDetailDTO>
                ) {
                    val token: MusicDetailDTO? = response.body()
                    Log.e("Data222",":${token}")
                    onFinishedListener.getDataFinished(token!!)
                }

                override fun onFailure(call: Call<MusicDetailDTO>, t: Throwable) {
                    Log.e("Data222",":fail${t.message}")
                    onFinishedListener.getDataFailure(t.message.toString())
                }

            })
    }
}