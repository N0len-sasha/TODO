package com.example.todo.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FolderDAOModel {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFolder(folder: Folder)

    @Delete
    fun deleteFolder(folder: Folder)

    @Update
    fun updateFolder(folder: Folder)

    @Query("SELECT * FROM folder_table")
    fun readAllFolders(): LiveData<List<Folder>>
}