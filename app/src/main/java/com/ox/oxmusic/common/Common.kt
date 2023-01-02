package com.ox.oxmusic.common

import android.content.Context
import android.widget.Toast

object Common {
    fun showShortToastMsg(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}