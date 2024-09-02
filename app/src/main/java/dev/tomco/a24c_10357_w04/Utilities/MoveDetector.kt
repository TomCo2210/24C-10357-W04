package dev.tomco.a24c_10357_w04.Utilities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dev.tomco.a24c_10357_w04.Interfaces.Callback_MoveCallback
import kotlin.math.abs

class MoveDetector(context: Context, private val moveCallback: Callback_MoveCallback?) {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) as Sensor
    private lateinit var sensorEventListener: SensorEventListener

    var moveCountX: Int = 0
        private set
    var moveCountY: Int = 0
        private set
    private var timestamp: Long = 0L

    init {
        initEventListener()
    }

    private fun initEventListener() {
        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                calculateMove(x, y)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // pass
            }

        }
    }

    private fun calculateMove(x: Float, y: Float) {
        if (System.currentTimeMillis() - timestamp >= 500) {
            timestamp = System.currentTimeMillis()
            if (abs(x) >= 6.0) {
                moveCountX++
                moveCallback?.moveX()
            }
            if (abs(y) >= 6.0) {
                moveCountY++
                moveCallback?.moveY()
            }
        }
    }

    fun stop() {
        sensorManager
            .unregisterListener(
                sensorEventListener,
                sensor
            )
    }

    fun start() {
        sensorManager
            .registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
    }
}