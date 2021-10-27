package ovh.krok.moviz.storage

import android.content.Context
import android.content.SharedPreferences

import ovh.krok.moviz.model.Event
import ovh.krok.moviz.storage.utility.Storage

object EventStorage {

    private const val PREF_NAME = "ovh.krok.moviz.preferences"
    private const val PREF_STORAGE = "storage"
    const val PREF_STORAGE_NONE = 0
    const val PREF_STORAGE_FILE_JSON = 1
    const val PREF_STORAGE_FILE_CSV = 2
    const val PREF_STORAGE_DATA_BASE = 3
    private const val PREF_STORAGE_DEFAULT = PREF_STORAGE_NONE

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getPreferencesStorage(context: Context): Int {
        return getPreferences(context).getInt(PREF_STORAGE, PREF_STORAGE_DEFAULT)
    }

    fun setPreferencesStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(PREF_STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<Event> {
        lateinit var storage: Storage<Event>
        when (getPreferencesStorage(context)) {
            PREF_STORAGE_NONE -> storage
            PREF_STORAGE_FILE_JSON -> storage
            PREF_STORAGE_FILE_CSV -> storage
            PREF_STORAGE_DATA_BASE -> storage
        }
        return storage
    }

}