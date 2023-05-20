package com.example.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val idTask: Long,
    val name: String,
    val remind: String,
    val comment: String,
    val writeText: String
)