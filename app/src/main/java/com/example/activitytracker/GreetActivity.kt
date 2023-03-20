package com.example.activitytracker

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class GreetActivity : AppCompatActivity() {
    lateinit var tv_date: TextView;
    lateinit var tv_time: TextView;
    lateinit var layout: RelativeLayout;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greet)

        tv_date = findViewById(R.id.tv_date)
        tv_time = findViewById(R.id.tv_time)
        layout = findViewById(R.id.welcomeBackground)

        tv_date.setText(getCurrentDate())
        tv_time.setText(getCurrentTime())

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                tv_time.setText(getCurrentTime())
                //Call your function here
                handler.postDelayed(this, 5000)//1 sec delay
            }
        }, 0)

        handler.postDelayed({
           startActivity(Intent(this, MainActivity::class.java))
        }, 50000)

    }

    fun getCurrentDate(): String{
        val current = LocalDateTime.now()
        val monthName = current.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfWeekName = current.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfMonth = current.dayOfMonth
        return "$dayOfWeekName, $monthName $dayOfMonth"
    }

    fun getCurrentTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        val formatted = current.format(formatter)
        return formatted
    }

    fun getHour(): Int{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formatted = current.format(formatter)

        return formatted.toInt()
    }
}