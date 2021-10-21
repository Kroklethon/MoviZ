package ovh.krok.moviz.model

import java.io.Serializable

class Event(val movie: Movie, val date : String, val location : String) : Serializable {
}