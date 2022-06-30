package by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_favorite_table")
data class CurrencyFavoriteData(
    val base: String,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)