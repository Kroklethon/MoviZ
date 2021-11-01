package ovh.krok.moviz.helpers

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Calendar

object helpers {
    fun dateToLong(datetime: String): Long {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.FRANCE)
        val localDate = LocalDateTime.parse(datetime, formatter)
        return localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
    }

    fun getendMillis(beginMillis : Long, duration: Int): Long {
        val cal = Calendar.getInstance()
        cal.timeInMillis = beginMillis
        cal.add(Calendar.MINUTE, duration)
        return cal.timeInMillis
    }

}