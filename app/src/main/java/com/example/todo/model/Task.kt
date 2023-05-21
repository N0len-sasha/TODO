package com.example.todo.model

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.R

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val idTask: Long,
    val name: String,
    val remind: String,
)