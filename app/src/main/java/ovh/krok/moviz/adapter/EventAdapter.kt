package ovh.krok.moviz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ovh.krok.moviz.R
import ovh.krok.moviz.model.Event

abstract class EventAdapter(val events : ArrayList<Event>) :
    RecyclerView.Adapter<EventAdapter.EventHolder>(){

    class EventHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById<TextView>(R.id.movie_name)
        val date : TextView = itemView.findViewById<TextView>(R.id.movie_date)
        val location : TextView = itemView.findViewById<TextView>(R.id.movie_location)

    }
    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view : View) : Boolean
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        view.setOnClickListener{view -> onItemClick(view)}
        view.setOnLongClickListener{view -> onLongItemClick(view)}
        return EventHolder(view)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.name.text = events.get(position).movie.titre
        holder.date.text = events.get(position).date
        holder.location.text = events.get(position).location
    }

    override fun getItemCount(): Int {
        return events.size
    }
}