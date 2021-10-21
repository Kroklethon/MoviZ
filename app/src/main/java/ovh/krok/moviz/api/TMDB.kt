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
        var params: HashMap<String, String> = hashMapOf()
        params["language"] = "fr" // @TODO: get lang of system
        params["query"] = serializeParameters(params)

        get(
            "/search/movie",
            params,
            Response.Listener<JSONObject> { response ->
                val obj = response.getJSONArray("results")
                callback(obj)
            }
        )
    }


    override fun onResponse(obj: JSONObject) {
        val result = obj
    }




}