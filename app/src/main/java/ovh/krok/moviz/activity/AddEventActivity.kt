package ovh.krok.moviz.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ovh.krok.moviz.R
import java.util.*

class AddEventActivity :AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var button_date : Button
    lateinit var button_validate : Button
    lateinit var textDate : TextView
    companion object{
        const val EXTRA_DATE = "EXTRA_DATE"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_LOCATION = "EXTRA_LOCATION"

    }
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)


        button_date = findViewById(R.id.button_date)
        button_date.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddEventActivity, this@AddEventActivity, year, month,day)
            datePickerDialog.show()
        }
        button_validate = findViewById(R.id.button_validate)
        button_validate.setOnClickListener {
            val intent = Intent(applicationContext, MovieAPIActivity::class.java).apply {
                putExtra(
                    EXTRA_DATE,
                    textDate.text.toString()
                );
                putExtra(
                    EXTRA_MOVIE,
                    findViewById<EditText>(R.id.text_movie).text.toString()
                );
                putExtra(
                    EXTRA_LOCATION,
                    findViewById<EditText>(R.id.text_location).text.toString()
                )
            }
            startActivity(intent)
        }

    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month + 1
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@AddEventActivity, this@AddEventActivity, hour, minute,
            DateFormat.is24HourFormat(this@AddEventActivity))
        timePickerDialog.show()
    }
    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        textDate = findViewById<TextView>(R.id.text_date)
        textDate.text = "$myDay/$myMonth/$myYear $myHour:$myMinute"

    }
}