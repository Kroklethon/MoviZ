package ovh.krok.moviz.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ovh.krok.moviz.R
import ovh.krok.moviz.model.Movie



abstract class MovieAPIAdapter(val movies : List<Movie>):
    RecyclerView.Adapter<MovieAPIAdapter.MovieHolder>(){

    class MovieHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById<TextView>(R.id.movie_name)
        val image : ImageView = itemView.findViewById<ImageView>(R.id.movie_image)
    }
    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view : View) : Boolean
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_api, parent, false)
        view.setOnClickListener{view -> onItemClick(view)}
        view.setOnLongClickListener{view -> onLongItemClick(view)}
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.name.text = movies.get(position).titre
        Picasso.get().load(movies.get(position).image_url).into(holder.image)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}