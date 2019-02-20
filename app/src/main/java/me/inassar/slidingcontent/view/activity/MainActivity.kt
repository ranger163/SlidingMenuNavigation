package me.inassar.slidingcontent.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_drawer_layout.*
import me.inassar.slidingcontent.R
import me.inassar.slidingcontent.enableAnimatedBackground
import me.inassar.slidingcontent.state.ScreenState
import me.inassar.slidingcontent.toast
import me.inassar.slidingcontent.view.adapter.NavAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(
            this,
            MainActivityViewModel.MainActivityViewModelFactory(MainActivityInteractor())
        )[MainActivityViewModel::class.java]
        viewModel.navItemsState.observe(::getLifecycle, ::updateDrawerItems)

        setDrawer()
        interactions()
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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun interactions() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setDrawer() {
        toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_drawer_open, R.string.nav_drawer_close
        ) {

            val scaleFactor = 6f

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width.times(slideOffset)
                content.apply {
                    translationX = slideX
                    scaleX = 1.minus(slideOffset.div(scaleFactor))
                    scaleY = 1.minus(slideOffset.div(scaleFactor))
                }
            }
        }.apply { syncState() }

        drawerLayout.apply {
            setScrimColor(Color.TRANSPARENT)
            drawerElevation = 0f
            addDrawerListener(toggle)
            enableAnimatedBackground()
        }
    }

    private fun updateDrawerItems(screenState: ScreenState<MainActivityState>?) {
        when (screenState) {
            is ScreenState.Render -> setDrawerItems(screenState.renderState)
        }
    }

    private fun setDrawerItems(renderState: MainActivityState) {
        when (renderState) {
            is MainActivityState.ShowNavItems -> {
                navRecycler.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = NavAdapter(renderState.navItems, viewModel::onNavItemClicked)
                }
            }
            is MainActivityState.HandleNavItemClick -> {
                toast("ItemClicked")
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }
}
