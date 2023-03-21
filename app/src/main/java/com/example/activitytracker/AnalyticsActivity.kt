package com.example.activitytracker

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class AnalyticsActivity : AppCompatActivity() {
    private lateinit var table: TableLayout;
    private lateinit var db: DatabaseHelper;
    private lateinit var bt_back: Button;
    private lateinit var bt_clear: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        table = findViewById(R.id.tableLayout);
        db = DatabaseHelper(this);
        bt_back = findViewById(R.id.bt_back)
        bt_clear = findViewById(R.id.bt_clear)

        bt_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java));
            finish()
        }

        bt_clear.setOnClickListener {
            fillTable(db.clearTable());
            startActivity(Intent(this, MainActivity::class.java));
            finish()
        }

        fillTable(db.getDataCursor());
    }

    @SuppressLint("Range")
    fun fillTable(cursor: Cursor){
        while (cursor.moveToNext()) {
            table.addView(
                generateRow(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTIVITY_TYPE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTIVITY_DATE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTIVITY_TIME)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTIVITY_DURATION))))
        }
        cursor.close();
    }

    fun generateRow(activityType: String, date: String, time:String, duration:Int): TableRow{
        val row = TableRow(this)

        val col1 = TextView(this)
        col1.text = activityType
        col1.textSize = 16f
        col1.setPadding(16, 16, 16, 16)
        row.addView(col1)

        val col2 = TextView(this)
        col2.text = date
        col2.textSize = 16f
        col2.setPadding(16, 16, 16, 16)
        row.addView(col2)

        val col3 = TextView(this)
        col3.text = time
        col3.textSize = 16f
        col3.setPadding(16, 16, 16, 16)
        row.addView(col3)

        val col4 = TextView(this)
        col4.text = duration.toString() + " m"
        col4.textSize = 16f
        col4.setPadding(16, 16, 16, 16)
        row.addView(col4)

        return row
    }
}