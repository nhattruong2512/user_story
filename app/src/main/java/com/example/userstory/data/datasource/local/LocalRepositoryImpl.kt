package com.example.userstory.data.datasource.local

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

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

    override suspend fun insertUser(user: UserDetail) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun insertUsers(users: List<UserDetail>) {
        userDao.insertUsers(users.map { it.toEntity() })
    }

    override fun getUser(loginName: String): Flow<UserEntity> {
        return userDao.getUser(loginName)
    }

    override fun getUsers(): PagingSource<Int, UserEntity> {
        return userDao.getUsers()
    }
}