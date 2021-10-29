package ovh.krok.moviz.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ovh.krok.moviz.R
import ovh.krok.moviz.Updatable
import ovh.krok.moviz.adapter.EventAdapter
import ovh.krok.moviz.model.Event
import ovh.krok.moviz.storage.EventJSONFileStorage
import android.content.pm.PackageManager

import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat



class MainActivity : AppCompatActivity() , Updatable {
    val events: ArrayList<Event> = arrayListOf()
    lateinit var json : EventJSONFileStorage
    lateinit var list: RecyclerView
    lateinit var button : FloatingActionButton
    lateinit var eventToAdd : Event
    companion object{
        const val EXTRA_EVENT = "EXTRA_EVENT"
        const val READ_STORAGE_CODE = 1
        const val WRITE_STORAGE_CODE = 2

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!checkPermission()) {
            requestPermission()
        }
        json  = EventJSONFileStorage(this, "event")


        events.addAll(json.findAll())
        if (intent.getSerializableExtra(EXTRA_EVENT) != null){
            eventToAdd = intent.getSerializableExtra(EXTRA_EVENT) as Event
            events.add(eventToAdd)
            json.insert(eventToAdd)

        }


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
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Supprimer un évènement")
                builder.setMessage("Voulez-vous supprimer cet évènement ?")
                builder.setPositiveButton("Oui") { _, _ ->
                    events.removeAt(list.getChildViewHolder(view).adapterPosition)
                    json.erase()
                    json.insertAll(events)
                    this.notifyDataSetChanged()

                }
                builder.setNegativeButton("Non"){ _, _->
                    Toast.makeText(applicationContext,
                        "Rien n'a été supprimé", Toast.LENGTH_SHORT).show()
                }
                builder.show()
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
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_STORAGE_CODE
        )
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            WRITE_STORAGE_CODE
        )
    }

}