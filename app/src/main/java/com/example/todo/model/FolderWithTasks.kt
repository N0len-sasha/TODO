package com.example.todo.model

import androidx.room.Embedded
import androidx.room.Relation

data class FolderWithTasks(
    @Embedded val folder: Folder,
    @Relation(
        parentColumn = "idFolder",
        entityColumn = "taskId"
    )
    val tasks: List<Task>
)