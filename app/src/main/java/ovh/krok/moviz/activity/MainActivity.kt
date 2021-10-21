package ovh.krok.moviz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ovh.krok.moviz.R
import ovh.krok.moviz.Updatable
import ovh.krok.moviz.adapter.EventAdapter
import ovh.krok.moviz.model.Event
import ovh.krok.moviz.model.Movie
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() , Updatable {
    // J'ai mis en Int car le model est pas fait
    val events: ArrayList<Event> = arrayListOf()

    lateinit var list: RecyclerView
    lateinit var button : FloatingActionButton
    companion object{
        const val EXTRA_EVENT = "EXTRA_EVENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie : Movie = Movie("Bienvenue chez les chtis", "caca")
        val event1 : Event = Event(movie,"21 Octobre 2021", "Chez Simon")
        events.add(event1)

        list = findViewById<RecyclerView>(R.id.event_list)

        list.adapter = object : EventAdapter(events) {
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, EventActivity::class.java).apply {
                    putExtra(
                        EXTRA_EVENT,
                        events.get(list.getChildViewHolder(view).adapterPosition)
                    )
                }
                startActivity(intent)
            }

            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        button = findViewById<FloatingActionButton>(R.id.event_add)
        button.setOnClickListener{
            val intent = Intent(applicationContext, AddEventActivity::class.java).apply{

            }
            startActivity(intent)

        }
    }

    // Je sais pas si ça nous sera utile un jour
    override fun update() {
        TODO("Not yet implemented")
    }
}