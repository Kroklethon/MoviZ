package ovh.krok.moviz.model

import org.json.JSONObject
import java.io.Serializable

class Movie(val titre : String,
            val image_url : String,
            val description : String )
    : Serializable {


    override fun toString(): String {
        return "$titre , $image_url"
    }


}