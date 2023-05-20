package com.example.todo.model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDAOModel: TaskDAOModel) {
    val readAllData: LiveData<List<Task>> = taskDAOModel.readAllTasks()

    fun addTask(task: Task) {
        taskDAOModel.addTask(task)
    }

    fun readAllTask(): LiveData<List<Task>> {
        return taskDAOModel.readAllTasks()
    }

    fun deleteTask(task: Task) {
        taskDAOModel.deleteTask(task)
    }

    fun updateTask(task: Task) {
        taskDAOModel.updateTask(task)
    }
}