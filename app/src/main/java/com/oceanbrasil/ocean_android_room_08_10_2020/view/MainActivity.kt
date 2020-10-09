package com.oceanbrasil.ocean_android_room_08_10_2020.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oceanbrasil.ocean_android_room_08_10_2020.R
import com.oceanbrasil.ocean_android_room_08_10_2020.model.Word
import com.oceanbrasil.ocean_android_room_08_10_2020.viewmodel.WordViewModel
import com.oceanbrasil.ocean_android_room_08_10_2020.viewmodel.WordViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        wordViewModel = ViewModelProvider(
            this,
            WordViewModelFactory(application)
        ).get(WordViewModel::class.java)

        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, Observer {
            adapter.words = it
        })

        fab.setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)

            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1
            && resultCode == Activity.RESULT_OK
        ) {
            data?.let {
                val extraReply = it.getStringExtra("REPLY_WORD")

                extraReply?.let { reply ->
                    val word = Word(reply)
                    wordViewModel.insert(word)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
