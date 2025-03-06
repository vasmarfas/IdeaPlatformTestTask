package ru.vasmarfas.ideaplatformtesttask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemsTable::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}


//Room.databaseBuilder(applicationContext, AppDatabase::class.java, "AppDB.db")
//.createFromAsset("data.db")
//.build()
