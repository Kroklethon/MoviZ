package ovh.krok.moviz.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ovh.krok.moviz.R
import ovh.krok.moviz.adapter.EventAdapter
import ovh.krok.moviz.adapter.MovieAPIAdapter
import ovh.krok.moviz.api.TMDB
import ovh.krok.moviz.model.Event
import ovh.krok.moviz.model.Movie

class MovieAPIActivity:AppCompatActivity() {
    lateinit var list: RecyclerView
    lateinit var movie_name : String
    lateinit var movie_date : String
    lateinit var movie_location : String
    private val movieApi = TMDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_api)

        movie_name = intent.getSerializableExtra(AddEventActivity.EXTRA_MOVIE) as String
        movie_date = intent.getSerializableExtra(AddEventActivity.EXTRA_DATE) as String
        movie_location = intent.getSerializableExtra(AddEventActivity.EXTRA_LOCATION) as String

        list = findViewById<RecyclerView>(R.id.movie_list)

        movieApi.search(movie_name) {
            println(it)
            list.adapter = object : MovieAPIAdapter(it) {
                override fun onItemClick(view: View) {
                    val intent = Intent(applicationContext, MainActivity::class.java).apply {
                        putExtra(
                            MainActivity.EXTRA_EVENT,
                            Event(it.get(list.getChildViewHolder(view).adapterPosition), movie_date, movie_location)
                        )
                    }
                    startActivity(intent)
                }

                override fun onLongItemClick(view: View): Boolean {
                    Toast.makeText(applicationContext, "je veux supprimer", Toast.LENGTH_SHORT)
                        .show()
                    return true
                }
            }
        }

    }
}