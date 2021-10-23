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
    }


    fun search(query: String, callback: (List<Movie>) -> Unit) {
        val params: HashMap<String, String> = hashMapOf()
        params["language"] = "fr" // @TODO: get lang of system
        params["query"] = query.trim().replace(" ", "+")


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
                val movie = Movie(
                    movieJson.getString("original_title") ,
                    API_ASSET + movieJson.getString("poster_path"),
                    movieJson.getString("overview")
                )
                movies.add(movie)
            }

            callback(movies)
        }
    }
}