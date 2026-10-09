package com.example.arkin_aismartnotesproductivityassistant.data

import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    val allNotes: Flow<List<Note>> = appDao.getAllNotes()
    val allTasks: Flow<List<Task>> = appDao.getAllTasks()

    suspend fun insertNote(note: Note) {
        appDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        appDao.deleteNote(note)
    }

    suspend fun insertTask(task: Task) {
        appDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        appDao.deleteTask(task)
    }
}
