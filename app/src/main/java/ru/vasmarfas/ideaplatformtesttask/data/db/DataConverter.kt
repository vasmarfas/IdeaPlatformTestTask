package ru.vasmarfas.ideaplatformtesttask.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object DataConverter {
    @TypeConverter
    fun fromListOfStrings(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListOfStrings(value: String): List<String> {
        return Json.decodeFromString(value)
    }

}
