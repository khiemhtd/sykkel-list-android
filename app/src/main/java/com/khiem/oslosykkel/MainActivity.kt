package com.khiem.oslosykkel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val osloSykkelManager = ApiManagerOsloSykkel()
        val textView: TextView = findViewById(R.id.textView)

        val btnRequest: Button = findViewById<Button>(R.id.btnRequest)
        btnRequest.setOnClickListener(View.OnClickListener {
            val result: JSONObject? = osloSykkelManager.getStations(this)
            textView.text = "Stations: $result"
        })
    }
}