package chn.phm.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import chn.phm.data.local.database.entity.FashionlyResultEntity
import chn.phm.data.utils.Constants.FASHIONLY_RESULT_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface FashionlyResultDao {

    @Query("SELECT * FROM $FASHIONLY_RESULT_TABLE_NAME")
    fun getAllFashionlyResults(): Flow<List<FashionlyResultEntity>>

    @Insert
    suspend fun insertFashionlyResult(fashionlyResult: FashionlyResultEntity): Long
}
