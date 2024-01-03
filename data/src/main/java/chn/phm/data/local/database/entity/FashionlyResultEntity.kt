package chn.phm.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import chn.phm.data.utils.Constants.FASHIONLY_RESULT_TABLE_NAME

@Entity(tableName = FASHIONLY_RESULT_TABLE_NAME)
data class FashionlyResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timeCreated: Long,
    val modelImageUri: String,
    val clothImageUri: String,
    val resultUrl: String,
    val prompt: String,
    val clothType: String
)
