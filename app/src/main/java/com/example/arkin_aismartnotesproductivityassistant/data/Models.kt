package com.example.arkin_aismartnotesproductivityassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val tag: String,
    val color: String,
    val isSecure: Boolean
)

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val title: String,
    val isCompleted: Boolean
)

data class HomeworkItem(
    val id: String,
    val subject: String,
    val title: String,
    val due: String,
    val color: String
)

data class ScheduleItem(
    val id: String,
    val subject: String,
    val time: String,
    val room: String,
    val color: String
)

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: Long
)

data class ReminderItem(
    val id: String,
    val title: String,
    val time: String,
    val icon: String,
    val color: String
)
