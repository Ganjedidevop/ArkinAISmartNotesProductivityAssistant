package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteModal(
    onDismiss: () -> Unit,
    initialTitle: String = "",
    initialContent: String = "",
    initialTag: String = "Personal",
    onSave: (title: String, content: String, tag: String, color: String) -> Unit
) {
    var title by remember { mutableStateOf(initialTitle) }
    var content by remember { mutableStateOf(initialContent) }
    var selectedTag by remember { mutableStateOf(initialTag) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "New Note",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = ArkinText
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .background(Color(0xFFF3F4F6), RoundedCornerShape(50))
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = PhosphorIcons.Regular.X,
                            contentDescription = "Close",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Title", color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ArkinText),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = ArkinPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Start typing your ideas here...", color = Color.Gray) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, color = ArkinTextMuted),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = ArkinPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TagChip(
                            label = "Personal",
                            isSelected = selectedTag == "Personal",
                            bgColor = IconBgPurple,
                            textColor = IconColorPurple,
                            onClick = { selectedTag = "Personal" }
                        )
                        TagChip(
                            label = "Study",
                            isSelected = selectedTag == "Study",
                            bgColor = IconBgTeal,
                            textColor = IconColorTeal,
                            onClick = { selectedTag = "Study" }
                        )
                    }
                    
                    Button(
                        onClick = {
                            val color = if (selectedTag == "Personal") "purple" else "teal"
                            if (title.isNotBlank()) {
                                onSave(title, content, selectedTag, color)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ArkinPrimary),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text("Save", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun TagChip(
    label: String,
    isSelected: Boolean,
    bgColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) bgColor else Color(0xFFF3F4F6))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) textColor else Color.Gray
        )
    }
}
