package dev.tomco.a24c_10357_w04

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.tomco.a24c_10357_w04.Fragments.HighScoreFragment
import dev.tomco.a24c_10357_w04.Fragments.MapFragment
import dev.tomco.a24c_10357_w04.Interfaces.Calback_HighScoreItemClicked

class MainActivity : AppCompatActivity() {

    private lateinit var main_FRAME_list: FrameLayout
    private lateinit var main_FRAME_map: FrameLayout
    private lateinit var highScoreFragment: HighScoreFragment
    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        initViews()
    }

    private fun findViews() {
        main_FRAME_list = findViewById(R.id.main_FRAME_list)
        main_FRAME_map = findViewById(R.id.main_FRAME_map)
    }

    private fun initViews() {
        highScoreFragment = HighScoreFragment()
        highScoreFragment.calbackHighScoreItemClicked = object : Calback_HighScoreItemClicked {
            override fun highScoreItemClicked(lat: Double, lon: Double) {
                mapFragment.zoom(lat, lon)
            }
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_FRAME_list, highScoreFragment)
            .commit()


        mapFragment = MapFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_FRAME_map, mapFragment)
            .commit()


    }
}