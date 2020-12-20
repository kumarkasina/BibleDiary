package com.example.jesusapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jesusapp.data.model.DonarModelItem
import com.example.jesusapp.data.model.HomeDataModel1Item
import com.example.jesusapp.data.model.Users
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class UserDao {

    @Query("SELECT * FROM Users")
    abstract fun getAllUsers(): Flow<List<Users>>

    @Query("SELECT * FROM Features")
    abstract fun getAllFeatures(): Flow<List<HomeDataModel1Item>>

    @Query("SELECT * FROM Users")
    abstract suspend fun getAllUsers2(): List<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(users: List<Users>)

    @Query("DELETE FROM Users")
    abstract fun deleteAllUsers()

    @Query("DELETE FROM Features")
    abstract fun deleteAllFeatures()

    @Query("SELECT * FROM Users WHERE id = :id")
    abstract fun getUser(id: String): Flow<Users>

    @ExperimentalCoroutinesApi
    fun getUserDistinctUntilChanged(id: String) =
        getUser(id).distinctUntilChanged()

    @ExperimentalCoroutinesApi
    fun getAllUsersDistinctUntilChanged() =
        getAllUsers().distinctUntilChanged()

    @ExperimentalCoroutinesApi
    fun getAllFeaturesDistinctUntilChanged() =
        getAllFeatures().distinctUntilChanged()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHomePag1(users: List<HomeDataModel1Item>)

    @Query("SELECT * FROM Features")
    abstract suspend fun getHomeData1(): List<HomeDataModel1Item>
}
