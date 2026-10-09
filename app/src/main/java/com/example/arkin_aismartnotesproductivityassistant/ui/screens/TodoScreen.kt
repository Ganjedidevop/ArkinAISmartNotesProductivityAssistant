package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
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
import com.example.arkin_aismartnotesproductivityassistant.data.Task
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController, viewModel: AppViewModel) {
    val tasks by viewModel.allTasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .padding(24.dp)
            .padding(bottom = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Regular.ArrowLeft, contentDescription = "Back", modifier = Modifier.size(20.dp), tint = ArkinText)
            }
            Text(text = "To-Do List", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                placeholder = { Text("Add a new task...", color = Color.Gray, fontSize = 14.sp) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ArkinPrimary.copy(alpha = 0.2f),
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(newTaskTitle)
                        newTaskTitle = ""
                    }
                })
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = ArkinPrimary.copy(alpha = 0.3f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(ArkinPrimary)
                    .clickable {
                        if (newTaskTitle.isNotBlank()) {
                            viewModel.addTask(newTaskTitle)
                            newTaskTitle = ""
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Regular.Plus, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(20.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        if (tasks.isEmpty()) {
            Text("All caught up!", color = ArkinTextMuted, fontSize = 14.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(tasks.sortedBy { it.isCompleted }) { task ->
                    TodoItem(
                        task = task,
                        onToggle = { viewModel.toggleTaskComplete(it) },
                        onDelete = { viewModel.deleteTask(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun TodoItem(task: Task, onToggle: (Task) -> Unit, onDelete: (Task) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(if (!task.isCompleted) 10.dp else 0.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, if (task.isCompleted) Color(0xFFF3F4F6) else Color.Transparent, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(2.dp, if (task.isCompleted) ArkinPrimary else ArkinTextMuted, RoundedCornerShape(6.dp))
                    .background(if (task.isCompleted) ArkinPrimary else Color.Transparent)
                    .clickable { onToggle(task) },
                contentAlignment = Alignment.Center
            ) {
                if (task.isCompleted) {
                    Icon(PhosphorIcons.Regular.Plus, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                }
            }
            Text(
                text = task.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (task.isCompleted) Color.Gray else ArkinText,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
        }
        IconButton(onClick = { onDelete(task) }, modifier = Modifier.size(24.dp)) {
            Icon(PhosphorIcons.Fill.Trash, contentDescription = "Delete", tint = Color(0xFFD1D5DB), modifier = Modifier.size(18.dp))
        }
    }
}
