package by.vfdev.currencytrackingsc.localmodel.currency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.vfdev.currencytrackingsc.utils.Converters

@Database(entities = [CurrencyTrackingData::class], version = 1)
@TypeConverters(Converters::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrencyDao() : ICurrencyDao

    companion object {

        private var currencyDatabase : CurrencyDatabase? = null
        fun getDataBase(contextApplication: Context): CurrencyDatabase {
            if (currencyDatabase == null) {
                currencyDatabase = Room.databaseBuilder(
                    contextApplication,
                    CurrencyDatabase::class.java, "currency_db"
                )
                    .build()
            }
            return currencyDatabase!!
        }
    }
}