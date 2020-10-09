package com.oceanbrasil.ocean_android_room_08_10_2020.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oceanbrasil.ocean_android_room_08_10_2020.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        btSave.setOnClickListener {
            val replyIntent = Intent()

            if (etWord.text.isBlank()) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = etWord.text.toString()

                replyIntent.putExtra("REPLY_WORD", word)

                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }
}
