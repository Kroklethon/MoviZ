package ovh.krok.moviz.api

import android.content.Context
import com.android.volley.Response
import org.json.JSONArray
import org.json.JSONObject
import ovh.krok.moviz.api.utility.APIGeneric

class TMDB(context: Context) : APIGeneric(context, API_URL, API_TOKEN){

    companion object {
        private const val API_URL = "https://api.themoviedb.org/3"
        // It's a read-only token, don't bother
        private const val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTYyNTRlMTgwNWRlYTIxNDU2ZWNhY2I3M2I5NGNmNyIsInN1YiI6IjYxNzExMDBmNmQ0Yzk3MDAyNzI5NTU3NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MafTX6qNT7MEB8hHmjPHuG_XViaqzrQ7VREC_YmGj-o"
    }


    fun search(query: String, callback: (JSONArray) -> Unit) {
        val params: HashMap<String, String> = hashMapOf()
        params["language"] = "fr" // @TODO: get lang of system
        params["query"] = query.replace(" ", "+")


        // Pas compris pourquoi la callback devait être en dehors des arguments, mais voici :
        // https://stackoverflow.com/a/53376287/14647155
        get(
            "/search/movie",
            params
        ) { response ->
            val obj = response.getJSONArray("results")
            callback(obj)
        }
    }
}