package com.example.derrick.material2components

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_bar.replaceMenu(R.menu.primary_menu)
        val fabVisibilityListener = object : FloatingActionButton.OnVisibilityChangedListener() {
            override fun onShown(fab: FloatingActionButton?) {
                super.onShown(fab)
            }

            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)

                bottom_bar.toggleFabAlignment()
                bottom_bar.replaceMenu(
                        if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) R.menu.secondary_menu
                        else R.menu.primary_menu
                )
                fab?.setImageDrawable(
                        if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) ResourcesCompat.getDrawable(resources, R.drawable.ic_undo_white_24dp, null)
                        else ResourcesCompat.getDrawable(resources, R.drawable.ic_add_white_24dp, null)
                )
                fab?.show()
            }
        }


        btnToggle.setOnClickListener {

            mFab.hide(fabVisibilityListener);

            invalidateOptionsMenu()
            bottom_bar.navigationIcon = if (bottom_bar.navigationIcon != null) null
            else ResourcesCompat.getDrawable(resources, R.drawable.ic_menu_white_24dp, null)
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

    private fun BottomAppBar.toggleFabAlignment() {
        currentFabAlignmentMode = fabAlignmentMode
        fabAlignmentMode = currentFabAlignmentMode.xor(1)
    }


}


