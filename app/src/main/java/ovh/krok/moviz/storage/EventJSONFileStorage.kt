package ovh.krok.moviz.storage

import android.content.Context
import org.json.JSONObject
import ovh.krok.moviz.model.Event
import ovh.krok.moviz.model.Movie

class EventJSONFileStorage(context: Context,name :String) : JSONFileStorage<Event>(context ,name ) {

    override fun create(id: Int, obj: Event): Event {
        return Event(obj.movie, obj.date,obj.location)
    }

    override fun objectToJson(id: Int, obj: Event): JSONObject {
        val m = JSONObject()
        val e = JSONObject()

        m.put(Movie.NAME, obj.movie.titre)
        m.put(Movie.IMAGE_URL, obj.movie.image_url)
        m.put(Movie.DESCRIPTION, obj.movie.description)
        m.put(Movie.BACKDROP_URL, obj.movie.backdrop_url)

        e.put(Event.MOVIE, m)
        e.put(Event.DATE, obj.date)
        e.put(Event.LOCATION, obj.location)
        return e
    }

    override fun jsonToObject(json: JSONObject): Event {
        val movie = Movie(
            json.getString(Movie.NAME),
            json.getString(Movie.IMAGE_URL),
            json.getString(Movie.DESCRIPTION),
            json.getString(Movie.BACKDROP_URL)
        )
        return Event(movie, json.getString(Event.DATE), json.getString(Event.LOCATION))
    }
}