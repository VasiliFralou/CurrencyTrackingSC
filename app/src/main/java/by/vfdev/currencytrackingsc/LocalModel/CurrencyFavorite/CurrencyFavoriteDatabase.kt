package by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrencyFavoriteData::class], version = 1)
abstract class CurrencyFavoriteDatabase : RoomDatabase() {
    abstract fun currencyFavoriteDao(): ICurrencyFavoriteDao

    companion object {
        private var currencyFavoriteDatabase : CurrencyFavoriteDatabase? = null
        fun getDataBase(contextApplication: Context): CurrencyFavoriteDatabase {
            if (currencyFavoriteDatabase == null) {
                currencyFavoriteDatabase = Room.databaseBuilder(
                    contextApplication,
                    CurrencyFavoriteDatabase::class.java, "currency_favorite_db"
                ).build()
            }
            return currencyFavoriteDatabase !!
        }
    }
}