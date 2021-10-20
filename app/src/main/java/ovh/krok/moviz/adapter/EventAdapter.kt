package ovh.krok.moviz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ovh.krok.moviz.R

abstract class EventAdapter() :
    RecyclerView.Adapter<EventAdapter.EventHolder>(){

    class EventHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.movie_name)
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
        //holder.name.text = events.get(position)
    }

    override fun getItemCount(): Int {
        //return events.size
        return 15;
    }
}