package ovh.krok.moviz.model

import java.io.Serializable

class Movie(val titre : String,
            val image_url : String,
            val description : String,
            val backdrop_url : String,
            val id : String,
            var duration: Int
            )
    : Serializable {

    companion object{
        const val NAME = "MOVIE_NAME"
        const val IMAGE_URL = "MOVIE_IMAGE_URL"
        const val BACKDROP_URL = "MOVIE_BACKDROP_URL"
        const val DESCRIPTION = "MOVIE_DESCRIPTION"
        const val ID = "ID"
        const val DURATION = "DURATION"
    }

    // debug
    override fun toString(): String {
        return "$titre , $image_url"
    }


}