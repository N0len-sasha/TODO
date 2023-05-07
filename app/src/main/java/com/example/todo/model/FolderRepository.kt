package com.example.todo.model

import androidx.lifecycle.LiveData

class FolderRepository(private val folderDAOModel:FolderDAOModel) {
    fun addFolder(folder:Folder){
        folderDAOModel.addFolder(folder)
    }

    fun readALLFolder(): LiveData<List<Folder>> {
        return folderDAOModel.readAllFolders()
    }
}