package com.wipro.assignment.mvvm.di.appmodule
import android.content.Context
import androidx.room.Room
import com.wipro.assignment.mvvm.db.AboutDatabase
import com.wipro.assignment.mvvm.db.AboutListDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesRoom(appContext : Context) : AboutDatabase {
        return Room
            .databaseBuilder(appContext, AboutDatabase::class.java, "database-about")
            .fallbackToDestructiveMigration()
            .build()    }
    @Singleton
    @Provides
    fun provideAboutDao(aboutDatabase: AboutDatabase): AboutListDao {
        return aboutDatabase.aboutDao()
    }
}