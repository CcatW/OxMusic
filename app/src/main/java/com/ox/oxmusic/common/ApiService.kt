package com.ox.oxmusic.common

import com.ox.oxmusic.dto.MusicDetailDTO
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("song.php")
    fun apiGetMusicData(
        @Field("musicCode") id: String
    ): Call<MusicDetailDTO>
}