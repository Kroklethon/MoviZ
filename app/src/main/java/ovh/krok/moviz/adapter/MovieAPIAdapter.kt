package ovh.krok.moviz.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.text.method.MovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ovh.krok.moviz.R
import ovh.krok.moviz.model.Movie
import java.util.concurrent.Executors


abstract class MovieAPIAdapter(val movies : List<Movie>):
    RecyclerView.Adapter<MovieAPIAdapter.MovieHolder>(){

    class MovieHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById<TextView>(R.id.movie_name)
        val image : ImageView = itemView.findViewById<ImageView>(R.id.movie_image)
    }
    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view : View) : Boolean
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAPIAdapter.MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_api, parent, false)
        view.setOnClickListener{view -> onItemClick(view)}
        view.setOnLongClickListener{view -> onLongItemClick(view)}
        return MovieAPIAdapter.MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAPIAdapter.MovieHolder, position: Int) {
        holder.name.text = movies.get(position).titre
        setImageFromInternet(holder.image, position)
    }

    fun setImageFromInternet(itemView : ImageView,position: Int){

        val executor = Executors.newSingleThreadExecutor()


        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null

        executor.execute {

            val imageURL = movies.get(position).image_url
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    itemView.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun getItemCount(): Int {
        return movies.size
    }
}