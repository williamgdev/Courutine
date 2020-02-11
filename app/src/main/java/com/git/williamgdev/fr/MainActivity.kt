package com.git.williamgdev.fr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.git.williamgdev.fr.ui.item.ItemFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ItemFragment.newInstance())
                .commitNow()
        }
    }

}
