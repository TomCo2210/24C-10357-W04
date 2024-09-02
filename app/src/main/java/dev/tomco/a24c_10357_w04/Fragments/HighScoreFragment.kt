package dev.tomco.a24c_10357_w04.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dev.tomco.a24c_10357_w04.Interfaces.Callback_HighScoreItemClicked
import dev.tomco.a24c_10357_w04.R


class HighScoreFragment : Fragment() {

    private lateinit var highScores_ET_location: TextInputEditText
    private lateinit var highScores_BTN_send: MaterialButton
    var calbackHighScoreItemClicked: Callback_HighScoreItemClicked? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_high_score, container, false)
        findViews(v)
        initViews(v)
        return v
    }

    private fun initViews(v: View) {
        highScores_BTN_send.setOnClickListener { v: View ->
            var coords = highScores_ET_location.text?.split(",")
            var lat: Double = coords?.get(0)?.toDouble() ?: 0.0
            var lon: Double = coords?.get(1)?.toDouble() ?: 0.0

            itemClicked(lat, lon)
        }

    }

    private fun itemClicked(lat: Double, lon: Double) {
        calbackHighScoreItemClicked?.highScoreItemClicked(lat, lon)
    }

    private fun findViews(v: View) {
        highScores_ET_location = v.findViewById(R.id.highScores_ET_location)
        highScores_BTN_send = v.findViewById(R.id.highScores_BTN_send)
    }
}