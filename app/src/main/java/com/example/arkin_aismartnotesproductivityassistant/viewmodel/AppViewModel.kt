package com.example.arkin_aismartnotesproductivityassistant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.arkin_aismartnotesproductivityassistant.data.AppRepository
import com.example.arkin_aismartnotesproductivityassistant.data.ChatMessage
import com.example.arkin_aismartnotesproductivityassistant.data.HomeworkItem
import com.example.arkin_aismartnotesproductivityassistant.data.Note
import com.example.arkin_aismartnotesproductivityassistant.data.ReminderItem
import com.example.arkin_aismartnotesproductivityassistant.data.ScheduleItem
import com.example.arkin_aismartnotesproductivityassistant.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class AppViewModel(private val repository: AppRepository) : ViewModel() {
    
    val allNotes: StateFlow<List<Note>> = repository.allNotes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val allTasks: StateFlow<List<Task>> = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                text = "Hello Rohan! 👋 I'm Arkin, your AI study companion. Need help organizing your day or summarizing notes?",
                isUser = false,
                timestamp = System.currentTimeMillis()
            )
        )
    )
    val chatMessages = _chatMessages.asStateFlow()

    private val _isVaultUnlocked = MutableStateFlow(false)
    val isVaultUnlocked = _isVaultUnlocked.asStateFlow()

    fun unlockVault() {
        _isVaultUnlocked.value = true
    }

    fun lockVault() {
        _isVaultUnlocked.value = false
    }

    // Dummy data for homework
    val homeworkList = listOf(
        HomeworkItem("h1", "DBMS", "Assignment 4", "Today, 11:59 PM", "purple"),
        HomeworkItem("h2", "OS", "Lab Report", "Tomorrow, 5:00 PM", "orange")
    )

    // Dummy data for timetable
    val timetableList = listOf(
        ScheduleItem("c1", "Operating System", "10:00 AM - 11:00 AM", "Room 402", "orange"),
        ScheduleItem("c2", "Database Systems", "11:30 AM - 1:00 PM", "Lab 2", "purple"),
        ScheduleItem("c3", "Machine Learning", "2:00 PM - 3:30 PM", "Room 305", "teal")
    )

    // Dummy data for reminders
    val remindersList = listOf(
        ReminderItem("r1", "Pay semester fee", "In 2 days", "CurrencyInr", "yellow"),
        ReminderItem("r2", "Library book return", "Tomorrow", "Books", "blue")
    )

    fun addNote(title: String, content: String, tag: String, color: String, isSecure: Boolean, id: String? = null) {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = id ?: UUID.randomUUID().toString(),
                    title = title,
                    content = content,
                    date = System.currentTimeMillis(),
                    tag = tag,
                    color = color,
                    isSecure = isSecure
                )
            )
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.insertTask(
                Task(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    isCompleted = false
                )
            )
        }
    }

    fun toggleTaskComplete(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun sendChatMessage(text: String) {
        val userMsg = ChatMessage(
            id = UUID.randomUUID().toString(),
            text = text,
            isUser = true,
            timestamp = System.currentTimeMillis()
        )
        _chatMessages.value = _chatMessages.value + userMsg

        // AI Mock Response
        viewModelScope.launch {
            kotlinx.coroutines.delay(1200)
            val lower = text.lowercase()
            var responseText = "I'm Arkin! I can help you summarize notes or manage your schedule."
            
            if (lower.contains("note") || lower.contains("summarize")) {
                val notes = allNotes.value
                responseText = if (notes.isNotEmpty()) {
                    "I found a note titled \"${notes.first().title}\". Shall I summarize it?"
                } else {
                    "You have no notes to summarize. Let's create one!"
                }
            } else if (lower.contains("task") || lower.contains("todo")) {
                val pending = allTasks.value.count { !it.isCompleted }
                responseText = if (pending > 0) {
                    "You have $pending pending tasks left today. Keep going!"
                } else {
                    "All tasks are complete! Great job."
                }
            } else if (lower.contains("vault") || lower.contains("locker")) {
                responseText = "The locker is a secure place for your private notes. Enter the PIN (1234 for demo) to access it."
            }

            val aiMsg = ChatMessage(
                id = UUID.randomUUID().toString(),
                text = responseText,
                isUser = false,
                timestamp = System.currentTimeMillis()
            )
            _chatMessages.value = _chatMessages.value + aiMsg
        }
    }

    class Factory(private val repository: AppRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
