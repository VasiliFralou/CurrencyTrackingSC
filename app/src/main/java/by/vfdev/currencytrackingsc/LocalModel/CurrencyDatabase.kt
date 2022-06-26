package by.vfdev.currencytrackingsc.LocalModel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTrackingEntity

@Database(entities = [CurrencyTrackingEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrencyDao() : ICurrencyDao

    companion object {

        @Volatile
        private var instance: CurrencyDatabase? = null
        private val  LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CurrencyDatabase::class.java,
                "currency_db.db"
            ).build()
    }
}