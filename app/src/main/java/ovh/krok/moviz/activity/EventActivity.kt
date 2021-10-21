package ovh.krok.moviz.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ovh.krok.moviz.R
import ovh.krok.moviz.model.Event

class EventActivity : AppCompatActivity() {
    private lateinit var event : Event
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        event = intent.getSerializableExtra(MainActivity.EXTRA_EVENT) as Event
        findViewById<TextView>(R.id.movie_name).text = event.movie.titre
        findViewById<TextView>(R.id.movie_date).text = event.date
        findViewById<TextView>(R.id.movie_location).text = event.location


    }
}