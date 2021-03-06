package ovh.krok.moviz.api.utility

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

abstract class APIGeneric(
    private val context: Context,
    private val API_URL: String,
    private val API_TOKEN: String) {

    private fun serializeParameters(params: HashMap<String, String>) : String {
        val keyJoined: ArrayList<String> = arrayListOf()
        params.toList().forEach { (key, value) ->
            keyJoined.add("$key=$value")
        }
        return keyJoined.joinToString("&")
    }

    protected fun get(path: String, params: HashMap<String, String>, callback: Response.Listener<JSONObject>) {
        val apiParams = serializeParameters(params)
        val url = API_URL + path + "?${apiParams}"
        println(url)


        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = object: JsonObjectRequest(
            Method.GET,
            url,
            null,
            callback,
            { error -> throw error }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                //val headers = super.getHeaders()
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $API_TOKEN"
                return headers
            }
        }

        queue.add(jsonObjectRequest)
        queue.start()
    }
}