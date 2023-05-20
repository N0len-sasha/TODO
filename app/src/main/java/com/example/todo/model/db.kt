package com.example.todo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Создаем БД, если = 0
//Класс, который содержит БД и служит точкой доступа для подключения к данным приложения
@Database(entities = [Folder::class, Task::class], version = 2, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun folderDAOModel(): FolderDAOModel
    abstract fun taskDAOModel(): TaskDAOModel

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "DB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}