package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun LockerScreen(navController: NavController, viewModel: AppViewModel) {
    val isVaultUnlocked by viewModel.isVaultUnlocked.collectAsState()
    var enteredPin by remember { mutableStateOf("") }
    var shakeEffect by remember { mutableStateOf(false) }

    LaunchedEffect(enteredPin) {
        if (enteredPin.length == 4) {
            delay(200)
            if (enteredPin == "1234") {
                viewModel.unlockVault()
                enteredPin = ""
            } else {
                shakeEffect = true
                delay(400)
                shakeEffect = false
                enteredPin = ""
            }
        }
    }

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
                Text(text = "Locker", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            }
            if (isVaultUnlocked) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
                        .clip(CircleShape)
                        .background(IconBgLocker)
                        .clickable { viewModel.lockVault() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PhosphorIcons.Regular.LockKey, contentDescription = "Lock", modifier = Modifier.size(20.dp), tint = IconColorLocker)
                }
            }
        }

        if (isVaultUnlocked) {
            NotesScreen(navController = navController, viewModel = viewModel, isSecureView = true)
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
                        .clip(CircleShape)
                        .background(IconBgLocker),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PhosphorIcons.Fill.ShieldCheck, contentDescription = null, tint = IconColorLocker, modifier = Modifier.size(40.dp))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Enter PIN", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Enter your 4-digit PIN to access secure notes.", fontSize = 14.sp, color = ArkinTextMuted)
                Spacer(modifier = Modifier.height(40.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    for (i in 0..3) {
                        val isFilled = i < enteredPin.length
                        val offset = if (shakeEffect) if (i % 2 == 0) (-4).dp else 4.dp else 0.dp
                        Box(
                            modifier = Modifier
                                .offset(x = offset)
                                .size(16.dp)
                                .clip(CircleShape)
                                .border(2.dp, if (isFilled) ArkinPrimary else Color(0xFFD1D5DB), CircleShape)
                                .background(if (isFilled) ArkinPrimary else Color.Transparent)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                val keys = listOf(
                    "1", "2", "3",
                    "4", "5", "6",
                    "7", "8", "9",
                    "C", "0", "<"
                )
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    for (row in keys.chunked(3)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            for (key in row) {
                                KeypadButton(key = key) {
                                    when (key) {
                                        "C" -> enteredPin = ""
                                        "<" -> if (enteredPin.isNotEmpty()) enteredPin = enteredPin.dropLast(1)
                                        else -> if (enteredPin.length < 4) enteredPin += key
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun KeypadButton(key: String, onClick: () -> Unit) {
    val isNumber = key.toIntOrNull() != null
    Box(
        modifier = Modifier
            .size(64.dp)
            .shadow(if (isNumber) 10.dp else 0.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(CircleShape)
            .background(if (isNumber) Color.White else Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (key == "C") {
            Text(text = "Clear", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ArkinTextMuted)
        } else if (key == "<") {
            Icon(PhosphorIcons.Bold.Backspace, contentDescription = "Backspace", tint = ArkinTextMuted, modifier = Modifier.size(24.dp))
        } else {
            Text(text = key, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        }
    }
}
