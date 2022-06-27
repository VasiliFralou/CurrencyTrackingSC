package by.vfdev.currencytrackingsc.Utils

import androidx.room.TypeConverter
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromString(value: String): Rates {
        val listType = object : TypeToken<Rates>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromRates(rates: Rates): String {
        val gson = Gson()
        return gson.toJson(rates)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromRatesList(value: List<Rates>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Rates>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRatesList(value: String): List<Rates> {
        val gson = Gson()
        val type = object : TypeToken<List<Rates>>() {}.type
        return gson.fromJson(value, type)
    }
}