package com.elhady.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

/**
 * Created by islam elhady on 26-May-21.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.fragmentContainerView).navigateUp()
}