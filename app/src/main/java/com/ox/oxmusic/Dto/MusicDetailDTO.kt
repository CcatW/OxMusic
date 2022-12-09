package com.ox.oxmusic.Dto

data class MusicDetailDTO(
    var code: String,
    var title: String,
    var singer: String,
    var length: String,
    var url: String
//    var musicDetail: MusicDetail
)

data class MusicDetail(
    var title: String,
    var url: String,
    var albumCover: String
)
