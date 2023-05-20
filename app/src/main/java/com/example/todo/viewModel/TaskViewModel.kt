package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.DataBase
import com.example.todo.model.Task
import com.example.todo.model.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    val id = MutableLiveData<Long>()

    init {
        val taskDao = DataBase.getDatabase(application).taskDAOModel()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(
                Task(
                    task.idTask,
                    task.name,
                    task.remind,
                    task.comment,
                    task.writeText
                )
            )
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
}
