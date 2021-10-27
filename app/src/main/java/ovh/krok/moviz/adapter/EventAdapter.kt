package ovh.krok.moviz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ovh.krok.moviz.R
import ovh.krok.moviz.model.Event

abstract class EventAdapter(val events : List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventHolder>(){

    class EventHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById<TextView>(R.id.movie_name)
        val date : TextView = itemView.findViewById<TextView>(R.id.movie_date)
        val location : TextView = itemView.findViewById<TextView>(R.id.movie_location)
        val poster : ImageView = itemView.findViewById<ImageView>(R.id.event_movie_poster)
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
        Picasso.get().load(events.get(position).movie.image_url).into(holder.poster)

    }

    override fun getItemCount(): Int {
        return events.size
    }
}