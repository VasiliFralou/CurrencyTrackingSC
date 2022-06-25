package by.vfdev.currencytrackingsc.LocalModel

import androidx.room.TypeConverter
import by.vfdev.currencytrackingsc.RemoteModel.Rates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromString(value: String): Rates {
        val listType = object : TypeToken<Rates>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: Rates): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}