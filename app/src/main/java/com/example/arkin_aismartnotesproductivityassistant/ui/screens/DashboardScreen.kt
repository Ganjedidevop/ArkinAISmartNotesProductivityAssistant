package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(navController: NavController, viewModel: AppViewModel) {
    val notes by viewModel.allNotes.collectAsState()
    val tasks by viewModel.allTasks.collectAsState()
    val completedTasks = tasks.count { it.isCompleted }
    val totalTasks = tasks.size
    val homeworkList = viewModel.homeworkList
    
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .verticalScroll(scrollState)
            .padding(24.dp)
            .padding(bottom = 80.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Menu */ }) {
                Icon(PhosphorIcons.Regular.List, contentDescription = "Menu", modifier = Modifier.size(24.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(PhosphorIcons.Regular.MagnifyingGlass, contentDescription = "Search", modifier = Modifier.size(24.dp))
                Box(modifier = Modifier.clickable { navController.navigate("reminders") }) {
                    Icon(PhosphorIcons.Regular.Bell, contentDescription = "Notifications", modifier = Modifier.size(24.dp))
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                            .align(Alignment.TopEnd)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(Color(0xFFC084FC), Color(0xFF60A5FA))))
                        .clickable { navController.navigate("profile") },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White)) {
                        // Avatar placeholder
                        Text("R", modifier = Modifier.align(Alignment.Center), color = ArkinPrimary, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Greeting
        Text(text = "Good Morning, Rohan 👋", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        Text(text = "Stay organized, stay productive.", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = ArkinTextMuted)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Hero Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(20.dp, RoundedCornerShape(24.dp), ambientColor = ArkinPrimary, spotColor = ArkinPrimary)
                .clip(RoundedCornerShape(24.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF6466F1), Color(0xFF8B5CF6))))
                .padding(24.dp)
        ) {
            Icon(
                imageVector = PhosphorIcons.Fill.CheckSquareOffset,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.2f),
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 20.dp, y = 20.dp)
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                        Icon(PhosphorIcons.Fill.CalendarBlank, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    val locale = androidx.compose.ui.platform.LocalConfiguration.current.locales[0]
                    Text(text = "Today, ${SimpleDateFormat("MMM dd", locale).format(Date())}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Plan your day and achieve your goals.", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(0.7f))
                Spacer(modifier = Modifier.height(24.dp))
                Box(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(50)).background(Color.Black.copy(alpha = 0.2f))) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (totalTasks > 0) completedTasks.toFloat() / totalTasks else 0f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFFF9A8D4))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$completedTasks / $totalTasks Tasks Completed", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Quick Access
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Quick Access", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "Edit", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = ArkinPrimary)
                Icon(PhosphorIcons.Regular.PencilSimple, contentDescription = null, tint = ArkinPrimary, modifier = Modifier.size(14.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                QuickAccessItem(icon = PhosphorIcons.Fill.FileText, label = "Notes", subLabel = "All your notes", bgColor = IconBgPurple, iconColor = IconColorPurple) { navController.navigate("notes") }
                QuickAccessItem(icon = PhosphorIcons.Fill.ListChecks, label = "To-Do", subLabel = "Your tasks", bgColor = IconBgTeal, iconColor = IconColorTeal) { navController.navigate("todo") }
                QuickAccessItem(icon = PhosphorIcons.Fill.BookOpen, label = "Homework", subLabel = "Assignments", bgColor = IconBgOrange, iconColor = IconColorOrange) { navController.navigate("homework") }
                QuickAccessItem(icon = PhosphorIcons.Fill.CalendarBlank, label = "Timetable", subLabel = "Class schedule", bgColor = IconBgBlue, iconColor = IconColorBlue) { navController.navigate("timetable") }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                QuickAccessItem(icon = PhosphorIcons.Fill.Robot, label = "AI Assistant", subLabel = "Ask anything", bgColor = IconBgPink, iconColor = IconColorPink) { navController.navigate("ai") }
                QuickAccessItem(icon = PhosphorIcons.Fill.ShieldCheck, label = "Locker", subLabel = "Secure vault", bgColor = IconBgLocker, iconColor = IconColorLocker) { navController.navigate("locker") }
                QuickAccessItem(icon = PhosphorIcons.Fill.BellRinging, label = "Reminders", subLabel = "Stay updated", bgColor = IconBgYellow, iconColor = IconColorYellow) { navController.navigate("reminders") }
                QuickAccessItem(icon = PhosphorIcons.Fill.ChartPieSlice, label = "Analytics", subLabel = "Your progress", bgColor = IconBgPurple, iconColor = IconColorPurple) { navController.navigate("analytics") }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Today's Overview
        Text(text = "Today's Overview", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                OverviewCard(icon = PhosphorIcons.Fill.FileText, count = notes.count { !it.isSecure }.toString(), label = "Notes", subLabel = "Total notes", bgColor = IconBgPurple.copy(alpha = 0.4f), iconColor = IconColorPurple)
            }
            item {
                OverviewCard(icon = PhosphorIcons.Fill.ListChecks, count = (totalTasks - completedTasks).toString(), label = "Tasks", subLabel = "Pending tasks", bgColor = IconBgTeal.copy(alpha = 0.4f), iconColor = IconColorTeal) { navController.navigate("todo") }
            }
            item {
                OverviewCard(icon = PhosphorIcons.Fill.BookOpen, count = homeworkList.size.toString(), label = "Homework", subLabel = "Due today", bgColor = IconBgOrange.copy(alpha = 0.4f), iconColor = IconColorOrange) { navController.navigate("homework") }
            }
        }
    }
}

@Composable
fun QuickAccessItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    subLabel: String,
    bgColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                .clip(RoundedCornerShape(16.dp))
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = iconColor, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        Text(text = subLabel, fontSize = 9.sp, color = ArkinTextMuted)
    }
}

@Composable
fun OverviewCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: String,
    label: String,
    subLabel: String,
    bgColor: Color,
    iconColor: Color,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
                }
                Text(text = count, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            Text(text = subLabel, fontSize = 12.sp, color = ArkinTextMuted)
        }
    }
}
