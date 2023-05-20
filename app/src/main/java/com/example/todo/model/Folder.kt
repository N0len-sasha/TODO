package com.example.todo.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder_table")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val idFolder: Int,
    val nameFolder: String
)


