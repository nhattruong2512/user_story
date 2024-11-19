package com.example.userstory.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.LocalRepository

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class LocalRepositoryImpl: RoomDatabase(), LocalRepository {

    abstract val userDao: UserDao

    companion object {
        fun getInstance(context: Context): LocalRepository {
            return Room
                .databaseBuilder(context, LocalRepositoryImpl::class.java, "user_database")
                .build()
        }
    }

    override suspend fun putUserDetail(user: UserDetail) {
        userDao.putUser(user.toEntity())
    }

    override suspend fun getUser(loginName: String): UserDetail {
        return userDao.getUser(loginName).toUserDetail();
    }
}