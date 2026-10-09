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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.example.arkin_aismartnotesproductivityassistant.data.ChatMessage
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiChatScreen(navController: NavController, viewModel: AppViewModel) {
    val messages by viewModel.chatMessages.collectAsState()
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .padding(24.dp)
            .padding(bottom = 80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
                Box(
                    modifier = Modifier.size(40.dp).shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f)).clip(CircleShape).background(IconBgPink),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PhosphorIcons.Fill.Robot, contentDescription = null, tint = IconColorPink, modifier = Modifier.size(20.dp))
                }
                Column {
                    Text(text = "Arkin AI", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF22C55E)))
                        Text(text = "Online", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = Color(0xFF22C55E))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(10.dp, RoundedCornerShape(12.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable { /* Menu */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Regular.DotsThreeVertical, contentDescription = "Menu", modifier = Modifier.size(20.dp), tint = ArkinText)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFF9FAFB), RoundedCornerShape(24.dp))
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    reverseLayout = false
                ) {
                    items(messages) { msg ->
                        ChatBubble(msg = msg)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(16.dp))
                        .background(ArkinBg)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("Ask me anything...", color = ArkinTextMuted, fontSize = 14.sp) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {
                            if (inputText.isNotBlank()) {
                                viewModel.sendChatMessage(inputText)
                                inputText = ""
                            }
                        })
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(10.dp, RoundedCornerShape(12.dp), spotColor = ArkinPrimary.copy(alpha = 0.3f))
                            .clip(RoundedCornerShape(12.dp))
                            .background(ArkinPrimary)
                            .clickable {
                                if (inputText.isNotBlank()) {
                                    viewModel.sendChatMessage(inputText)
                                    inputText = ""
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(PhosphorIcons.Fill.PaperPlaneRight, contentDescription = "Send", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(msg: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!msg.isUser) {
            Box(
                modifier = Modifier.size(32.dp).clip(CircleShape).background(IconBgPink),
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Fill.Robot, contentDescription = null, tint = IconColorPink, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        val bgColor = if (msg.isUser) ArkinPrimary else Color(0xFFEEF2FF)
        val textColor = if (msg.isUser) Color.White else ArkinText
        val shape = if (msg.isUser) RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp) else RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp)

        Box(
            modifier = Modifier
                .widthIn(max = 250.dp)
                .shadow(if (msg.isUser) 5.dp else 2.dp, shape, spotColor = Color.Black.copy(alpha = 0.05f))
                .clip(shape)
                .background(bgColor)
                .border(1.dp, if (!msg.isUser) Color(0xFFE0E7FF).copy(alpha = 0.5f) else Color.Transparent, shape)
                .padding(12.dp)
        ) {
            Text(text = msg.text, fontSize = 14.sp, color = textColor, lineHeight = 20.sp)
        }

        if (msg.isUser) {
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier.size(32.dp).clip(CircleShape).background(Brush.linearGradient(listOf(Color(0xFFC084FC), Color(0xFF60A5FA)))),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Text("R", color = ArkinPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}
