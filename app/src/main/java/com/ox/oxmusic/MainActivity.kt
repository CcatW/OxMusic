package com.ox.oxmusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ox.oxmusic.databinding.ActivityMainBinding
import com.ox.oxmusic.view.CurrentMusicActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )

        binding.btnTest.setOnClickListener {
            val intent = Intent(this, CurrentMusicActivity::class.java)
            startActivity(intent)
        }
    }
}