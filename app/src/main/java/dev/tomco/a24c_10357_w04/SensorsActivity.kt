package dev.tomco.a24c_10357_w04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textview.MaterialTextView
import dev.tomco.a24c_10357_w04.Interfaces.Callback_MoveCallback
import dev.tomco.a24c_10357_w04.Utilities.MoveDetector

class SensorsActivity : AppCompatActivity() {

    private lateinit var sensors_LBL_moveX: MaterialTextView
    private lateinit var sensors_LBL_moveY: MaterialTextView
    private lateinit var moveDetector: MoveDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        findViews()
        initMoveDetector()
    }

    private fun findViews() {
        sensors_LBL_moveX = findViewById(R.id.sensors_LBL_moveX)
        sensors_LBL_moveY = findViewById(R.id.sensors_LBL_moveY)
    }

    private fun initMoveDetector() {
        moveDetector = MoveDetector(
            this,
            object : Callback_MoveCallback {
                override fun moveX() {
                    sensors_LBL_moveX.text = moveDetector.moveCountX.toString()
                }

                override fun moveY() {
                    sensors_LBL_moveY.text = moveDetector.moveCountY.toString()
                }
            }
        )
    }


    override fun onPause() {
        super.onPause()
        moveDetector.stop()
    }

    override fun onResume() {
        super.onResume()
        moveDetector.start()
    }


}