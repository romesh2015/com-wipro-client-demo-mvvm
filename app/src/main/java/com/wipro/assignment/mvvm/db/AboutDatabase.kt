package com.wipro.assignment.mvvm.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.wipro.assignment.mvvm.repository.data.AboutList

@Database(entities = [(AboutList::class)], version = 1)
abstract class AboutDatabase : RoomDatabase(){
    abstract fun aboutDao() : AboutListDao
}