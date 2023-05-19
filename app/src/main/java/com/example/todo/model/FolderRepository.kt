package com.example.todo.model

import androidx.lifecycle.LiveData

class FolderRepository(private val folderDAOModel:FolderDAOModel) {

    val readAllData: LiveData<List<Folder>> = folderDAOModel.readAllFolders()

    fun addFolder(folder:Folder){
        folderDAOModel.addFolder(folder)
    }

    fun readALLFolder(): LiveData<List<Folder>> {
        return folderDAOModel.readAllFolders()
    }

    fun deleteFolder(folder: Folder){
        folderDAOModel.deleteFolder(folder)
    }

}