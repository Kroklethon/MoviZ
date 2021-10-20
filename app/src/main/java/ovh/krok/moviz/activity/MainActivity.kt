package ovh.krok.moviz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ovh.krok.moviz.R
import ovh.krok.moviz.Updatable
import ovh.krok.moviz.adapter.EventAdapter

class MainActivity : AppCompatActivity() , Updatable {
    // J'ai mis en Int car le model est pas fait
    val events: ArrayList<Int> = arrayListOf()

    lateinit var list: RecyclerView
    companion object{
        const val EXTRA_EVENT = "EXTRA_EVENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Temporaire ça sert juste à faire que chaque élément de la liste soit clickable
        for (i in 0..15){
            events.add(1)
        }

        list = findViewById<RecyclerView>(R.id.event_list)

        list.adapter = object : EventAdapter() {
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

    }

    // Je sais pas si ça nous sera utile un jour
    override fun update() {
        TODO("Not yet implemented")
    }
}