package com.example.activitytracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class GreetActivity : AppCompatActivity() {
    lateinit var tv_date: TextView;
    lateinit var tv_time: TextView;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greet)

        tv_date = findViewById(R.id.tv_date)
        tv_time = findViewById(R.id.tv_time)

        tv_date.setText(getCurrentDate())
        tv_time.setText(getCurrentTime())

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                tv_time.setText(getCurrentTime())
                handler.postDelayed(this, 5000)
            }
        }, 0)

        handler.postDelayed({
           startActivity(Intent(this, MainActivity::class.java))
        }, 5000)

    }

    fun getCurrentDate(): String{
        val currentDate = LocalDateTime.now()
        val monthName = currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfWeekName = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfMonth = currentDate.dayOfMonth
        return "$dayOfWeekName, $monthName $dayOfMonth"
    }

    fun getCurrentTime(): String{
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        return currentTime.format(formatter)
    }

}