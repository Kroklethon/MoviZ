package ovh.krok.moviz.model

import org.json.JSONObject
import java.io.Serializable

class Movie(val titre : String,
            val image_url : String,
            val description : String )
    : Serializable {

    companion object{
        const val NAME = "MOVIE_NAME"
        const val IMAGE_URL = "MOVIE_IMAGE_IRL"
        const val DESCRIPTION = "MOVIE_DESCRIPTION"
    }

    override fun toString(): String {
        return "$titre , $image_url"
    }


}