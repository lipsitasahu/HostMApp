package com.example.hostmapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SettingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(setting: SettingEntity)

    @Query("SELECT * FROM settings WHERE key = :key")
    suspend fun getSetting(key: String): SettingEntity?
}
