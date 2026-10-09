package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.bold.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.fill.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.regular.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.light.*
import com.adamglin.phosphoricons.*
import com.example.arkin_aismartnotesproductivityassistant.data.Note
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NotesScreen(navController: NavController, viewModel: AppViewModel, isSecureView: Boolean = false) {
    val allNotes by viewModel.allNotes.collectAsState()
    val notes = allNotes.filter { it.isSecure == isSecureView }
    
    var editingNote by remember { mutableStateOf<Note?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .padding(24.dp)
            .padding(bottom = if (isSecureView) 0.dp else 80.dp)
    ) {
        if (!isSecureView) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "My Notes", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(10.dp, RoundedCornerShape(12.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable { /* Search */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PhosphorIcons.Regular.MagnifyingGlass, contentDescription = "Search", modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        if (notes.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(50)).background(Color(0xFFF3F4F6)), contentAlignment = Alignment.Center) {
                    Icon(PhosphorIcons.Regular.FileDashed, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "No notes here", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = ArkinTextMuted)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(notes) { note ->
                    NoteItem(
                        note = note, 
                        onDelete = { viewModel.deleteNote(it) },
                        onClick = { editingNote = note }
                    )
                }
            }
        }
    }

    if (editingNote != null) {
        NoteModal(
            onDismiss = { editingNote = null },
            initialTitle = editingNote!!.title,
            initialContent = editingNote!!.content,
            initialTag = editingNote!!.tag,
            onSave = { title, content, tag, color ->
                viewModel.addNote(title, content, tag, color, editingNote!!.isSecure, editingNote!!.id)
                editingNote = null
            }
        )
    }
}

@Composable
fun NoteItem(note: Note, onDelete: (Note) -> Unit, onClick: () -> Unit) {
    val (bgColor, textColor) = when (note.color) {
        "purple" -> IconBgPurple to IconColorPurple
        "teal" -> IconBgTeal to IconColorTeal
        "orange" -> IconBgOrange to IconColorOrange
        else -> IconBgPurple to IconColorPurple
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(bgColor).padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(text = note.tag.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textColor, letterSpacing = 1.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = java.text.SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(note.date)), fontSize = 12.sp, fontWeight = FontWeight.Medium, color = ArkinTextMuted)
                    IconButton(onClick = { onDelete(note) }, modifier = Modifier.size(24.dp)) {
                        Icon(PhosphorIcons.Fill.Trash, contentDescription = "Delete", tint = Color(0xFFD1D5DB), modifier = Modifier.size(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ArkinText, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content, fontSize = 14.sp, color = ArkinTextMuted, maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 20.sp)
        }
    }
}
