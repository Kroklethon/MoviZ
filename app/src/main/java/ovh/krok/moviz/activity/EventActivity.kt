package ovh.krok.moviz.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import ovh.krok.moviz.R
import ovh.krok.moviz.api.TMDB
import ovh.krok.moviz.helpers.helpers
import ovh.krok.moviz.model.Event

class EventActivity : AppCompatActivity() {
    private lateinit var event : Event
    lateinit var button : FloatingActionButton
    lateinit var inviteMsg : String
    lateinit var calendar_button: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        event = intent.getSerializableExtra(MainActivity.EXTRA_EVENT) as Event
        findViewById<TextView>(R.id.movie_name).text = event.movie.titre
        findViewById<TextView>(R.id.movie_date).text = event.date
        findViewById<TextView>(R.id.movie_location).text = event.location
        findViewById<TextView>(R.id.movie_description).text = event.movie.description
        val backdrop = findViewById<ImageView>(R.id.event_movie_backdrop)

        inviteMsg = "Vous avez été invité à regarder " + event.movie.titre +"\n" +
                "La séance aura lieu le " + event.date + "\n" +
                "Le lieu est : " + event.location + "\n" +
                 TMDB.getMovieLink(event.movie.id)

        button = findViewById<FloatingActionButton>(R.id.share_button)
        button.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, inviteMsg)
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }
        calendar_button = findViewById<ImageView>(R.id.calendar_button)
        calendar_button.setOnClickListener{
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra("beginTime",helpers.dateToLong(event.date))
            intent.putExtra("endTime",helpers.getendMillis(helpers.dateToLong(event.date), event.movie.duration))
            intent.putExtra("title", event.movie.titre)
            intent.putExtra("description", "Vous avez été invité à voir un film")
            intent.putExtra("eventLocation",event.location)

            startActivity(intent)
        }
        if (event.movie.backdrop_url.isEmpty()) {
            backdrop.visibility = View.GONE
        } else {
            backdrop.visibility = View.VISIBLE
            Picasso.get().load(event.movie.backdrop_url).into(backdrop)
        }

    }
}