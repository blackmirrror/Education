package ru.blackmirrror.dbnetworkpatterns.task12

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.blackmirrror.dbnetworkpatterns.task12.dao.BouquetDao
import ru.blackmirrror.dbnetworkpatterns.task12.dao.BouquetFlowerDao
import ru.blackmirrror.dbnetworkpatterns.task12.model.Bouquet
import ru.blackmirrror.dbnetworkpatterns.task12.model.BouquetFlower
import ru.blackmirrror.dbnetworkpatterns.task12.model.Flower

@Database(entities = [Bouquet::class, Flower::class, BouquetFlower::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bouquetDao(): BouquetDao
    abstract fun bouquetFlowerDao(): BouquetFlowerDao

    companion object {

        private const val DATABASE_NAME = "bouquets.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE bouquets ADD COLUMN design TEXT DEFAULT 'undefined' NOT NULL")
                db.execSQL("ALTER TABLE flowers ADD COLUMN country_of_manufacture TEXT DEFAULT 'undefined' NOT NULL")
            }
        }

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build()
    }
}