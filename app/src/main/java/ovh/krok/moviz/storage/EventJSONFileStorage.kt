package ovh.krok.moviz.storage

import android.content.Context
import org.json.JSONObject
import ovh.krok.moviz.model.Event

class ContactJSONFileStorage(context: Context,name :String) : JSONFileStorage<Event>(context ,name ) {
    override fun create(id: Int, obj: Event): Event {
        return Event(obj.movie, obj.date,obj.location)
    }

    override fun objectToJson(id: Int, obj: Event): JSONObject {
        TODO("Not yet implemented")
    }

    override fun jsonToObject(json: JSONObject): Event {
        TODO("Not yet implemented")
    }
}