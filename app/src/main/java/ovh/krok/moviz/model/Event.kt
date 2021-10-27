package ovh.krok.moviz.model

import java.io.Serializable

class Event(val movie: Movie, val date : String, val location : String) : Serializable {

    companion object{
        const val DATE = "EVENT_DATE"
        const val LOCATION = "EVENT_LOCATION"
        const val MOVIE = "EVENT_MOVIE"
    }
    override fun toString(): String {
        return "$movie, $date, $location"
    }
}