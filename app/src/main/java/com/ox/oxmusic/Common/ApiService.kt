package com.ox.oxmusic.Common

import com.ox.oxmusic.Dto.MusicDetailDTO
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