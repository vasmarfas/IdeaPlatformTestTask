package ru.vasmarfas.ideaplatformtesttask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vasmarfas.ideaplatformtesttask.data.db.AppDatabase
import ru.vasmarfas.ideaplatformtesttask.data.db.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIDatabaseModule {

    @Provides
    @Singleton
    fun injectRoomDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "database")
        .createFromAsset("data.db").build()

    @Provides
    @Singleton
    fun injectDao(
        database: AppDatabase,
    ): UserDao = database.userDao()

}