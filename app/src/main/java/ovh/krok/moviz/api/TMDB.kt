package ovh.krok.moviz.api

import android.content.Context
import com.android.volley.Response
import org.json.JSONArray
import org.json.JSONObject
import ovh.krok.moviz.api.utility.APIGeneric
import ovh.krok.moviz.model.Movie
import java.util.ArrayList

class TMDB(context: Context) : APIGeneric(context, API_URL, API_TOKEN){

    companion object {
        private const val API_URL = "https://api.themoviedb.org/3"
        private const val API_ASSET = "https://image.tmdb.org/t/p/w500"
        // It's a read-only token, don't bother
        private const val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTYyNTRlMTgwNWRlYTIxNDU2ZWNhY2I3M2I5NGNmNyIsInN1YiI6IjYxNzExMDBmNmQ0Yzk3MDAyNzI5NTU3NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MafTX6qNT7MEB8hHmjPHuG_XViaqzrQ7VREC_YmGj-o"
        private const val ROOT = "https://themoviedb.org"
        
        fun getMovieLink(idMovie: String) : String {
            // TODO: 27/10/2021 move it to class function and make the class a singleton 
            return "$ROOT/movie/$idMovie?language=fr"
        }
    }

    fun getDuration(movie: Movie, callback: (Int) -> Unit) {
        get("/movie/${movie.id}", hashMapOf()) { response ->
            callback(response.getInt("runtime"))
        }
    }

    fun search(query: String, callback: (List<Movie>) -> Unit) {
        val params: HashMap<String, String> = hashMapOf()
        params["language"] = "fr" // @TODO: get lang of system
        params["query"] = query.trim().replace(" ", "+")
        params["include_adult"] = "false"


        // Pas compris pourquoi la callback devait être en dehors des arguments, mais voici :
        // https://stackoverflow.com/a/53376287/14647155
        get(
            "/search/movie",
            params
        ) { response ->
            val obj = response.getJSONArray("results")

            val movies: ArrayList<Movie> = arrayListOf()
            for (i in 0 until obj.length()) {

                val movieJson = obj.getJSONObject(i)

                val image_url = movieJson.getString("poster_path")
                val backdrop_url = movieJson.getString("backdrop_path")

                val movie = Movie(
                    movieJson.getString("title"),
                    if(image_url.isEmpty()) "" else API_ASSET + image_url,
                    movieJson.getString("overview"),
                    if(backdrop_url.isEmpty()) "" else API_ASSET + backdrop_url,
                    movieJson.getString("id"),
                    0
                )
                getDuration(movie) {
                    movie.duration = it
                }
                movies.add(movie)
            }

            callback(movies)
        }
    }

}