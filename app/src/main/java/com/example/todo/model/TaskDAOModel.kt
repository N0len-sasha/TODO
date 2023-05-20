package com.example.todo.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDAOModel {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun readAllTasks(): LiveData<List<Task>>
}