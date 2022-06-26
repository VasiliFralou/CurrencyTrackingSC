package by.vfdev.currencytrackingsc.LocalModel

import androidx.room.PrimaryKey

data class Currency (
    val base: String,
    val rate: String,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)