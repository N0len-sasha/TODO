package com.example.todo.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FolderDAOModel {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFolder(folder: Folder)

    @Query("SELECT * FROM folder_table")
    fun readAllFolders(): LiveData<List<Folder>>
}