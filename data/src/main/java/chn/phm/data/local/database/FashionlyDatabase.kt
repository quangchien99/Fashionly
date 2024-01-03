package chn.phm.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import chn.phm.data.local.database.dao.FashionlyResultDao
import chn.phm.data.local.database.entity.FashionlyResultEntity

@Database(entities = [FashionlyResultEntity::class], version = 1)
abstract class FashionlyDatabase : RoomDatabase() {

    abstract fun fashionlyResultDao(): FashionlyResultDao
}
