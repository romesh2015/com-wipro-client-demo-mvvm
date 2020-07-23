package com.wipro.assignment.mvvm.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wipro.assignment.mvvm.repository.data.AboutList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
@Dao
abstract class AboutListDao {
    @Query("SELECT * FROM AboutList")
    abstract fun getAllAboutData(): Flow<List<AboutList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAboutList(about: List<AboutList>)

    @Query("DELETE FROM AboutList")
    abstract fun deleteAllAboutData()

    @Query("SELECT * FROM AboutList WHERE id = :id")
    abstract fun getData(id: String): Flow<AboutList>

    @ExperimentalCoroutinesApi
    fun getAllDistinctUntilChanged(id:String) =
        getData(id).distinctUntilChanged()

    @ExperimentalCoroutinesApi
    fun getAllDistinctUntilChanged() =
        getAllAboutData().distinctUntilChanged()

}
