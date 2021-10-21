package ovh.krok.moviz.model

import java.io.Serializable

class Movie(val titre : String,
            val image_url : String
):Serializable{
    override fun toString(): String {
        return "$titre , $image_url"
    }
}