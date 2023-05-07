package com.example.todo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Создаем БД, если = 0
//Класс, который содержит БД и служит точкой доступа для подключения к данным приложения
@Database(entities = [Folder::class, Task::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {
    abstract fun folderDAOModel(): FolderDAOModel

    companion object {
        @Volatile
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "db.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}