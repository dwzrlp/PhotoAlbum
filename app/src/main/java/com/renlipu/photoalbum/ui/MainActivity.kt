package com.renlipu.photoalbum.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renlipu.photoalbum.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * @program: Photo Album
 *
 * @description: Single activity application uses the Navigation library. Content is displayed by Fragments.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:03
 **/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}