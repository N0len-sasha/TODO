package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.DB
import com.example.todo.model.Folder
import com.example.todo.model.FolderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class exampleViewModel(application: Application) : AndroidViewModel(application) {
    val folderDAOModel = DB.getDatabase(application).folderDAOModel()
    val repository: FolderRepository = FolderRepository(folderDAOModel)

    fun addFolder(folder: Folder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFolder(Folder(0, "Новая папка"))
        }
    }

    fun readFolder(): LiveData<List<Folder>> {
        return repository.readALLFolder()
    }
}