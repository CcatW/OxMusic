package com.ox.oxmusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.ox.oxmusic.databinding.ActivityMainBinding
import com.ox.oxmusic.view.CurrentMusicActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )

        binding.btnTest.setOnClickListener {
            val intent = Intent(this, CurrentMusicActivity::class.java)
            intent.putExtra("musicCode", "1234")
            startActivity(intent)
        }



//
//        CoroutineScope(Dispatchers.Default).launch {
//            var i = 3
//            while (i > 2){
//                delay(1000)
//                Log.e("123", "1234444")
//                delay(3000)
//                Log.e("123", "1234444")
//                delay(5000)
//                Log.e("123", "1234444")
//                delay(7000)
//                Log.e("123", "1234444")
//            }
//        }
    }
}