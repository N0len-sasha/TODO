package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.DataBase
import com.example.todo.model.Folder
import com.example.todo.model.FolderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FolderViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Folder>>
    private val repository: FolderRepository

    init{
        val folderDao = DataBase.getDatabase(application).folderDAOModel()
        repository = FolderRepository(folderDao)
        readAllData = repository.readAllData

    }

    fun addFolder(folder: Folder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFolder(Folder(0, folder.nameFolder))
        }
    }

    fun deleteFolder(folder: Folder){
        viewModelScope.launch ( Dispatchers.IO){
            repository.deleteFolder(folder)
        }
    }

    fun updateFolder(folder: Folder){
        viewModelScope.launch ( Dispatchers.IO){
            repository.updateFolder(folder)
        }
    }
}