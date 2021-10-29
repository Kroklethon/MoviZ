package ovh.krok.moviz.helpers

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

object helpers {
    fun dateToLong(datetime: String): Long {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.FRANCE)
        val localDate = LocalDateTime.parse(datetime, formatter)
        return localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
    }
}