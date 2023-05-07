package com.example.todo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(foreignKeys = [ForeignKey(entity = Folder::class, parentColumns = ["idFolder"],
    childColumns = ["taskId"], onDelete = ForeignKey.CASCADE
)], tableName = "task_table")
data class Task(
    @PrimaryKey val idTask: Long,
    val name: String,
    val taskId: Long
)