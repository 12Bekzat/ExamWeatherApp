package com.example.examproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var city: EditText? = null
    var findBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        city = findViewById(R.id.city_name)
        findBtn = findViewById(R.id.find_city)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val weathers: RecyclerView = findViewById(R.id.list_weathers)
        weathers.layoutManager = linearLayoutManager
        val array_weather = ArrayList<String>()
        findBtn?.setOnClickListener {
            if(city?.text.toString().trim() == "") {
                Toast.makeText(this, "Введите город", Toast.LENGTH_LONG).show()
            }
            else {
                val city = city?.text.toString()
                val key: String = "318d86e610dfb76c01702642e1ff63e1"
                val url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key"

                doAsync {
                    val apiResponse = URL(url).readText()

                    val jsonWeather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = jsonWeather.getJSONObject(0).getString("description")

                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")

                    array_weather.add("$city $desc $temp")
                }
            }
        }
        val adapter = WeatherAdapter(array_weather)
        weathers.adapter = adapter
    }


}