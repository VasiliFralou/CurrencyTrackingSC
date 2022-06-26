package by.vfdev.currencytrackingsc.LocalDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.vfdev.currencytrackingsc.DataSourse.CurrencyTrackingEntity

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
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return currencyDatabase!!
        }

//        @Volatile
//        private var instance: CurrencyDatabase? = null
//        private val  LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also { instance = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                CurrencyDatabase::class.java,
//                "currency_db.db"
//            ).build()
    }
}