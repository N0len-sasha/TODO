package com.example.todo.model


import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity (tableName = "folder_table")
data class Folder(
    @PrimaryKey(autoGenerate = true) val idFolder: Int,
    val nameFolder: String
)



