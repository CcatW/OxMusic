package com.ox.oxmusic.dto

data class MusicDetailDTO(
    var code: String,
    var title: String,
    var singer: String,
    var length: String,
    var url: String,
    var album: String
//    var musicDetail: MusicDetail
)

data class MusicDetail(
    var title: String,
    var url: String,
    var albumCover: String
)
